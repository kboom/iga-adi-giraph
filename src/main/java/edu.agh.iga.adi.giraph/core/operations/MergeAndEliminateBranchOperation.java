package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

public final class MergeAndEliminateBranchOperation implements IgaOperation<MergeAndEliminateBranchMessage> {

  static final MergeAndEliminateBranchOperation MERGE_AND_ELIMINATE_BRANCH_OPERATION
      = new MergeAndEliminateBranchOperation();

  @Override
  public MergeAndEliminateBranchMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateBranchMessage message, DirectionTree tree) {

  }

  @Override
  public void process(IgaElement element) {

  }

  public static class MergeAndEliminateBranchMessage extends IgaMessage {

    public MergeAndEliminateBranchMessage(long srcId) {
      super(srcId, MERGE_AND_ELIMINATE_BRANCH_OPERATION);
    }

  }

}
