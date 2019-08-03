package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteInterimOperation;
import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

final class BackwardsSubstituteInterimComputation extends IgaComputation<BackwardsSubstituteInterimMessage> {

  private static final BackwardsSubstituteInterimOperation OPERATION = new BackwardsSubstituteInterimOperation();

  BackwardsSubstituteInterimComputation() {
    super(OPERATION, BackwardsSubstituteInterimMessage.class);
  }

}
