package edu.agh.iga.adi.giraph.core.problem.phenomena;

import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.MethodCoefficients;
import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.problem.ProblemFactory;
import edu.agh.iga.adi.giraph.core.problem.SolutionTransformer;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import lombok.Builder;
import lombok.val;
import org.ojalgo.structure.Access2D;

import static edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients.EXPLICIT_METHOD_COEFFICIENTS;

@Builder
public final class HeatTransferPhenomena implements Problem, SolutionTransformer {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private final PartialSolution solution;
  private final Mesh mesh;
  private final int step;
  private final double delta;

  @Override
  public double valueAt(double x, double y) {
    return solution.valueAt(x, y) + delta * solution.valueAt(x, y, this);
  }

  @Override
  public double valueAt(Access2D<Double> c, double x, double y) {
    val ielemx = (long) (x / mesh.getDx());
    val ielemy = (long) (y / mesh.getDy());
    val localx = x - mesh.getDx() * ielemx;
    val localy = y - mesh.getDy() * ielemy;

    double solution = 0.0;

    if (!isEven()) {
      val b1dx = b1.getSecondDerivativeValueAt(localx);
      val b1y = b1.getValue(localy);
      val b2dx = b2.getSecondDerivativeValueAt(localx);
      val b2y = b2.getValue(localy);
      val b3dx = b3.getSecondDerivativeValueAt(localx);
      val b3y = b3.getValue(localy);

      solution += b1dx * b1y * c.doubleValue(0, ielemy);
      solution += b1dx * b2y * c.doubleValue(0, ielemy + 1);
      solution += b1dx * b3y * c.doubleValue(0, ielemy + 2);
      solution += b2dx * b1y * c.doubleValue(1, ielemy);
      solution += b2dx * b2y * c.doubleValue(1, ielemy + 1);
      solution += b2dx * b3y * c.doubleValue(1, ielemy + 2);
      solution += b3dx * b1y * c.doubleValue(2, ielemy);
      solution += b3dx * b2y * c.doubleValue(2, ielemy + 1);
      solution += b3dx * b3y * c.doubleValue(2, ielemy + 2);

    } else {
      val b1dy = b1.getSecondDerivativeValueAt(localy);
      val b1x = b1.getValue(localx);
      val b2dy = b2.getSecondDerivativeValueAt(localy);
      val b2x = b2.getValue(localx);
      val b3dy = b3.getSecondDerivativeValueAt(localy);
      val b3x = b3.getValue(localx);

      solution += b1x * b1dy * c.doubleValue(0, ielemy);
      solution += b1x * b2dy * c.doubleValue(0, ielemy + 1);
      solution += b1x * b3dy * c.doubleValue(0, ielemy + 2);
      solution += b2x * b1dy * c.doubleValue(1, ielemy);
      solution += b2x * b2dy * c.doubleValue(1, ielemy + 1);
      solution += b2x * b3dy * c.doubleValue(1, ielemy + 2);
      solution += b3x * b1dy * c.doubleValue(2, ielemy);
      solution += b3x * b2dy * c.doubleValue(2, ielemy + 1);
      solution += b3x * b3dy * c.doubleValue(2, ielemy + 2);
    }

    return solution;
  }

  private boolean isEven() {
    return step % 2 == 0;
  }

  @Builder
  public static class HeatTransferPhenomenaFactory implements ProblemFactory {

    private final Mesh mesh;
    private final int step;
    private final double delta;

    @Override
    public Problem problemFor(PartialSolution partialSolution) {
      return HeatTransferPhenomena
          .builder()
          .solution(partialSolution)
          .delta(delta)
          .mesh(mesh)
          .step(step)
          .build();
    }

    @Override
    public MethodCoefficients coefficients() {
      return EXPLICIT_METHOD_COEFFICIENTS;
    }

  }

}
