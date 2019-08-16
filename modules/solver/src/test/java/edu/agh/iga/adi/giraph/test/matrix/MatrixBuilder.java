package edu.agh.iga.adi.giraph.test.matrix;

import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.structure.Access2D;
import org.ojalgo.structure.Mutate2D;
import org.ojalgo.structure.Transformation2D;

import java.util.Arrays;

import static java.lang.System.arraycopy;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public class MatrixBuilder {

  private final int rows;
  private final int cols;
  private final double[] values;

  private MatrixBuilder(int rows, int cols, double[] values) {
    assert values.length == rows * cols;
    this.rows = rows;
    this.cols = cols;
    this.values = new double[rows * cols];
    arraycopy(values, 0, this.values, 0, values.length);
  }

  public PrimitiveDenseStore build() {
    PrimitiveDenseStore ds = FACTORY.makeZero(rows, cols);
    ds.modifyAny(new Transformation());
    return ds;
  }

  public static MatrixValuesBuilder matrixOfSize(int rows, int cols) {
    return new MatrixValuesBuilder(rows, cols);
  }

  public static PrimitiveDenseStore emptyMatrixOfSize(int rows, int cols) {
    return FACTORY.makeZero(rows, cols);
  }

  public static class MatrixValuesBuilder {
    private final int rows;
    private final int cols;

    private MatrixValuesBuilder(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
    }

    public PrimitiveDenseStore withValue(double value) {
      double[] values = new double[rows * cols];
      Arrays.fill(values, value);
      return new MatrixBuilder(rows, cols, values).build();
    }

    public PrimitiveDenseStore withIndexedValues() {
      double[] values = new double[rows * cols];
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          values[r * cols + c] = Double.valueOf((r + 1) + "." + (c + 1));
        }
      }
      return new MatrixBuilder(rows, cols, values).build();
    }

    public PrimitiveDenseStore withValues(double... values) {
      return new MatrixBuilder(rows, cols, values).build();
    }
  }

  private double valueAt(int row, int col) {
    return values[cols * row + col];
  }

  private class Transformation implements Transformation2D<Double> {

    @Override
    public final <T extends Mutate2D.ModifiableReceiver<Double> & Access2D<Double>> void transform(T t) {
      t.loopAll((row, col) -> t.set(row, col, valueAt((int) row, (int) col)));
    }

  }

}
