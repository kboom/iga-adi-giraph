package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.EmptyMessage;
import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

final class MergeAndEliminateLeavesOperation implements IgaOperation<EmptyMessage, MergeAndEliminateLeavesMessage> {

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<EmptyMessage> messages) {
    return null;
  }

  @Override
  public Iterator<MergeAndEliminateLeavesMessage> sendMessages(IgaElement element) {
    return null;
  }

  public static class MergeAndEliminateLeavesMessage extends IgaMessage {

    protected MergeAndEliminateLeavesMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
