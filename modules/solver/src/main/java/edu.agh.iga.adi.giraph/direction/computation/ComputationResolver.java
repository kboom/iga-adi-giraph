package edu.agh.iga.adi.giraph.direction.computation;

import org.apache.giraph.graph.Computation;

public final class ComputationResolver {

  public static Class<? extends Computation> computationForStep(long step) {
    if (step == 0) {
      return InitialComputation.class;
    }
    return IgaComputation.class;
  }

}
