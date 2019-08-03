package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateRootOperation;
import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

final class MergeAndEliminateRootComputation extends IgaComputation<MergeAndEliminateRootMessage> {

  private static final MergeAndEliminateRootOperation OPERATION = new MergeAndEliminateRootOperation();

  MergeAndEliminateRootComputation() {
    super(OPERATION, MergeAndEliminateRootMessage.class);
  }

}
