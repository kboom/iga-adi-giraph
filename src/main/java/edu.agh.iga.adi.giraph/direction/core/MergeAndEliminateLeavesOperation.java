package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

public final class MergeAndEliminateLeavesOperation implements IgaOperation<MergeAndEliminateLeavesMessage> {

  @Override
  public Iterator<MergeAndEliminateLeavesMessage> sendMessages(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<MergeAndEliminateLeavesMessage> messages) {
    return null;
  }

  public static class MergeAndEliminateLeavesMessage extends IgaMessage {

    protected MergeAndEliminateLeavesMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
