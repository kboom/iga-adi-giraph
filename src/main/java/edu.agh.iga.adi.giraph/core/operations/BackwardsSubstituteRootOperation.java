package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

public final class BackwardsSubstituteRootOperation implements IgaOperation<BackwardsSubstituteRootMessage> {

  static final BackwardsSubstituteRootOperation BACKWARDS_SUBSTITUTE_ROOT_OPERATION
      = new BackwardsSubstituteRootOperation();

  @Override
  public BackwardsSubstituteRootMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteRootMessage message, DirectionTree tree) {

  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    public BackwardsSubstituteRootMessage(long srcId) {
      super(srcId, BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
    }

  }

}
