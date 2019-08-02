package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.computation.ComputationResolver;
import org.apache.giraph.master.DefaultMasterCompute;

/**
 * Computes a one direction of the Alternating Directions Solver.
 */
public class DirectionComputation extends DefaultMasterCompute {

  private ComputationResolver computationResolver;

  @Override
  public final void compute() {
    computationResolver.computationForStep(getSuperstep()).ifPresent(this::setComputation);
  }

}
