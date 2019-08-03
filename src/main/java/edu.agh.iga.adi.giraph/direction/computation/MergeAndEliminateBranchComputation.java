package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateBranchOperation.MERGE_AND_ELIMINATE_BRANCH_OPERATION;

final class MergeAndEliminateBranchComputation extends IgaComputation<MergeAndEliminateBranchMessage> {

  MergeAndEliminateBranchComputation() {
    super(MERGE_AND_ELIMINATE_BRANCH_OPERATION, MergeAndEliminateBranchMessage.class);
  }

}
