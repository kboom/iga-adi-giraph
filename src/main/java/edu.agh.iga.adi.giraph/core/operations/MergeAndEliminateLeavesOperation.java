package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

/**
 * Merges 3 leaf elements and eliminates 2 fully assembled rows at their parent.
 */
public final class MergeAndEliminateLeavesOperation implements IgaOperation<MergeAndEliminateLeavesMessage> {

  public static final MergeAndEliminateLeavesOperation MERGE_AND_ELIMINATE_LEAVES_OPERATION
      = new MergeAndEliminateLeavesOperation();

  @Override
  public Iterator<MergeAndEliminateLeavesMessage> sendMessage(IgaElement element) {
    return null;
  }

  @Override
  public IgaElement consumeMessage(IgaElement element, Iterator<MergeAndEliminateLeavesMessage> messages) {
    return null;
  }

  public static class MergeAndEliminateLeavesMessage extends IgaMessage {

    protected MergeAndEliminateLeavesMessage(long srcId, long dstId) {
      super(srcId, dstId, MERGE_AND_ELIMINATE_LEAVES_OPERATION);
    }
  }

}
