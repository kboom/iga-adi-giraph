package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.function.constant.PrimitiveMath;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

/*
case object MergeAndEliminateRoot extends Production
  with BaseProduction[MergeAndEliminateRootMessage]
  with MergingProduction[MergeAndEliminateRootMessage] {

  override def emit(src: IgaElement, dst: IgaElement)(implicit ctx: IgaTaskContext): MergeAndEliminateRootMessage = (childPositionOf(src.v)(ctx.tree): @switch) match {
    case LEFT_CHILD => MergeAndEliminateRootMessage(
      MatrixFactory.ofDim(src.mA) {
        _ (0 until 4, 0 until 4) += src.mA(2 until 6, 2 until 6)
      },
      MatrixFactory.ofDim(src.mB) {
        _ (0 until 4, ::) += src.mB(2 until 6, ::)
      }
    )
    case RIGHT_CHILD => MergeAndEliminateRootMessage(
      MatrixFactory.ofDim(src.mA) {
        _ (2 until 6, 2 until 6) += src.mA(2 until 6, 2 until 6)
      },
      MatrixFactory.ofDim(src.mB) {
        _ (2 until 6, ::) += src.mB(2 until 6, ::)
      }
    )
  }

  override def merge(a: MergeAndEliminateRootMessage, b: MergeAndEliminateRootMessage): MergeAndEliminateRootMessage = MergeAndEliminateRootMessage(
    a.ca += b.ca,
    a.cb += b.cb
  )

  override def consume(dst: IgaElement, msg: MergeAndEliminateRootMessage)(implicit ctx: IgaTaskContext): Unit = {
    dst.mA += msg.ca
    dst.mB += msg.cb

    partialForwardElimination(6, 6)(dst)
    partialBackwardsSubstitution(6, 6)(dst)
  }
}
 */
public final class MergeAndEliminateRootOperation implements IgaOperation<MergeAndEliminateRootMessage> {

  public static final MergeAndEliminateRootOperation MERGE_AND_ELIMINATE_ROOT_OPERATION
      = new MergeAndEliminateRootOperation();

  @Override
  public MergeAndEliminateRootMessage sendMessage(IgaVertex dstId, IgaElement element) {
    return new MergeAndEliminateRootMessage(
        element.id,
        element.ma.regionByLimits(6, 6).regionByOffsets(2, 2),
        element.mb.regionByRows(2, 3, 4, 5)
    );
  }

  @Override
  public void consumeMessage(IgaElement element, MergeAndEliminateRootMessage message, DirectionTree tree) {
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
    partialForwardElimination(element, 6, 6);
    partialBackwardsSubstitution(element, 6, 6);
  }

  public static class MergeAndEliminateRootMessage extends IgaMessage {

    public final TransformableRegion<Double> ma;
    public final TransformableRegion<Double> mb;

    public MergeAndEliminateRootMessage(long srcId, TransformableRegion<Double> ma, TransformableRegion<Double> mb) {
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
