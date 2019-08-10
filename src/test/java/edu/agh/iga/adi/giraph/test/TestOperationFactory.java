package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaOperationGraph.DirectedOperation;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BACKWARDS_SUBSTITUTE_BRANCH_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BACKWARDS_SUBSTITUTE_INTERIM_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BACKWARDS_SUBSTITUTE_ROOT_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MERGE_AND_ELIMINATE_BRANCH_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MERGE_AND_ELIMINATE_INTERIM_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MERGE_AND_ELIMINATE_ROOT_OPERATION;

public final class TestOperationFactory {

  private final DirectionTree tree;

  public TestOperationFactory(DirectionTree tree) {
    this.tree = tree;
  }

  public DirectedOperation mergeAndEliminateLeaves(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_LEAVES_OPERATION);
  }

  public DirectedOperation mergeAndEliminateBranch(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_BRANCH_OPERATION);
  }

  public DirectedOperation mergeAndEliminateInterim(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_INTERIM_OPERATION);
  }

  public DirectedOperation mergeAndEliminateRoot(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_ROOT_OPERATION);
  }

  public DirectedOperation backwardsSubstituteRoot(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
  }

  public DirectedOperation backwardsSubstituteInterim(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
  }

  public DirectedOperation backwardsSubstituteBranch(long src, long dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
  }

}
