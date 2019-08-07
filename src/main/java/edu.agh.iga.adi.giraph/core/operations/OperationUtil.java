package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;

final class OperationUtil {

  private OperationUtil() {

  }

  static void swapDofs(IgaElement e, int a, int b, int size) {
    e.ma.regionByLimits(size, 1).exchangeRows(a, b);
    e.ma.regionByLimits(1, size).exchangeColumns(a, b);
    e.mb.exchangeRows(a, b);
    e.mx.exchangeRows(a, b);
  }

}
