package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import lombok.val;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaConstants.COLS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class MergeAndEliminateBranchOperation implements IgaOperation<MergeAndEliminateBranchMessage> {

  public static final MergeAndEliminateBranchOperation MERGE_AND_ELIMINATE_BRANCH_OPERATION
      = new MergeAndEliminateBranchOperation();

  @Override
  public MergeAndEliminateBranchMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return new MergeAndEliminateBranchMessage(
        element.id,
        element.ma.regionByOffsets(1, 1),
        element.mb.regionByRows(1, 2, 3, 4)
    );
  }

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    val ma = FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    val mb = FACTORY.makeZero(ROWS_BOUND_TO_NODE, ctx.getMesh().getDofsX());
    return igaElement(vertex.id(), ma, mb, null);
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateBranchMessage message, DirectionTree tree) {
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
  public void postConsume(IgaElement element, DirectionTree tree) {
    swapDofs(element, 0, 2, 6);
    swapDofs(element, 1, 3, 6);
    partialForwardElimination(element, 2, 6);
  }

  public static class MergeAndEliminateBranchMessage extends IgaMessage {

    private final TransformableRegion<Double> ma;
    private final TransformableRegion<Double> mb;

    public MergeAndEliminateBranchMessage(long srcId, TransformableRegion<Double> ma, TransformableRegion<Double> mb) {
      super(srcId, MERGE_AND_ELIMINATE_BRANCH_OPERATION);
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
    return "MergeAndEliminateBranchOperation";
  }

}
