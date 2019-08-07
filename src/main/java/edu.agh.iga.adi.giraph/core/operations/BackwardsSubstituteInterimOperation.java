package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

public final class BackwardsSubstituteInterimOperation implements IgaOperation<BackwardsSubstituteInterimMessage> {

  static final BackwardsSubstituteInterimOperation BACKWARDS_SUBSTITUTE_INTERIM_OPERATION
      = new BackwardsSubstituteInterimOperation();

  @Override
  public BackwardsSubstituteInterimMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteInterimMessage message, DirectionTree tree) {

  }

  public static class BackwardsSubstituteInterimMessage extends IgaMessage {

    public BackwardsSubstituteInterimMessage(long srcId) {
      super(srcId, BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
    }
  }

}
