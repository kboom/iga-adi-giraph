package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

public final class MergeAndEliminateRootOperation implements IgaOperation<MergeAndEliminateRootMessage> {

  public static final MergeAndEliminateRootOperation MERGE_AND_ELIMINATE_ROOT_OPERATION
      = new MergeAndEliminateRootOperation();

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
      super(srcId, dstId, MERGE_AND_ELIMINATE_ROOT_OPERATION);
    }
  }

}
