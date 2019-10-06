package edu.agh.iga.adi.giraph.core.setup;

import org.ojalgo.matrix.store.TransformableRegion;
import org.ojalgo.structure.Access2D;

class InitialisationAccess2D implements Access2D<Double> {

  private final TransformableRegion<Double> left;
  private final TransformableRegion<Double> right;
  private final int leftRows;

  InitialisationAccess2D(TransformableRegion<Double> left, TransformableRegion<Double> right, int leftRows) {
    this.left = left;
    this.right = right;
    this.leftRows = leftRows;
  }

  @Override
  public double doubleValue(long row, long col) {
    if (row < leftRows) {
      return left.doubleValue(row, col);
    } else {
      return right.doubleValue(row - leftRows, col);
    }
  }

  @Override
  public Double get(long row, long col) {
    return doubleValue(row, col);
  }

  @Override
  public long countColumns() {
    return left.countColumns();
  }

  @Override
  public long countRows() {
    return 3;
  }

}
