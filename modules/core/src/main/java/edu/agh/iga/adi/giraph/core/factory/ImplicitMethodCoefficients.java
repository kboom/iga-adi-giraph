package edu.agh.iga.adi.giraph.core.factory;

import org.ojalgo.matrix.PrimitiveMatrix;

public final class ImplicitMethodCoefficients implements MethodCoefficients  {

  private static final PrimitiveMatrix IMPLICIT_COEFFICIENTS = PrimitiveMatrix.FACTORY.rows(
      new double[] {1.0 / 20.0 + 1.0 / 3.0, 13.0 / 120 - 1.0 / 6.0, 1.0 / 120 - 1.0 / 6.0},
      new double[] {13.0 / 120.0 - 1.0 / 6.0, 45.0 / 100.0 + 1.0 / 3.0, 13.0 / 120.0 - 1.0 / 6.0},
      new double[] {1.0 / 120.0 - 1.0 / 6.0, 13.0 / 120.0 - 1.0 / 6.0, 1.0 / 20.0 + 1.0 / 3.0}
  );

  @Override
  public PrimitiveMatrix coefficients() {
    return IMPLICIT_COEFFICIENTS;
  }

}
