package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.function.constant.PrimitiveMath;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

/*
case object BackwardsSubstituteBranch extends Production
  with BaseProduction[BackwardsSubstituteBranchMessage] {

  override def emit(src: IgaElement, dst: IgaElement)(implicit ctx: IgaTaskContext): BackwardsSubstituteBranchMessage = {
    (Vertex.childPositionOf(dst.v)(ctx.tree): @switch) match {
      case LEFT_CHILD => BackwardsSubstituteBranchMessage(
        MatrixFactory.ofDim(src.mX) {
          _ (1 until 5, ::) += src.mX(0 until 4, ::)
        }
      )
      case RIGHT_CHILD => BackwardsSubstituteBranchMessage(
        MatrixFactory.ofDim(src.mX) {
          _ (1 until 5, ::) += src.mX(2 until 6, ::)
        }
      )
    }
  }

  override def consume(dst: IgaElement, msg: BackwardsSubstituteBranchMessage)(implicit ctx: IgaTaskContext): Unit = {
    dst.mX :+= msg.cx
    partialBackwardsSubstitution(1, 5)(dst)
    swapDofs(0, 1, 5)(dst)
    swapDofs(1, 2, 5)(dst)
  }

}
 */
public final class BackwardsSubstituteBranchOperation implements IgaOperation<BackwardsSubstituteBranchMessage> {

  public static final BackwardsSubstituteBranchOperation BACKWARDS_SUBSTITUTE_BRANCH_OPERATION
      = new BackwardsSubstituteBranchOperation();

  @Override
  public BackwardsSubstituteBranchMessage sendMessage(IgaVertex dstId, IgaElement element) {
    switch (dstId.childPosition()) {
      case LEFT:
        return new BackwardsSubstituteBranchMessage(
            element.id,
            element.mx.regionByRows(0, 1, 2, 3)
        );
      case RIGHT:
        return new BackwardsSubstituteBranchMessage(
            element.id,
            element.mx.regionByRows(2, 3, 4, 5)
        );
      default:
        throw new IllegalStateException("Could not send message");
    }
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteBranchMessage message, DirectionTree tree) {
    element.mx.regionByLimits(5, (int) element.mx.countColumns()).regionByOffsets(1, 0).modifyMatching(ADD, message.mx);
  }

  @Override
  public void process(IgaElement element, DirectionTree tree) {
    partialBackwardsSubstitution(element, 1, 5);
    swapDofs(element, 0, 1, 5);
    swapDofs(element, 1, 2, 5);
  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    public final TransformableRegion<Double> mx;

    public BackwardsSubstituteBranchMessage(long srcId, TransformableRegion<Double> mx) {
      super(srcId, BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
      this.mx = mx;
    }

  }

  @Override
  public String toString() {
    return "BackwardsSubstituteBranchOperation";
  }

}
