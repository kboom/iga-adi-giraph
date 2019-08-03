package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

import static edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteInterimOperation.BACKWARDS_SUBSTITUTE_INTERIM_OPERATION;

final class BackwardsSubstituteInterimComputation extends IgaComputation<BackwardsSubstituteInterimMessage> {

  BackwardsSubstituteInterimComputation() {
    super(BACKWARDS_SUBSTITUTE_INTERIM_OPERATION, BackwardsSubstituteInterimMessage.class);
  }

}
