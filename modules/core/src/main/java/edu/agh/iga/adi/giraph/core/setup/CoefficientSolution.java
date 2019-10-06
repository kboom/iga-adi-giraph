package edu.agh.iga.adi.giraph.core.setup;

import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.problem.SolutionTransformer;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import lombok.val;
import org.ojalgo.structure.Access2D;

public class CoefficientSolution implements PartialSolution {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private final Mesh mesh;
  private final Access2D<Double> coef;

  CoefficientSolution(Mesh mesh, Access2D<Double> coefficients) {
    this.mesh = mesh;
    this.coef = coefficients;
  }

  @Override
  public double valueAt(double x, double y) {
    return internalValueAt(y, x); // we rotate the plane
  }

  private double internalValueAt(double x, double y) {
    val ielemx = (long) (x / mesh.getDx());
    val ielemy = (long) (y / mesh.getDy());
    val localx = x - mesh.getDx() * ielemx;
    val localy = y - mesh.getDy() * ielemy;

    val sp1x = b1.getValue(localx);
    val sp1y = b1.getValue(localy);
    val sp2y = b2.getValue(localy);
    val sp2x = b2.getValue(localx);
    val sp3y = b3.getValue(localy);
    val sp3x = b3.getValue(localx);

    return coef.doubleValue(0, ielemy) * sp1x * sp1y +
        coef.doubleValue(0, ielemy + 1) * sp1x * sp2y +
        coef.doubleValue(0, ielemy + 2) * sp1x * sp3y +
        coef.doubleValue(1, ielemy) * sp2x * sp1y +
        coef.doubleValue(1, ielemy + 1) * sp2x * sp2y +
        coef.doubleValue(1, ielemy + 2) * sp2x * sp3y +
        coef.doubleValue(2, ielemy) * sp3x * sp1y +
        coef.doubleValue(2, ielemy + 1) * sp3x * sp2y +
        coef.doubleValue(2, ielemy + 2) * sp3x * sp3y;
  }

  @Override
  public double valueAt(double x, double y, SolutionTransformer transformer) {
    return transformer.valueAt(coef, x, y);
  }

}
