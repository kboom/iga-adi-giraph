package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateInterimOperation;
import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

final class MergeAndEliminateInterimComputation extends IgaComputation<MergeAndEliminateInterimMessage> {

  private static final MergeAndEliminateInterimOperation OPERATION = new MergeAndEliminateInterimOperation();

  MergeAndEliminateInterimComputation() {
    super(OPERATION, MergeAndEliminateInterimMessage.class);
  }

}
