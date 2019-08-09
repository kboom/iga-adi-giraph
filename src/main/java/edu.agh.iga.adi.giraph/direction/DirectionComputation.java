package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.ComputationResolver;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.log4j.Logger;

import java.util.Optional;

import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;

/**
 * Computes a one direction of the Alternating Directions Solver.
 */
public class DirectionComputation extends DefaultMasterCompute {

  private static final Logger LOG = Logger.getLogger(DirectionComputation.class);

  private ComputationResolver computationResolver;

  @Override
  public void initialize() {
    int problemSize = PROBLEM_SIZE.get(getConf());
    computationResolver = new ComputationResolver(new DirectionTree(problemSize));
  }

  // alternatively org.apache.giraph.examples.scc.SccPhaseMasterCompute in giraph repo
  @Override
  public final void compute() {
    Optional<Class<? extends Computation>> nextComputation = computationResolver.computationForStep(getSuperstep());
    if (nextComputation.isPresent()) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Running next stage");
      }
      setComputation(nextComputation.get());
    } else {
      haltComputation();
    }
  }

}
