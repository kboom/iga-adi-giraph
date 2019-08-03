package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateBranchOperation;
import edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

final class MergeAndEliminateBranchComputation extends IgaComputation<MergeAndEliminateBranchMessage> {

  private static final MergeAndEliminateBranchOperation OPERATION = new MergeAndEliminateBranchOperation();

  MergeAndEliminateBranchComputation() {
    super(OPERATION, MergeAndEliminateBranchMessage.class);
  }

}
