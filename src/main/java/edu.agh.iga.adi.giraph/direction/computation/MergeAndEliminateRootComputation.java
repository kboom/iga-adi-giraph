package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateRootOperation.MERGE_AND_ELIMINATE_ROOT_OPERATION;

final class MergeAndEliminateRootComputation extends IgaComputation<MergeAndEliminateRootMessage> {

  MergeAndEliminateRootComputation() {
    super(MERGE_AND_ELIMINATE_ROOT_OPERATION, MergeAndEliminateRootMessage.class);
  }

}
