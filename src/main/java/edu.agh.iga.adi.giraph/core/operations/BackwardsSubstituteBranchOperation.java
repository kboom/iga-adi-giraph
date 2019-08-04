package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

public final class BackwardsSubstituteBranchOperation implements IgaOperation<BackwardsSubstituteBranchMessage> {

  static final BackwardsSubstituteBranchOperation BACKWARDS_SUBSTITUTE_BRANCH_OPERATION
      = new BackwardsSubstituteBranchOperation();

  @Override
  public BackwardsSubstituteBranchMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteBranchMessage message) {

  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    public BackwardsSubstituteBranchMessage(long srcId, long dstId) {
      super(srcId, dstId, BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
    }

  }

}
