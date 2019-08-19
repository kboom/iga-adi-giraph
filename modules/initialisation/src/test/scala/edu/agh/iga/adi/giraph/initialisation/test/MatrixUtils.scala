package edu.agh.iga.adi.giraph.initialisation.test

import breeze.linalg.DenseMatrix

object MatrixUtils {

  def exploded(m: DenseMatrix[Double]): DenseMatrix[Double] =
    DenseMatrix.create(m.majorStride, m.data.length / m.majorStride, m.data)

  def weakPrecision(m: DenseMatrix[Double]): DenseMatrix[Double] =
    m.mapValues(BigDecimal(_).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble)

  def precisionCut(m: DenseMatrix[Double]): DenseMatrix[Double] =
    m.mapValues(BigDecimal(_).setScale(3, BigDecimal.RoundingMode.FLOOR).toDouble)

  def fromVector(r: Int, c: Int)(cells: Double*): DenseMatrix[Double] = DenseMatrix.create(c, r, cells.toArray).t

  def identityMatrix(size: Int): DenseMatrix[Double] =
    assembleMatrix(size, size)(Seq(diagonal))

  def diagonal: (Int, Int) => Double = (i, j) => if (i == j) 1 else 0

  def unit: (Int, Int) => Double = fill(1)

  def fill(v: Double): (Int, Int) => Double = (_, _) => v

  def sumOfIndexes(): (Int, Int) => Double = (x, y) => x + y

  def entry(r: Int, c: Int)(v: Double): (Int, Int) => Double = (tr, tc) => if (tr == r && tc == c) v else 0

  def index: (Int, Int) => Double = (tr, tc) => tr + (tc.toDouble / 100)

  def identity: (Int, Int) => Double = (tr, tc) => if (tr == tc) 1 else 0

  def indexedSquareMatrix(size: Int): DenseMatrix[Double] = indexedMatrix(size, size)

  def indexedMatrix(r: Int, c: Int): DenseMatrix[Double] = generatedMatrix(r, c)((rc, cc) => rc + (cc.toDouble / 100))

  def dummyMatrix(r: Int, c: Int): DenseMatrix[Double] = generatedMatrix(r, c)(_ + _)

  def featuredMatrix(r: Int, c: Int)(f: Int*): DenseMatrix[Double] = generatedMatrix(r, c)((rc, cc) => if (f.contains(c * rc + cc)) 1.0 else 0)

  def matrixOf(r: Int, c: Int)(m: Array[Array[Double]]*): DenseMatrix[Double] = generatedMatrix(r, c)((rc, cc) => m.map(_ (rc)(cc)).sum)

  def constMatrix(r: Int, c: Int)(o: Double = 1): DenseMatrix[Double] = generatedMatrix(r, c)((_, _) => o)

  def unitMatrix(r: Int, c: Int): DenseMatrix[Double] = constMatrix(r, c)(1)

  def assembleMatrix(s: Int)(g: Seq[(Int, Int) => Double]): DenseMatrix[Double] = assembleMatrix(s, s)(g)

  def assembleMatrix(r: Int, c: Int)(g: Seq[(Int, Int) => Double]): DenseMatrix[Double] =
    DenseMatrix.tabulate(r, c) { case (x, y) => g.map(_.apply(x, y)).sum }

  @Deprecated
  def generatedMatrix(r: Int, c: Int)(gen: (Int, Int) => Double): DenseMatrix[Double] =
    DenseMatrix.tabulate(r, c) { case (x, y) => gen(x, y) }

}
