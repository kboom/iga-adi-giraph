package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

public final class MergeAndEliminateBranchOperation implements IgaOperation<MergeAndEliminateBranchMessage> {

  public static final MergeAndEliminateBranchOperation MERGE_AND_ELIMINATE_BRANCH_OPERATION
      = new MergeAndEliminateBranchOperation();

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
      super(srcId, dstId, MERGE_AND_ELIMINATE_BRANCH_OPERATION);
    }

  }

}
