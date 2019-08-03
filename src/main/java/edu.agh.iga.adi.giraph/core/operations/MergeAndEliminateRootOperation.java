package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

final class MergeAndEliminateRootOperation implements IgaOperation<MergeAndEliminateRootMessage> {

  private static final MergeAndEliminateRootOperation MERGE_AND_ELIMINATE_ROOT_OPERATION
      = new MergeAndEliminateRootOperation();

  @Override
  public MergeAndEliminateRootMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateRootMessage message) {

  }

  public static class MergeAndEliminateRootMessage extends IgaMessage {

    protected MergeAndEliminateRootMessage(long srcId, long dstId) {
      super(srcId, dstId, MERGE_AND_ELIMINATE_ROOT_OPERATION);
    }
  }

}
