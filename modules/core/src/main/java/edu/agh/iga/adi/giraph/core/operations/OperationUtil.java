package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.ojalgo.function.constant.PrimitiveMath.DIVIDE;

@NoArgsConstructor(access = PRIVATE)
final class OperationUtil {

  static void swapDofs(IgaElement e, int a, int b, int size) {
    final int rows = (int) e.ma.countRows();
    final int cols = (int) e.ma.countColumns();
    e.ma.regionByLimits(rows, size).exchangeRows(a, b);
    e.ma.regionByLimits(size, cols).exchangeColumns(a, b);
    e.mb.exchangeRows(a, b);
    e.mx.exchangeRows(a, b);
  }

  static void partialForwardElimination(IgaElement e, int elim, int size) {
    final int rows = (int) e.mx.countRows();
    final int nrhs = (int) e.mx.countColumns();
    final double[] ma = e.ma.data; // column-major
    final double[] mb = e.mb.data; // column-major

    for (int irow = 0; irow < elim; irow++) {
      double diag = ma[irow * rows + irow];
      for (int icol = irow; icol < size; icol++) {
        ma[rows * icol + irow] /= diag;
      }
      for (int irhs = 0; irhs < nrhs; irhs++) {
        mb[irow + irhs * rows] /= diag; // ?
      }
      for (int isub = irow + 1; isub < size; isub++) {
        double mult = ma[irow * rows + isub];
        for (int icol = irow; icol < size; icol++) {
          ma[icol * rows + isub] -= ma[icol * rows + irow] * mult;
        }
        for (int irhs = 0; irhs < nrhs; irhs++) {
          mb[irhs * rows + isub] -= mb[irhs * rows + irow] * mult;
        }
      }
    }
  }

  static void partialBackwardsSubstitution(IgaElement e, int elim, int size) {
    final int nrhs = (int) e.mx.countColumns();
    for (int irhs = 0; irhs < nrhs; irhs++) {
      for (int irow = elim - 1; irow >= 0; irow--) {
        e.mx.set(irow, irhs, e.mb.doubleValue(irow, irhs));
        for (int icol = irow + 1; icol < size; icol++) {
          e.mx.set(irow, irhs, e.mx.doubleValue(irow, irhs) - e.ma.doubleValue(irow, icol) * e.mx.doubleValue(icol, irhs));
        }
        e.mx.modifyOne(irow, irhs, DIVIDE.by(e.ma.doubleValue(irow, irow)));
      }
    }
  }

}
