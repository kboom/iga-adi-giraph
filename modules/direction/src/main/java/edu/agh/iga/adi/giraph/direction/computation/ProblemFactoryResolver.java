package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.problem.NoopProblemFactory;
import edu.agh.iga.adi.giraph.core.problem.ProblemFactory;
import edu.agh.iga.adi.giraph.core.problem.phenomena.HeatTransferPhenomena.HeatTransferPhenomenaFactory;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;

import static edu.agh.iga.adi.giraph.direction.ContextFactory.meshOf;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_TYPE;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.STEP_DELTA;

@NoArgsConstructor
public class ProblemFactoryResolver {

  public static ProblemFactory getProblemFactory(GiraphConfiguration conf) {
    return getProblemFactory(conf, 0);
  }

  public static ProblemFactory getProblemFactory(GiraphConfiguration conf, int step) {
    val problemType = PROBLEM_TYPE.get(conf);
    switch (problemType) {
      case PROJECTION:
        return new NoopProblemFactory();
      case HEAT:
        return HeatTransferPhenomenaFactory.builder()
            .mesh(meshOf(conf))
            .step(step)
            .delta(STEP_DELTA.get(conf))
            .build();
    }
    return new NoopProblemFactory();
  }

}
