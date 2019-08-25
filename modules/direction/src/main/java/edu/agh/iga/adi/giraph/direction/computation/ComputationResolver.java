package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.apache.giraph.graph.Computation;

public final class ComputationResolver {

  public static Class<? extends Computation> computationForStep(DirectionTree tree, long step) {
    if (step == 0) {
      return InitialComputation.class;
    }
    if (step < 2 * tree.height()) {
      return FactorisationComputation.class;
    }
    if (step <= 2 * tree.height() + 1) {
      return TranspositionComputation.class;
    }
    return null;
  }

}
