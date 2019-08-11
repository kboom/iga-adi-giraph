package edu.agh.iga.adi.giraph.core.problem;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.ojalgo.matrix.PrimitiveMatrix;

public final class ExplicitMethodCoefficients {

  private static final PrimitiveMatrix COEFFICIENTS = PrimitiveMatrix.FACTORY.rows(
      new double[] {1.0 / 20.0, 13.0 / 120, 1.0 / 120},
      new double[] {13.0 / 120.0, 45.0 / 100.0, 13.0 / 120.0},
      new double[] {1.0 / 120.0, 13.0 / 120.0, 1.0 / 20.0}
  );

  public static void bindMethodCoefficients(IgaElement e) {
    e.ma.fillMatching(COEFFICIENTS);
  }

}
