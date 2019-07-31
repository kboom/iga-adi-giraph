package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.computation.ComputationResolver;
import org.apache.giraph.master.DefaultMasterCompute;

/**
 * This encodes the IGA-ADI algorithm in a series of Supersteps.
 */
public class IgaComputation extends DefaultMasterCompute {

  private ComputationResolver computationResolver;

  @Override
  public final void compute () {
    setComputation(computationResolver.computationForStep(getSuperstep()));
  }

}
