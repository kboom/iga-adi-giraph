package edu.agh.iga.adi.giraph.initialisation.initialisation

import breeze.linalg.{DenseMatrix, DenseVector}
import edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf
import edu.agh.iga.adi.giraph.core.{DirectionTree, IgaVertex, Mesh}
import edu.agh.iga.adi.giraph.initialisation.initialisation.HorizontalInitializer.collocate
import edu.agh.iga.adi.giraph.initialisation.problem.GaussPoint.gaussPoints
import edu.agh.iga.adi.giraph.initialisation.problem._
import edu.agh.iga.adi.giraph.initialisation.spark.EvenlyDistributedRDD
import edu.agh.iga.adi.giraph.initialisation.{ElementCoefficients, IgaContext}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.annotation.switch

sealed trait ValueProvider {
  def valueAt(i: Double, j: Double): Double
}

case class FromCoefficientsValueProvider(problem: Problem, m: DenseMatrix[Double]) extends ValueProvider {
  private val extractor = MatrixExtractor(m)

  override def valueAt(x: Double, y: Double): Double = problem.valueAt(extractor, x, y)
}

case class FromProblemValueProvider(problem: Problem) extends ValueProvider {
  override def valueAt(x: Double, y: Double): Double = problem.valueAt(NoExtractor, x, y)
}

object HorizontalInitializer {

  def collocate(r: Iterator[(Long, DenseVector[Double])])(implicit ctx: IgaContext): Iterator[(Long, Seq[(Int, DenseVector[Double])])] =
    r.flatMap { row =>
      val idx = row._1.toInt

      VerticalInitializer.verticesDependentOnRow(idx)
        .map(vertex => {
          val localRow = VerticalInitializer.findLocalRowFor(vertex, idx)
          (vertex.id, Seq((localRow, row._2)))
        })
    }

}

case class HorizontalInitializer(surface: Surface, problem: Problem) extends LeafInitializer {

  override def leafData(ctx: IgaContext)(implicit sc: SparkContext): RDD[(Long, ElementCoefficients)] = {
    surface match {
      case PlainSurface(_) => initializeSurface(ctx)
      case s: SplineSurface => projectSurface(ctx, s)
    }
  }

  private def initializeSurface(ctx: IgaContext)(implicit sc: SparkContext): RDD[(Long, ElementCoefficients)] = {
    implicit val tree: DirectionTree = ctx.xTree()
    val provider = FromProblemValueProvider(problem)

    new EvenlyDistributedRDD(sc, tree.firstIndexOfLeafRow, tree.lastIndexOfLeafRow)
      //      .partitionBy(VertexPartitioner(sc.defaultParallelism, tree)) // this lets spark group the next operation under the same stage as the join from the calling class
//      .repartition(sc.defaultParallelism)
      .mapPartitions(_.map { case (idx, _) =>
        val vertex = vertexOf(tree, idx)
        (idx, createElement(vertex, provider)(ctx))
      }, preservesPartitioning = true)
  }

  private def projectSurface(ctx: IgaContext, ss: SplineSurface)(implicit sc: SparkContext): RDD[(Long, ElementCoefficients)] = {
    implicit val tree: DirectionTree = ctx.xTree()

    ss.m
      .mapPartitions(collocate(_)(ctx))
      .reduceByKey(_ ++ _)
      .mapPartitions(
        _.map { case (vid, s) =>
          val v = vertexOf(tree, vid)
          val dx = s.toMap
          (vid, createElement(v, FromCoefficientsValueProvider(problem, DenseMatrix(
            dx(0),
            dx(1),
            dx(2)
          )))(ctx)) // todo this might be incorrect for more complex computations (column major)
        },
        preservesPartitioning = true
      )
  }

  private def createElement(v: IgaVertex, vp: ValueProvider)(implicit ctx: IgaContext): ElementCoefficients = {
    implicit val mesh: Mesh = ctx.mesh
    implicit val problemTree: DirectionTree = ctx.tree()
    val segment = v.segmentOf().getLeft
    ElementCoefficients(DenseMatrix.tabulate(6, mesh.getDofsX)((j, i) => if (j < 3) force(vp, segment, j, i) else 0))
  }

  private def force(vp: ValueProvider, segment: Double, r: Int, i: Int)(implicit ctx: IgaContext): Double = {
    implicit val problemTree: DirectionTree = ctx.tree()
    implicit val mesh: Mesh = ctx.mesh

    val left = (r: @switch) match {
      case 0 => GaussPoint.S31
      case 1 => GaussPoint.S21
      case 2 => GaussPoint.S11
    }

    val center = (r: @switch) match {
      case 0 => GaussPoint.S32
      case 1 => GaussPoint.S22
      case 2 => GaussPoint.S12
    }

    val right = (r: @switch) match {
      case 0 => GaussPoint.S33
      case 1 => GaussPoint.S23
      case 2 => GaussPoint.S13
    }

    var value = 0.0

    val dy = mesh.getDy
    val elemY = mesh.getElementsY

    for (k <- 0 until GaussPoint.gaussPointCount) {
      val gpk = gaussPoints(k)
      val x = gpk.v * mesh.getDx + segment

      for (l <- 0 until GaussPoint.gaussPointCount) {
        val gpl = gaussPoints(l)
        if (i > 1) {
          val y = (gpl.v + i - 2) * dy
          value += left(k, l) * vp.valueAt(x, y)
        }
        if (i > 0 && (i - 1) < elemY) {
          val y = (gpl.v + i - 1) * dy
          value += center(k, l) * vp.valueAt(x, y)
        }
        if (i < elemY) {
          val y = (gpl.v + i) * dy
          value += right(k, l) * vp.valueAt(x, y)
        }
      }
    }

    value
  }

}
