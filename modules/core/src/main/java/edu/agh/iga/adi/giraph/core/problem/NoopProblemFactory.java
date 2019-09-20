package edu.agh.iga.adi.giraph.core.problem;

import edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients;
import edu.agh.iga.adi.giraph.core.factory.MethodCoefficients;

public class NoopProblemFactory implements ProblemFactory {
  @Override
  public Problem problemFor(PartialSolution partialSolution) {
    return partialSolution::valueAt;
  }

  @Override
  public MethodCoefficients coefficients() {
    return new ExplicitMethodCoefficients();
  }
}
