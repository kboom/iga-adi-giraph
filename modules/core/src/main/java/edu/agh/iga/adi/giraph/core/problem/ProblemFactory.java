package edu.agh.iga.adi.giraph.core.problem;

import edu.agh.iga.adi.giraph.core.factory.MethodCoefficients;

public interface ProblemFactory {

  Problem problemFor(PartialSolution partialSolution);

  MethodCoefficients coefficients();

}
