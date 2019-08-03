package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.EmptyMessage;
import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

final class BackwardsSubstituteInterimOperation implements IgaOperation<EmptyMessage, BackwardsSubstituteInterimMessage> {

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<EmptyMessage> messages) {
    return null;
  }

  @Override
  public Iterator<BackwardsSubstituteInterimMessage> sendMessages(IgaElement element) {
    return null;
  }

  public static class BackwardsSubstituteInterimMessage extends IgaMessage {

    protected BackwardsSubstituteInterimMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
