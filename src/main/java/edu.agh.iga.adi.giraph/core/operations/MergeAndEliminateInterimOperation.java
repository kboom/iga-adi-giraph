package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

public final class MergeAndEliminateInterimOperation implements IgaOperation<MergeAndEliminateInterimMessage> {

  static final MergeAndEliminateInterimOperation MERGE_AND_ELIMINATE_INTERIM_OPERATION
      = new MergeAndEliminateInterimOperation();

  @Override
  public MergeAndEliminateInterimMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateInterimMessage message) {

  }

  public static class MergeAndEliminateInterimMessage extends IgaMessage {

    public MergeAndEliminateInterimMessage(long srcId, long dstId) {
      super(srcId, dstId, MERGE_AND_ELIMINATE_INTERIM_OPERATION);
    }

  }

}
