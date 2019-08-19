package edu.agh.iga.adi.giraph.initialisation

import java.nio.file.Path

import breeze.linalg.DenseVector
import edu.agh.iga.adi.giraph.core.Mesh
import edu.agh.iga.adi.giraph.initialisation.hadoop.io.ElementCoefficientsWritable
import edu.agh.iga.adi.giraph.initialisation.initialisation.VerticalInitializer
import edu.agh.iga.adi.giraph.initialisation.problem.{CoefficientExtractor, Problem, SplineSurface}
import edu.agh.iga.adi.giraph.initialisation.transposition.Transposition.transpose
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.mapred.TextOutputFormat
import org.apache.spark.SparkContext

object NoProblem extends Problem {
  override def valueAt(c: CoefficientExtractor, x: Double, y: Double): Double = throw new IllegalStateException("Should not be called")
}

case class DirectionFlipperJob(mesh: Mesh) {

  def flip(in: Path, out: Path)(implicit sc: SparkContext): Unit = {
    val surface = SplineSurface(transpose(coefficientRows(in, sc)), mesh)
    val init = new VerticalInitializer(surface)
    init.leafData(IgaContext(mesh, NoProblem, VERTICAL))
      .mapPartitions(_.map {
        case (i, c) => (new LongWritable(i), new ElementCoefficientsWritable(c))
      })
      .saveAsHadoopFile(
        out.toString,
        classOf[LongWritable],
        classOf[ElementCoefficientsWritable],
        classOf[TextOutputFormat[LongWritable, ElementCoefficientsWritable]]
      )
  }

  private def coefficientRows(in: Path, sc: SparkContext) = {
    sc.textFile(in.toString)
      .mapPartitions(mapIntoRows)
  }

  private def mapIntoRows: Iterator[String] => Iterator[(Long, DenseVector[Double])] =
    _.map(line => {
      val vertexAndValue = line.split('\t')
      val vid = vertexAndValue(0).toLong
      val values = vertexAndValue(1).split(',').map(_.toDouble)
      val vector = DenseVector.create(values, 0, 0, values.length)
      (vid, vector)
    })

}
