package edu.agh.iga.adi.giraph.core.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaOperationFactory.DirectedOperation;

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

  public DirectedOperation mergeAndEliminateLeaves(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_LEAVES_OPERATION);
  }

  public DirectedOperation mergeAndEliminateBranch(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_BRANCH_OPERATION);
  }

  public DirectedOperation mergeAndEliminateInterim(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_INTERIM_OPERATION);
  }

  public DirectedOperation mergeAndEliminateRoot(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), MERGE_AND_ELIMINATE_ROOT_OPERATION);
  }

  public DirectedOperation backwardsSubstituteRoot(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
  }

  public DirectedOperation backwardsSubstituteInterim(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
  }

  public DirectedOperation backwardsSubstituteBranch(int src, int dst) {
    return new DirectedOperation(vertexOf(tree, src), vertexOf(tree, dst), BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
  }

}
