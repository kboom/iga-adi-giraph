package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

final class BackwardsSubstituteRootOperation implements IgaOperation<BackwardsSubstituteRootMessage> {

  private static final BackwardsSubstituteRootOperation BACKWARDS_SUBSTITUTE_ROOT_OPERATION
      = new BackwardsSubstituteRootOperation();

  @Override
  public BackwardsSubstituteRootMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteRootMessage message) {

  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    protected BackwardsSubstituteRootMessage(long srcId, long dstId) {
      super(srcId, dstId, BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
    }
  }

}
