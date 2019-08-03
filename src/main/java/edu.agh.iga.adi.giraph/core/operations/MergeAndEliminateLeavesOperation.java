package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

/**
 * Merges 3 leaf elements and eliminates 2 fully assembled rows at their parent.
 */
final class MergeAndEliminateLeavesOperation implements IgaOperation<MergeAndEliminateLeavesMessage> {

  private static final MergeAndEliminateLeavesOperation INSTANCE
      = new MergeAndEliminateLeavesOperation();

  @Override
  public MergeAndEliminateLeavesMessage sendMessage(long dstId, IgaElement element) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateLeavesMessage message) {

  }

  public static class MergeAndEliminateLeavesMessage extends IgaMessage {

    protected MergeAndEliminateLeavesMessage(long srcId, long dstId) {
      super(srcId, dstId, INSTANCE);
    }
  }

}
