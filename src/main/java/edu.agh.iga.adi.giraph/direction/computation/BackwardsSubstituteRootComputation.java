package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteRootOperation;
import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

final class BackwardsSubstituteRootComputation extends IgaComputation<BackwardsSubstituteRootMessage> {

  private static final BackwardsSubstituteRootOperation OPERATION = new BackwardsSubstituteRootOperation();

  BackwardsSubstituteRootComputation() {
    super(OPERATION, BackwardsSubstituteRootMessage.class);
  }

}
