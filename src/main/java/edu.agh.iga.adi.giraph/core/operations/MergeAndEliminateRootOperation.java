package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

public final class MergeAndEliminateRootOperation implements IgaOperation<MergeAndEliminateRootMessage> {

  static final MergeAndEliminateRootOperation MERGE_AND_ELIMINATE_ROOT_OPERATION
      = new MergeAndEliminateRootOperation();

  @Override
  public MergeAndEliminateRootMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateRootMessage message, DirectionTree tree) {

  }

  public static class MergeAndEliminateRootMessage extends IgaMessage {

    public MergeAndEliminateRootMessage(long srcId) {
      super(srcId, MERGE_AND_ELIMINATE_ROOT_OPERATION);
    }

  }

}
