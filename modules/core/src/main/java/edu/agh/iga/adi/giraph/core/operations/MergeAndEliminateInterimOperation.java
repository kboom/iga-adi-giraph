package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;


public final class MergeAndEliminateInterimOperation implements IgaOperation<MergeAndEliminateInterimMessage> {

  public static final MergeAndEliminateInterimOperation MERGE_AND_ELIMINATE_INTERIM_OPERATION
      = new MergeAndEliminateInterimOperation();

  @Override
  public MergeAndEliminateInterimMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return new MergeAndEliminateInterimMessage(
        element.id,
        element.ma.regionByLimits(6, 6).regionByOffsets(2, 2),
        element.mb.regionByRows(2, 3, 4, 5)
    );
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateInterimMessage message, DirectionTree tree) {
    switch (vertexOf(tree, message.getSrcId()).childPosition()) {
      case LEFT:
        element.ma.regionByLimits(4, 4).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(0, 1, 2, 3).modifyMatching(ADD, message.mb);
        break;
      case RIGHT:
        element.ma.regionByLimits(6, 6).regionByOffsets(2, 2).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(2, 3, 4, 5).modifyMatching(ADD, message.mb);
        break;
    }
  }

  @Override
  public void process(IgaElement element, DirectionTree tree) {
    swapDofs(element, 0, 2, 6);
    swapDofs(element, 1, 3, 6);
    partialForwardElimination(element, 2, 6);
  }

  public static class MergeAndEliminateInterimMessage extends IgaMessage {

    public final TransformableRegion<Double> ma;
    public final TransformableRegion<Double> mb;

    public MergeAndEliminateInterimMessage(long srcId, TransformableRegion<Double> ma, TransformableRegion<Double> mb) {
      super(srcId, MERGE_AND_ELIMINATE_INTERIM_OPERATION);
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
    return "MergeAndEliminateInterimOperation";
  }

}
