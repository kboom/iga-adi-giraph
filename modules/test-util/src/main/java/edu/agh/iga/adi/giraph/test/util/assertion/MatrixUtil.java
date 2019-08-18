package edu.agh.iga.adi.giraph.test.util.assertion;

import org.ojalgo.function.UnaryFunction;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static java.lang.Math.round;

public class MatrixUtil {

  public static PrimitiveDenseStore weakMatrix(PrimitiveDenseStore ds, int precision) {
    final double prec = Math.pow(10, precision);
    PrimitiveDenseStore mds = ds.copy();
    mds.modifyAll(new UnaryFunction<Double>() {

      @Override
      public double invoke(double arg) {
        return (double) round(arg * prec) / prec;
      }

      @Override
      public Double invoke(Double arg) {
        return invoke(arg.doubleValue());
      }

    });
    return mds;
  }

}
