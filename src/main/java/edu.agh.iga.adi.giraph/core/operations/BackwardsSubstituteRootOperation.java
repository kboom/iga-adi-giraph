package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

public final class BackwardsSubstituteRootOperation implements IgaOperation<BackwardsSubstituteRootMessage> {

  public static final BackwardsSubstituteRootOperation BACKWARDS_SUBSTITUTE_ROOT_OPERATION
      = new BackwardsSubstituteRootOperation();

  @Override
  public Iterator<BackwardsSubstituteRootMessage> sendMessage(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessage(IgaElement element, Iterator<BackwardsSubstituteRootMessage> messages) {
    return null;
  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    protected BackwardsSubstituteRootMessage(long srcId, long dstId) {
      super(srcId, dstId, BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
    }
  }

}
