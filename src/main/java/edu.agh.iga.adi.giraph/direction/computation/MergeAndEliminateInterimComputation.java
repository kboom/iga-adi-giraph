package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateInterimOperation.MERGE_AND_ELIMINATE_INTERIM_OPERATION;

final class MergeAndEliminateInterimComputation extends IgaComputation<MergeAndEliminateInterimMessage> {

  MergeAndEliminateInterimComputation() {
    super(MERGE_AND_ELIMINATE_INTERIM_OPERATION, MergeAndEliminateInterimMessage.class);
  }

}
