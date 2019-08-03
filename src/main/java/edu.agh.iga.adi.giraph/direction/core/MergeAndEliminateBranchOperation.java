package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

public final class MergeAndEliminateBranchOperation implements IgaOperation<MergeAndEliminateBranchMessage> {

  @Override
  public Iterator<MergeAndEliminateBranchMessage> sendMessages(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<MergeAndEliminateBranchMessage> messages) {
    return null;
  }

  public static class MergeAndEliminateBranchMessage extends IgaMessage {

    protected MergeAndEliminateBranchMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }

  }

}
