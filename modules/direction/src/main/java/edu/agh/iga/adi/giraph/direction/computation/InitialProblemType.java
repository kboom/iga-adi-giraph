package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.problem.initial.RadialProblem;
import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;

import java.util.function.Function;

import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.config.InitialComputationConfiguration.RADIAL_PROBLEM_RADIUS_RATIO;
import static edu.agh.iga.adi.giraph.direction.config.InitialComputationConfiguration.RADIAL_PROBLEM_RADIUS_VALUE;

public enum InitialProblemType {
  CONSTANT(conf -> (x, y) -> 1),
  LINEAR(conf -> Double::sum),
  RADIAL((conf) -> {
    val radiusRatio = RADIAL_PROBLEM_RADIUS_RATIO.get(conf);
    val problemSize = PROBLEM_SIZE.get(conf);
    val valueBoost = RADIAL_PROBLEM_RADIUS_VALUE.get(conf);

    val radius = problemSize * radiusRatio / 2;
    val center = problemSize / 2;

    return RadialProblem.builder()
        .radius(radius)
        .center(center)
        .value(valueBoost * problemSize)
        .build();
  });

  Function<GiraphConfiguration, Problem> problem;

  InitialProblemType(Function<GiraphConfiguration, Problem> problem) {
    this.problem = problem;
  }

  public Problem createProblem(GiraphConfiguration configuration) {
    return problem.apply(configuration);
  }

}
