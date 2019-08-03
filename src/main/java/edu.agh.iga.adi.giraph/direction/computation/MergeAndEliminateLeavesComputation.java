package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;

final class MergeAndEliminateLeavesComputation extends IgaComputation<MergeAndEliminateLeavesMessage> {

  MergeAndEliminateLeavesComputation() {
    super(MERGE_AND_ELIMINATE_LEAVES_OPERATION, MergeAndEliminateLeavesMessage.class);
  }

}
