package edu.agh.iga.adi.giraph.direction.core;

import edu.agh.iga.adi.giraph.direction.EmptyMessage;
import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.direction.core.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

final class BackwardsSubstituteBranchOperation implements IgaOperation<EmptyMessage, BackwardsSubstituteBranchMessage> {

  @Override
  public IgaElement consumeMessages(IgaElement element, Iterator<EmptyMessage> messages) {
    return null;
  }

  @Override
  public Iterator<BackwardsSubstituteBranchMessage> sendMessages(IgaElement element) {
    return null;
  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    protected BackwardsSubstituteBranchMessage(long srcId, long dstId) {
      super(srcId, dstId);
    }
  }

}
