package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation;
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
    if (step == 2 * tree.height() + 2) {
      return InitialComputation.class;
    }
    if (step < 4 * tree.height() + 3) {
      return FactorisationComputation.class;
    }
    return null;
  }

}
