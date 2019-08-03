package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

final class BackwardsSubstituteBranchOperation implements IgaOperation<BackwardsSubstituteBranchMessage> {

  private static final BackwardsSubstituteBranchOperation INSTANCE
      = new BackwardsSubstituteBranchOperation();

  @Override
  public BackwardsSubstituteBranchMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteBranchMessage message) {

  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    protected BackwardsSubstituteBranchMessage(long srcId, long dstId) {
      super(srcId, dstId, INSTANCE);
    }

  }

}
