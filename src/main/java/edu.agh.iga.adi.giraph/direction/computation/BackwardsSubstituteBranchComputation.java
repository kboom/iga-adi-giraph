package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteBranchOperation;
import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

final class BackwardsSubstituteBranchComputation extends IgaComputation<BackwardsSubstituteBranchMessage> {

  private static final BackwardsSubstituteBranchOperation OPERATION = new BackwardsSubstituteBranchOperation();

  BackwardsSubstituteBranchComputation() {
    super(OPERATION, BackwardsSubstituteBranchMessage.class);
  }

}
