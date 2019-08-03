package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

final class BackwardsSubstituteInterimOperation implements IgaOperation<BackwardsSubstituteInterimMessage> {

  private static final BackwardsSubstituteInterimOperation BACKWARDS_SUBSTITUTE_INTERIM_OPERATION
      = new BackwardsSubstituteInterimOperation();

  @Override
  public BackwardsSubstituteInterimMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteInterimMessage message) {

  }

  public static class BackwardsSubstituteInterimMessage extends IgaMessage {

    protected BackwardsSubstituteInterimMessage(long srcId, long dstId) {
      super(srcId, dstId, BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
    }
  }

}
