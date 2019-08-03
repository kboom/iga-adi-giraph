package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

final class MergeAndEliminateBranchOperation implements IgaOperation<MergeAndEliminateBranchMessage> {

  private static final MergeAndEliminateBranchOperation INSTANCE
      = new MergeAndEliminateBranchOperation();

  @Override
  public MergeAndEliminateBranchMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateBranchMessage message) {

  }

  public static class MergeAndEliminateBranchMessage extends IgaMessage {

    protected MergeAndEliminateBranchMessage(long srcId, long dstId) {
      super(srcId, dstId, INSTANCE);
    }

  }

}
