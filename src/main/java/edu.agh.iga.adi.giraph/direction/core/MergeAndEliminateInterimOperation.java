package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

final class MergeAndEliminateInterimOperation implements IgaOperation<MergeAndEliminateInterimMessage> {

  @Override
  public Iterator<MergeAndEliminateInterimMessage> sendMessages(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<MergeAndEliminateInterimMessage> messages) {
    return null;
  }

  public static class MergeAndEliminateInterimMessage extends IgaMessage {

    protected MergeAndEliminateInterimMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }

  }

}
