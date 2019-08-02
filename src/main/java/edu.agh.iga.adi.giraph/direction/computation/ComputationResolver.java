package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.DirectionTree;
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
      return Optional.of(MergeAndEliminateLeavesComputation.class);
    }
    if (step == 1) {
      return Optional.of(MergeAndEliminateBranchingComputation.class);
    }
    if (step < treeHeight - 1) {
      return Optional.of(MergeAndEliminateBranchingComputation.class);
    }
    if (step == treeHeight) {
      return Optional.of(MergeAndEliminateRootComputation.class);
    }
    if (step < 2 * treeHeight - 1) {
      return Optional.of(BackwardsSubstituteInterimComputation.class);
    }
    if (step == 2 * treeHeight - 1) {
      return Optional.of(BackwardsSubstituteBranchComputation.class);
    }
    return Optional.empty();
  }

}
