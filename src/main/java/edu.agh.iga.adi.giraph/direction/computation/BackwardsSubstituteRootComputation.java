package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BACKWARDS_SUBSTITUTE_ROOT_OPERATION;

final class BackwardsSubstituteRootComputation extends IgaComputation<BackwardsSubstituteRootMessage> {

  BackwardsSubstituteRootComputation() {
    super(BACKWARDS_SUBSTITUTE_ROOT_OPERATION, BackwardsSubstituteRootMessage.class);
  }

}
