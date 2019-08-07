package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

public final class MergeAndEliminateInterimOperation implements IgaOperation<MergeAndEliminateInterimMessage> {

  static final MergeAndEliminateInterimOperation MERGE_AND_ELIMINATE_INTERIM_OPERATION
      = new MergeAndEliminateInterimOperation();

  @Override
  public MergeAndEliminateInterimMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateInterimMessage message, DirectionTree tree) {

  }

  public static class MergeAndEliminateInterimMessage extends IgaMessage {

    public MergeAndEliminateInterimMessage(long srcId) {
      super(srcId, MERGE_AND_ELIMINATE_INTERIM_OPERATION);
    }

  }

}
