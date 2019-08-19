package edu.agh.iga.adi.giraph.initialisation.problem

import breeze.linalg.{DenseMatrix, DenseVector}
import edu.agh.iga.adi.giraph.core.Mesh
import edu.agh.iga.adi.giraph.initialisation.problem.Spline.{Spline1T, Spline2T, Spline3T}
import org.apache.spark.rdd.RDD
import org.slf4j.LoggerFactory

sealed trait Surface {
  def mesh: Mesh
}

case class SplineSurface(m: RDD[(Long, DenseVector[Double])], mesh: Mesh) extends Surface

case class PlainSurface(mesh: Mesh) extends Surface

object SplineSurface {

  private val Log = LoggerFactory.getLogger(classOf[SplineSurface])

  def asArray(s: SplineSurface): DenseMatrix[Double] = {
    val arr2d = s.m
      .sortBy(_._1)
      .map(_._2.toArray)
      .collect()

    DenseMatrix.create(arr2d.length, arr2d(0).length, arr2d.reduce(_ ++ _))
  }

  def asString(s: SplineSurface): String = s.m
    .map(_._2.toArray.map(i => f"$i%+.3f").mkString("\t"))
    .collect
    .mkString(System.lineSeparator())


  def print(s: SplineSurface): Unit = {
    Log.info(s"\n${asString(s)}")
  }

  def valueRowsDependentOn(coefficientRow: Int)(implicit mesh: Mesh): Seq[Int] = {
    val elements = mesh.getDofsY
    val size = mesh.getDofsX

    val all = Seq(-1, 0, 1)
      .map(_ + coefficientRow - 1)
      .filterNot { x => x < 0 || x >= size }

    val span = Math.min(3, 1 + Math.min(coefficientRow, elements - 1 - coefficientRow))

    if (coefficientRow < elements / 2) all.take(span) else all.takeRight(span)
  }

  def projectedValue(c: CoefficientExtractor, y: Double, x: Double)(implicit mesh: Mesh): Double = {
    val ielemx = (x / mesh.getDofsX).toInt
    val ielemy = (y / mesh.getDofsY).toInt
    val localx = x - mesh.getDx * ielemx
    val localy = y - mesh.getDy * ielemy

    val sp1x = Spline1T.getValue(localx)
    val sp1y = Spline1T.getValue(localy)
    val sp2y = Spline2T.getValue(localy)
    val sp2x = Spline2T.getValue(localx)
    val sp3y = Spline3T.getValue(localy)
    val sp3x = Spline3T.getValue(localx)

    c match {
      case NoExtractor => sp1x * sp1y + sp1x * sp2y + sp1x * sp3y + sp2x * sp1y + sp2x * sp2y + sp2x * sp3y + sp3x * sp1y + sp3x * sp2y + sp3x * sp3y
      case MatrixExtractor(dm) =>
        dm(0, ielemy) * sp1x * sp1y +
          dm(0, ielemy + 1) * sp1x * sp2y +
          dm(0, ielemy + 2) * sp1x * sp3y +
          dm(1, ielemy) * sp2x * sp1y +
          dm(1, ielemy + 1) * sp2x * sp2y +
          dm(1, ielemy + 2) * sp2x * sp3y +
          dm(2, ielemy) * sp3x * sp1y +
          dm(2, ielemy + 1) * sp3x * sp2y +
          dm(2, ielemy + 2) * sp3x * sp3y
    } // probably can do broadcast which could be more efficient!
  }

}