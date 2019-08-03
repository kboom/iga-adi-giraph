package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

public final class BackwardsSubstituteInterimOperation implements IgaOperation<BackwardsSubstituteInterimMessage> {

  public static final BackwardsSubstituteInterimOperation BACKWARDS_SUBSTITUTE_INTERIM_OPERATION
      = new BackwardsSubstituteInterimOperation();

  @Override
  public Iterator<BackwardsSubstituteInterimMessage> sendMessages(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<BackwardsSubstituteInterimMessage> messages) {
    return null;
  }

  public static class BackwardsSubstituteInterimMessage extends IgaMessage {

    protected BackwardsSubstituteInterimMessage(long srcId, long dstId) {
      super(srcId, dstId, BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
    }
  }

}
