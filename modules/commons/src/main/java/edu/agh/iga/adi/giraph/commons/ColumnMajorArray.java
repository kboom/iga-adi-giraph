package edu.agh.iga.adi.giraph.commons;

import org.ojalgo.structure.Access2D;

public final class ColumnMajorArray implements Access2D<Double> {

  private final int rows;
  private final int cols;
  private final int offset;
  private final double[] values;

  public ColumnMajorArray(int rows, int cols, int offset, double[] values) {
    this.rows = rows;
    this.cols = cols;
    this.offset = offset;
    this.values = values;
  }

  @Override
  public double doubleValue(long row, long col) {
    return values[offset + (int) (col * rows + row)];
  }

  @Override
  public Double get(long row, long col) {
    return values[offset + (int) (col * rows + row)];
  }

  @Override
  public long countColumns() {
    return cols;
  }

  @Override
  public long countRows() {
    return rows;
  }

}
