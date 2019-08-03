package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

public final class BackwardsSubstituteBranchOperation implements IgaOperation<BackwardsSubstituteBranchMessage> {

  public static final BackwardsSubstituteBranchOperation BACKWARDS_SUBSTITUTE_BRANCH_OPERATION
      = new BackwardsSubstituteBranchOperation();

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<BackwardsSubstituteBranchMessage> messages) {
    return null;
  }

  @Override
  public Iterator<BackwardsSubstituteBranchMessage> sendMessages(IgaElement element) {
    return null;
  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    protected BackwardsSubstituteBranchMessage(long srcId, long dstId) {
      super(srcId, dstId, BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
    }

  }

}
