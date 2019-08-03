package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

final class MergeAndEliminateRootOperation implements IgaOperation<MergeAndEliminateRootMessage> {

  @Override
  public Iterator<MergeAndEliminateRootMessage> sendMessages(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<MergeAndEliminateRootMessage> messages) {
    return null;
  }

  public static class MergeAndEliminateRootMessage extends IgaMessage {

    protected MergeAndEliminateRootMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
