package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.EmptyMessage;
import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

final class BackwardsSubstituteRootOperation implements IgaOperation<EmptyMessage, BackwardsSubstituteRootMessage> {

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<EmptyMessage> messages) {
    return null;
  }

  @Override
  public Iterator<BackwardsSubstituteRootMessage> sendMessages(IgaElement element) {
    return null;
  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    protected BackwardsSubstituteRootMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
