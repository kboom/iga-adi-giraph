package edu.agh.iga.adi.giraph.core.setup;

import lombok.val;
import org.ojalgo.matrix.store.TransformableRegion;
import org.ojalgo.structure.Access2D;

class InitialisationAccess2D implements Access2D<Double> {

  private final TransformableRegion<Double> left;
  private final TransformableRegion<Double> right;

  InitialisationAccess2D(TransformableRegion<Double> left, TransformableRegion<Double> right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public double doubleValue(long row, long col) {
    val leftRows = left.countRows();
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
