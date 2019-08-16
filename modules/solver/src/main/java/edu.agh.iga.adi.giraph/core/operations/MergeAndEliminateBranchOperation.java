package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

/*
case object MergeAndEliminateBranch extends Production
  with BaseProduction[MergeAndEliminateBranchMessage]
  with MergingProduction[MergeAndEliminateBranchMessage] {

  override def emit(src: IgaElement, dst: IgaElement)(implicit ctx: IgaTaskContext): MergeAndEliminateBranchMessage = (childPositionOf(src.v)(ctx.tree): @switch) match {
    case LEFT_CHILD => MergeAndEliminateBranchMessage(
      MatrixFactory.ofDim(src.mA) {
        _ (0 until 4, 0 until 4) += src.mA(1 until 5, 1 until 5)
      },
      MatrixFactory.ofDim(src.mB) {
        _ (0 until 4, ::) += src.mB(1 until 5, ::)
      }
    )
    case RIGHT_CHILD => MergeAndEliminateBranchMessage(
      MatrixFactory.ofDim(src.mA) {
        _ (2 until 6, 2 until 6) += src.mA(1 until 5, 1 until 5)
      },
      MatrixFactory.ofDim(src.mB) {
        _ (2 until 6, ::) += src.mB(1 until 5, ::)
      }
    )
  }

  override def merge(a: MergeAndEliminateBranchMessage, b: MergeAndEliminateBranchMessage):
  MergeAndEliminateBranchMessage = MergeAndEliminateBranchMessage(
    a.ca += b.ca,
    a.cb += b.cb
  )

  override def consume(dst: IgaElement, msg: MergeAndEliminateBranchMessage)(implicit ctx: IgaTaskContext): Unit = {
    dst.mA += msg.ca
    dst.mB += msg.cb

    swapDofs(0, 2, 6)(dst)
    swapDofs(1, 3, 6)(dst)

    partialForwardElimination(2, 6)(dst)
  }
}
 */
public final class MergeAndEliminateBranchOperation implements IgaOperation<MergeAndEliminateBranchMessage> {

  public static final MergeAndEliminateBranchOperation MERGE_AND_ELIMINATE_BRANCH_OPERATION
      = new MergeAndEliminateBranchOperation();

  @Override
  public MergeAndEliminateBranchMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return new MergeAndEliminateBranchMessage(
        element.id,
        element.ma.regionByLimits(5, 5).regionByOffsets(1, 1),
        element.mb.regionByRows(1, 2, 3, 4)
    );
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
  public void process(IgaElement element, DirectionTree tree) {
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
