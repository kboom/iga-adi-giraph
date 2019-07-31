package edu.agh.iga.adi.giraph.computation;

import edu.agh.iga.adi.giraph.DirectionTree;
import org.apache.giraph.graph.Computation;

public final class ComputationResolver {

  private final DirectionTree tree;

  public ComputationResolver(DirectionTree tree) {
    this.tree = tree;
  }

  public Class<? extends Computation> computationForStep(long step) {
    final int treeHeight = tree.height();
    if (step == 0) {
      return MergeAndEliminateLeavesComputation.class;
    }
    if (step == 1) {
      return MergeAndEliminateBranchingComputation.class;
    }
    if (step < treeHeight - 1) {
      return MergeAndEliminateBranchingComputation.class;
    }
    if (step == treeHeight) {
      return MergeAndEliminateRootComputation.class;
    }
    if (step < 2 * treeHeight - 1) {
      return BackwardsSubstituteInterimComputation.class;
    }
    if (step == 2 * treeHeight - 1) {
      return BackwardsSubstituteBranchComputation.class;
    }
    throw new IllegalStateException("There cannot be more steps than two times the height of the problem tree");
  }

}
