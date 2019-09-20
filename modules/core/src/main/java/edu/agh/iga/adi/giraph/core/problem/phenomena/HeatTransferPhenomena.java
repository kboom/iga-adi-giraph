package edu.agh.iga.adi.giraph.core.problem.phenomena;

import edu.agh.iga.adi.giraph.core.factory.MethodCoefficients;
import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.problem.ProblemFactory;
import edu.agh.iga.adi.giraph.core.problem.SolutionTransformer;
import lombok.RequiredArgsConstructor;
import org.ojalgo.structure.Access2D;

import static edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients.EXPLICIT_METHOD_COEFFICIENTS;

@RequiredArgsConstructor
public final class HeatTransferPhenomena implements Problem, SolutionTransformer {

  private final PartialSolution solution;
  private final double delta;

  @Override
  public double valueAt(double x, double y) {
    return solution.valueAt(x, y) + delta * solution.valueAt(x, y, this);
  }

  @Override
  public double valueAt(Access2D<Double> c, double x, double y) {
    return 0;
  }

  public static class HeatTransferPhenomenaFactory implements ProblemFactory {

    @Override
    public Problem problemFor(PartialSolution partialSolution) {
      return new HeatTransferPhenomena(partialSolution, 0.00000000001);
    }

    @Override
    public MethodCoefficients coefficients() {
      return EXPLICIT_METHOD_COEFFICIENTS;
    }

  }

}
