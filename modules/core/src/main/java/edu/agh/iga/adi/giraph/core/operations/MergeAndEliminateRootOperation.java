package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import lombok.val;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaConstants.COLS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class MergeAndEliminateRootOperation implements IgaOperation<MergeAndEliminateRootMessage> {

  public static final MergeAndEliminateRootOperation MERGE_AND_ELIMINATE_ROOT_OPERATION
      = new MergeAndEliminateRootOperation();

  private static final int[] SEND_ROWS = {2, 3, 4, 5};
  private static final int[] RECEIVE_ROWS_LEFT = {0, 1, 2, 3};
  private static final int[] RECEIVE_ROWS_RIGHT = {2, 3, 4, 5};

  @Override
  public MergeAndEliminateRootMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return new MergeAndEliminateRootMessage(
        element.id,
        element.ma.regionByLimits(6, 6).regionByOffsets(2, 2),
        element.mb.regionByRows(SEND_ROWS)
    );
  }

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    val ma = FACTORY.make(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    val mb = FACTORY.make(ROWS_BOUND_TO_NODE, ctx.getMesh().getDofsX());
    val mx = FACTORY.make(ROWS_BOUND_TO_NODE, ctx.getMesh().getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateRootMessage message, DirectionTree tree) {
    switch (vertexOf(tree, message.getSrcId()).childPosition()) {
      case LEFT:
        element.ma.regionByLimits(4, 4).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(RECEIVE_ROWS_LEFT).modifyMatching(ADD, message.mb);
        break;
      case RIGHT:
        element.ma.regionByLimits(6, 6).regionByOffsets(2, 2).modifyMatching(ADD, message.ma);
        element.mb.regionByRows(RECEIVE_ROWS_RIGHT).modifyMatching(ADD, message.mb);
        break;
    }
  }

  @Override
  public void postConsume(IgaElement element, DirectionTree tree) {
    partialForwardElimination(element, 6, 6);
    partialBackwardsSubstitution(element, 6, 6);
  }

  public static class MergeAndEliminateRootMessage extends IgaMessage {

    public final TransformableRegion<Double> ma;
    public final TransformableRegion<Double> mb;

    public MergeAndEliminateRootMessage(int srcId, TransformableRegion<Double> ma, TransformableRegion<Double> mb) {
      super(srcId, MERGE_AND_ELIMINATE_ROOT_OPERATION);
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
    return "MergeAndEliminateRootOperation";
  }

}
