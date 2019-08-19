package edu.agh.iga.adi.giraph.initialisation.initialisation

import breeze.linalg.{DenseMatrix, DenseVector}
import edu.agh.iga.adi.giraph.core.{DirectionTree, IgaVertex}
import edu.agh.iga.adi.giraph.initialisation.initialisation.VerticalInitializer.collocate
import edu.agh.iga.adi.giraph.initialisation.problem.SplineSurface
import edu.agh.iga.adi.giraph.initialisation.{ElementCoefficients, IgaContext}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object VerticalInitializer {

  private val FIRST_PARTITION = Array(1d, 1 / 2d, 1 / 3d)
  private val SECOND_PARTITION = Array(1 / 2d, 1 / 3d, 1 / 3d)
  private val THIRD_PARTITION = Array(1 / 3d, 1 / 3d, 1 / 3d)

  private val MIDDLE_PARTITION = Array(1 / 3d, 1 / 3d, 1 / 3d)

  private val THIRD_TO_LAST_PARTITION = Array(1 / 3d, 1 / 3d, 1 / 3d)
  private val SECOND_TO_LAST_PARTITION = Array(1 / 3d, 1 / 3d, 1 / 2d)
  private val LAST_PARTITION = Array(1 / 3d, 1 / 2d, 1d)

  def verticesDependentOnRow(rowNo: Int)(implicit ctx: IgaContext): Seq[IgaVertex] = {
    implicit val tree: DirectionTree = ctx.yTree()
    val elements = ctx.mesh.getDofsY

    val all = Seq(-1, 0, 1)
      .view
      .map(_ + tree.firstIndexOfLeafRow)
      .map(_ + rowNo - 1)
      .filterNot { x => x < tree.firstIndexOfLeafRow || x > tree.lastIndexOfLeafRow }
      .map(IgaVertex.vertexOf(tree, _))


    val span = Math.min(3, 1 + Math.min(rowNo, elements - 1 - rowNo))

    if (rowNo < elements / 2) all.take(span) else all.takeRight(span)
  }

  def findLocalRowFor(v: IgaVertex, rowNo: Int)(implicit ctx: IgaContext): Int = {
    implicit val tree: DirectionTree = ctx.yTree()
    (rowNo - v.offsetLeft()).toInt
  }

  def findPartitionFor(v: IgaVertex, rowNo: Int)(implicit ctx: IgaContext): Double = {
    implicit val tree: DirectionTree = ctx.yTree()
    val localRow = findLocalRowFor(v, rowNo)
    val firstIdx = tree.firstIndexOfLeafRow
    val lastIdx = tree.lastIndexOfLeafRow
    v.id match {
      case x if x == firstIdx => FIRST_PARTITION(localRow)
      case x if x == firstIdx + 1 => SECOND_PARTITION(localRow)
      case x if x == firstIdx + 2 => THIRD_PARTITION(localRow)
      case x if x == lastIdx - 2 => THIRD_TO_LAST_PARTITION(localRow)
      case x if x == lastIdx - 1 => SECOND_TO_LAST_PARTITION(localRow)
      case x if x == lastIdx => LAST_PARTITION(localRow)
      case _ => MIDDLE_PARTITION(localRow)
    }
  }

  def collocate(r: Iterator[(Long, DenseVector[Double])])(implicit ctx: IgaContext): Iterator[(Long, Seq[(Int, DenseVector[Double])])] =
    r.flatMap { row =>
      val idx = row._1.toInt

      VerticalInitializer.verticesDependentOnRow(idx)
        .map(vertex => {
          val localRow = findLocalRowFor(vertex, idx)
          val partition = findPartitionFor(vertex, idx)
          val vertexRowValues = row._2.map(_ * partition)
          (vertex.id, Seq((localRow, vertexRowValues)))
        })
    }
}

case class VerticalInitializer(hsi: SplineSurface) extends LeafInitializer {

  override def leafData(ctx: IgaContext)(implicit sc: SparkContext): RDD[(Long, ElementCoefficients)] = {
    implicit val tree: DirectionTree = ctx.yTree()

    hsi.m
      .mapPartitions(collocate(_)(ctx))
      .reduceByKey(_ ++ _)
      .mapPartitions(
        _.map { case (idx, d) =>
          val dx = d.toMap
          (idx.toLong, ElementCoefficients(DenseMatrix(
            dx(0),
            dx(1),
            dx(2)
          )))
        }, preservesPartitioning = true
      )
  }

}

