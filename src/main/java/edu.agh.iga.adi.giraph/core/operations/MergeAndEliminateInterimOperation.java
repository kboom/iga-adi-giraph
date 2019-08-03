package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

public final class MergeAndEliminateInterimOperation implements IgaOperation<MergeAndEliminateInterimMessage> {

  public static final MergeAndEliminateInterimOperation MERGE_AND_ELIMINATE_INTERIM_OPERATION
      = new MergeAndEliminateInterimOperation();

  @Override
  public Iterator<MergeAndEliminateInterimMessage> sendMessage(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessage(IgaElement element, Iterator<MergeAndEliminateInterimMessage> messages) {
    return null;
  }

  public static class MergeAndEliminateInterimMessage extends IgaMessage {

    protected MergeAndEliminateInterimMessage(long srcId, long dstId) {
      super(srcId, dstId, MERGE_AND_ELIMINATE_INTERIM_OPERATION);
    }

  }

}
