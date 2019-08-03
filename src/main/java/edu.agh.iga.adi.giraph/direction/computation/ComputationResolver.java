package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.apache.giraph.graph.Computation;

import java.util.Optional;

public final class ComputationResolver {

  private final DirectionTree tree;

  public ComputationResolver(DirectionTree tree) {
    this.tree = tree;
  }

  public Optional<Class<? extends Computation>> computationForStep(long step) {
    final int treeHeight = tree.height();
    if (step == 0) {
      return Optional.of(InitialComputation.class);
    }
    if (step < 2 * treeHeight) {
      return Optional.of(IgaComputation.class);
    }
    return Optional.empty();
  }

}
