package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;

final class OperationUtil {

  private static final int ROWS = 6;

  private OperationUtil() {

  }

  static void swapDofs(IgaElement e, int a, int b, int size) {
    e.ma.regionByLimits(size, 1).exchangeRows(a, b);
    e.ma.regionByLimits(1, size).exchangeColumns(a, b);
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
