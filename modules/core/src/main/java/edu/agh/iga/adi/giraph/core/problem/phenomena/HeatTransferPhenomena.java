package edu.agh.iga.adi.giraph.core.problem.phenomena;

import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.problem.SolutionTransformer;
import lombok.RequiredArgsConstructor;
import org.ojalgo.structure.Access2D;

@RequiredArgsConstructor
public final class HeatTransferPhenomena implements Problem, SolutionTransformer {

  private final PartialSolution solution;
  private final int delta;

  @Override
  public double valueAt(double x, double y) {
    return solution.valueAt(x, y) + delta * solution.valueAt(x, y, this);
  }

  @Override
  public double valueAt(Access2D<Double> c, double x, double y) {
    return 0;
  }

}
