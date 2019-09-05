package edu.agh.iga.adi.giraph.core.problem.phenomena;

import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class HeatTransferPhenomena implements Problem {

  private final PartialSolution solution;

  @Override
  public double valueAt(double x, double y) {
    return solution.valueAt(x, y);
  }

}
