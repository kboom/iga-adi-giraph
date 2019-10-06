package edu.agh.iga.adi.giraph.core.problem;

import edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients;
import edu.agh.iga.adi.giraph.core.factory.MethodCoefficients;
import lombok.val;

public class NoopProblemFactory implements ProblemFactory {
  @Override
  public Problem problemFor(PartialSolution partialSolution) {
    return (x, y) -> {
      val sol = partialSolution.valueAt(x - 0.5, y);
      if(Math.round(sol / 100) * 100 != Math.round(x / 100) * 100) {
        val diff = Math.abs(x - sol);
      }
      return sol;
    };
  }

  @Override
  public MethodCoefficients coefficients() {
    return new ExplicitMethodCoefficients();
  }
}
