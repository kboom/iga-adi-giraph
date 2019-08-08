package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;

final class OperationUtil {

  private static final int ROWS = 6;

  private OperationUtil() {

  }

  /*
    def swapDofs(a: Int, b: Int, size: Int)(implicit p: IgaElement): Unit = {
      p.mA.swapRows(a, b, size)
      p.mA.swapCols(a, b, size)
      p.mB.swapRows(a, b)
      p.mX.swapRows(a, b)
  }

  def swapRows(r1: Int, r2: Int, size: Int): DenseMatrix[Double] = {
    val old = d(r1, 0 until size).inner.copy.t
    d(r1, 0 until size) := d(r2, 0 until size)
    d(r2, 0 until size) := old
        d
  }

  def swapCols(c1: Int, c2: Int, size: Int): DenseMatrix[Double] = {
    val old = d(0 until size, c1).copy
    d(0 until size, c1) := d(0 until size, c2)
    d(0 until size, c2) := old
        d
  }

   */
  static void swapDofs(IgaElement e, int a, int b, int size) {
    final int rows = (int) e.ma.countRows();
    final int cols = (int) e.ma.countColumns();
    e.ma.regionByLimits(rows, size).exchangeRows(a, b);
    e.ma.regionByLimits(size, cols).exchangeColumns(a, b);
    e.mb.exchangeRows(a, b);
    e.mx.exchangeRows(a, b);
  }

  /*

  for (irow <- 0 until elim) {
      val diag = p.mA(irow, irow)
      p.mA(irow, irow until size) :/= diag
      p.mB(irow, ::) :/= diag

      for (isub <- irow + 1 until size) {
        val mult = p.mA(isub, irow)

        p.mA(isub, irow until size) :-= p.mA(irow, irow until size) * mult
        p.mB(isub, ::) :-= p.mB(irow, ::) * mult
      }
    }

   */
  static void partialForwardElimination(IgaElement e, int elim, int size) {
    final int nrhs = (int) e.mx.countColumns();
    final double[] ma = e.ma.data; // column-major
    final double[] mb = e.mb.data; // column-major

    for (int irow = 0; irow < elim; irow++) {
      double diag = ma[irow * ROWS + irow];
      for (int icol = irow; icol < size; icol++) {
        ma[ROWS * icol + irow] /= diag;
      }
      for (int irhs = 0; irhs < nrhs; irhs++) {
        mb[irow + irhs * ROWS] /= diag; // ?
      }
      for (int isub = irow + 1; isub < size; isub++) {
        double mult = ma[irow * ROWS + isub];
        for (int icol = irow; icol < size; icol++) {
          ma[icol * ROWS + isub] -= ma[icol * ROWS + irow] * mult;
        }
        for (int irhs = 0; irhs < nrhs; irhs++) {
          mb[irhs * ROWS + isub] -= mb[irhs * ROWS + irow] * mult;
        }
      }
    }
  }

}
