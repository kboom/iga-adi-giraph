package edu.agh.iga.adi.giraph.initialisation.problem

import breeze.linalg.DenseMatrix
import edu.agh.iga.adi.giraph.core.Mesh

trait CoefficientExtractor
case object NoExtractor extends CoefficientExtractor
case class MatrixExtractor(mat: DenseMatrix[Double]) extends CoefficientExtractor

abstract class Problem extends Serializable {
  /**
    * Gets the new value in (x,y) based on current projection (coefficients)
    *
    * @param c local coefficients 3x(N+2)
    * @param x
    * @param y
    * @return
    */
  def valueAt(c: CoefficientExtractor, x: Double, y: Double): Double
}

abstract class StaticProblem extends Problem {
  final def valueAt(c: CoefficientExtractor, x: Double, y: Double): Double =
    valueAt(x, y)

  def valueAt(x: Double, y: Double): Double
}

abstract class IterativeProblem(mesh: Mesh) extends Problem {
  implicit val thisMesh = mesh
}