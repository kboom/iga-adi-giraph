package edu.agh.iga.adi.giraph.core.factory;

import org.ojalgo.matrix.PrimitiveMatrix;

public final class ExplicitMethodCoefficients implements MethodCoefficients {

  public static final ExplicitMethodCoefficients EXPLICIT_METHOD_COEFFICIENTS = new ExplicitMethodCoefficients();

  private static final PrimitiveMatrix EXPLICIT_COEFFICIENTS = PrimitiveMatrix.FACTORY.rows(
      new double[] {1.0 / 20.0, 13.0 / 120, 1.0 / 120},
      new double[] {13.0 / 120.0, 45.0 / 100.0, 13.0 / 120.0},
      new double[] {1.0 / 120.0, 13.0 / 120.0, 1.0 / 20.0}
  );

  @Override
  public PrimitiveMatrix coefficients() {
    return EXPLICIT_COEFFICIENTS;
  }
}
