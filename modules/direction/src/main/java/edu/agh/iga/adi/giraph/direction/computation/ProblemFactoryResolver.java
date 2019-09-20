package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.problem.NoopProblemFactory;
import edu.agh.iga.adi.giraph.core.problem.ProblemFactory;
import edu.agh.iga.adi.giraph.core.problem.phenomena.HeatTransferPhenomena;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;

import static edu.agh.iga.adi.giraph.core.problem.ProblemType.HEAT;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_TYPE;

@NoArgsConstructor
public class ProblemFactoryResolver {

  public static ProblemFactory getProblemFactory(GiraphConfiguration conf) {
    val problemType = PROBLEM_TYPE.get(conf);
    if (problemType == HEAT) {
      return new HeatTransferPhenomena.HeatTransferPhenomenaFactory();
    }
    return new NoopProblemFactory();
  }

}
