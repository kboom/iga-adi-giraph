package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateLeavesOperation;
import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

final class MergeAndEliminateLeavesComputation extends IgaComputation<MergeAndEliminateLeavesMessage> {

  private static final MergeAndEliminateLeavesOperation OPERATION = new MergeAndEliminateLeavesOperation();

  MergeAndEliminateLeavesComputation() {
    super(OPERATION, MergeAndEliminateLeavesMessage.class);
  }

}
