package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

public final class BackwardsSubstituteRootOperation implements IgaOperation<BackwardsSubstituteRootMessage> {

  @Override
  public Iterator<BackwardsSubstituteRootMessage> sendMessages(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<BackwardsSubstituteRootMessage> messages) {
    return null;
  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    protected BackwardsSubstituteRootMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
