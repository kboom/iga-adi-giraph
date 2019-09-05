package edu.agh.iga.adi.giraph.core.setup;

import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import org.ojalgo.structure.Access2D;

public final class CoefficientSolution implements PartialSolution {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private final Mesh mesh;
  private final Access2D<Double> coefficients;

  public CoefficientSolution(Mesh mesh, Access2D<Double> coefficients) {
    this.mesh = mesh;
    this.coefficients = coefficients;
  }

  @Override
  public double valueAt(double x, double y) {
    final int ielemx = (int) (x / mesh.getDx()) + 1;
    final int ielemy = (int) (y / mesh.getDy()) + 1;
    final double localx = x - mesh.getDx() * (ielemx - 1);
    final double localy = y - mesh.getDy() * (ielemy - 1);
    return b1.getValue(localx) * b1.getValue(localy) * coefficients.doubleValue(ielemx, ielemy)
        + b1.getValue(localx) * b2.getValue(localy) * coefficients.doubleValue(ielemx, ielemy + 1)
        + b1.getValue(localx) * b3.getValue(localy) * coefficients.doubleValue(ielemx, ielemy + 2)
        + b2.getValue(localx) * b1.getValue(localy) * coefficients.doubleValue(ielemx + 1, ielemy)
        + b2.getValue(localx) * b2.getValue(localy) * coefficients.doubleValue(ielemx + 1, ielemy + 1)
        + b2.getValue(localx) * b3.getValue(localy) * coefficients.doubleValue(ielemx + 1, ielemy + 2)
        + b3.getValue(localx) * b1.getValue(localy) * coefficients.doubleValue(ielemx + 2, ielemy)
        + b3.getValue(localx) * b2.getValue(localy) * coefficients.doubleValue(ielemx + 2, ielemy + 1)
        + b3.getValue(localx) * b3.getValue(localy) * coefficients.doubleValue(ielemx + 2, ielemy + 2);
  }

}
