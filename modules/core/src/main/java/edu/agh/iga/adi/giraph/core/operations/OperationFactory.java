package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.InterimVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.RootVertex;

import java.util.Optional;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BACKWARDS_SUBSTITUTE_BRANCH_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BACKWARDS_SUBSTITUTE_INTERIM_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BACKWARDS_SUBSTITUTE_ROOT_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MERGE_AND_ELIMINATE_BRANCH_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MERGE_AND_ELIMINATE_INTERIM_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MERGE_AND_ELIMINATE_ROOT_OPERATION;

public class OperationFactory {

  private OperationFactory() {
  }

  public static Optional<IgaOperation> operationFor(IgaVertex src, IgaVertex dst) {
    if (src.is(LeafVertex.class) && dst.is(BranchVertex.class)) {
      return Optional.of(MERGE_AND_ELIMINATE_LEAVES_OPERATION);
    }
    if (src.is(BranchVertex.class) && dst.is(InterimVertex.class)) {
      return Optional.of(MERGE_AND_ELIMINATE_BRANCH_OPERATION);
    }
    if (src.is(InterimVertex.class) && dst.is(RootVertex.class)) {
      return Optional.of(MERGE_AND_ELIMINATE_ROOT_OPERATION);
    }
    if (src.is(InterimVertex.class) && dst.is(InterimVertex.class)) {
      return src.after(dst) ? Optional.of(MERGE_AND_ELIMINATE_INTERIM_OPERATION) : Optional.of(BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
    }
    if (src.is(RootVertex.class) && dst.is(InterimVertex.class)) {
      return Optional.of(BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
    }
    if (src.is(InterimVertex.class) && dst.is(BranchVertex.class)) {
      return Optional.of(BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
    }
    return Optional.empty();
  }

}
