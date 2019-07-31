package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.computation.ComputationResolver;
import org.apache.giraph.master.DefaultMasterCompute;

/**
 * Computes a one direction of the Alternating Directions Solver.
 */
public class DirectionComputation extends DefaultMasterCompute {

  private ComputationResolver computationResolver;

  @Override
  public final void compute () {
    setComputation(computationResolver.computationForStep(getSuperstep()));
  }

}
