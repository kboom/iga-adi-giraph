package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BACKWARDS_SUBSTITUTE_BRANCH_OPERATION;

final class BackwardsSubstituteBranchComputation extends IgaComputation<BackwardsSubstituteBranchMessage> {

  BackwardsSubstituteBranchComputation() {
    super(BACKWARDS_SUBSTITUTE_BRANCH_OPERATION, BackwardsSubstituteBranchMessage.class);
  }

}
