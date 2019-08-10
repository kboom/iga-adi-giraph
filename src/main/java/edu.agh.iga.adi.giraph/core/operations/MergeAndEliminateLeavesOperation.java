package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

/**
 * Merges 3 leaf elements and eliminates 2 fully assembled rows at their parent.
 */
/*

case LEFT_CHILD => MergeAndEliminateLeafMessage(
      ofDim(src.mA) {
        _ (0 until 3, 0 until 3) += src.mA(0 until 3, 0 until 3)
      },
      ofDim(src.mB) {
        _ (0 until 3, ::) += src.mB(0 until 3, ::)
      }
    )
    case MIDDLE_CHILD => MergeAndEliminateLeafMessage(
      ofDim(src.mA) {
        _ (1 until 4, 1 until 4) += src.mA(0 until 3, 0 until 3)
      },
      ofDim(src.mB) {
        _ (1 until 4, ::) += src.mB(0 until 3, ::)
      }
    )
    case RIGHT_CHILD => MergeAndEliminateLeafMessage(
      ofDim(src.mA) {
        _ (2 until 5, 2 until 5) += src.mA(0 until 3, 0 until 3)
      },
      ofDim(src.mB) {
        _ (2 until 5, ::) += src.mB(0 until 3, ::)
      }
    )

 */
public final class MergeAndEliminateLeavesOperation implements IgaOperation<MergeAndEliminateLeavesMessage> {

  static final MergeAndEliminateLeavesOperation MERGE_AND_ELIMINATE_LEAVES_OPERATION
      = new MergeAndEliminateLeavesOperation();

  @Override
  public MergeAndEliminateLeavesMessage sendMessage(IgaVertex dst, IgaElement element) {
    return new MergeAndEliminateLeavesMessage(
        element.id,
        element.ma.regionByLimits(3, 3), // todo is this really inclusive/inclusive?
        element.mb.regionByRows(0, 1, 2) // todo is this really inclusive/inclusive?
    );
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateLeavesMessage message, DirectionTree tree) {
    switch (vertexOf(tree, message.getSrcId()).childPosition()) {
      case LEFT:
        element.ma.regionByLimits(3, 3).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(0, 1, 2).modifyMatching(ADD, message.mb);
        break;
      case MIDDLE:
        element.ma.regionByLimits(4, 4).regionByOffsets(1, 1).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(1, 2, 3).modifyMatching(ADD, message.mb);
        break;
      case RIGHT:
        element.ma.regionByLimits(5, 5).regionByOffsets(2, 2).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(2, 3, 4).modifyMatching(ADD, message.mb);
        break;
    }
  }

  @Override
  public void process(IgaElement element, DirectionTree tree) {
    swapDofs(element, 0, 2, 5);
    swapDofs(element, 1, 2, 5);
    partialForwardElimination(element, 1, 5);
  }

  public static class MergeAndEliminateLeavesMessage extends IgaMessage {

    public final TransformableRegion<Double> ma;
    public final TransformableRegion<Double> mb;

    public MergeAndEliminateLeavesMessage(long srcId, TransformableRegion<Double> ma, TransformableRegion<Double> mb) {
      super(srcId, MERGE_AND_ELIMINATE_LEAVES_OPERATION);
      this.ma = ma;
      this.mb = mb;
    }

    public TransformableRegion<Double> getMa() {
      return ma;
    }

    public TransformableRegion<Double> getMb() {
      return mb;
    }

  }

  @Override
  public String toString() {
    return "MergeAndEliminateLeavesOperation";
  }

}
