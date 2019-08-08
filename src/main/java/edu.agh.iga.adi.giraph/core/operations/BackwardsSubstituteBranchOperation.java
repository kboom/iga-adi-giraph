package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

public final class BackwardsSubstituteBranchOperation implements IgaOperation<BackwardsSubstituteBranchMessage> {

  static final BackwardsSubstituteBranchOperation BACKWARDS_SUBSTITUTE_BRANCH_OPERATION
      = new BackwardsSubstituteBranchOperation();

  @Override
  public BackwardsSubstituteBranchMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteBranchMessage message, DirectionTree tree) {

  }

  @Override
  public void process(IgaElement element, DirectionTree tree) {
    
  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    public BackwardsSubstituteBranchMessage(long srcId) {
      super(srcId, BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
    }

  }

}
