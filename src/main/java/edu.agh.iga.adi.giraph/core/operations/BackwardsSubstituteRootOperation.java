package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.*;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

/*
case object BackwardsSubstituteRoot extends Production
  with BaseProduction[BackwardsSubstituteRootMessage] {

  override def emit(src: IgaElement, dst: IgaElement)(implicit ctx: IgaTaskContext): BackwardsSubstituteRootMessage = {
    (Vertex.childPositionOf(dst.v)(ctx.tree): @switch) match {
      case LEFT_CHILD => BackwardsSubstituteRootMessage(
        MatrixFactory.ofDim(src.mX) {
          _ (2 to -1, ::) += src.mX(0 until 4, ::)
        }
      )
      case RIGHT_CHILD => BackwardsSubstituteRootMessage(
        MatrixFactory.ofDim(src.mX) {
          _ (2 to -1, ::) += src.mX(2 until 6, ::)
        }
      )
    }
  }

  override def consume(dst: IgaElement, msg: BackwardsSubstituteRootMessage)(implicit ctx: IgaTaskContext): Unit = {
    dst.mX += msg.cx

    partialBackwardsSubstitution(2, 6)(dst)
    swapDofs(0, 2, 6)(dst)
    swapDofs(1, 3, 6)(dst)
  }

}
 */
public final class BackwardsSubstituteRootOperation implements IgaOperation<BackwardsSubstituteRootMessage> {

  static final BackwardsSubstituteRootOperation BACKWARDS_SUBSTITUTE_ROOT_OPERATION
      = new BackwardsSubstituteRootOperation();

  @Override
  public BackwardsSubstituteRootMessage sendMessage(IgaVertex dstId, IgaElement element) {
    switch (dstId.childPosition()) {
      case LEFT:
        return new BackwardsSubstituteRootMessage(
            element.id,
            element.mx.regionByRows(0, 1, 2, 3)
        );
      case RIGHT:
        return new BackwardsSubstituteRootMessage(
            element.id,
            element.mx.regionByRows(2, 3, 4, 5)
        );
      default:
        throw new IllegalStateException("Could not send message");
    }
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteRootMessage message, DirectionTree tree) {
    element.ma.regionByOffsets(2, 0).modifyMatching(ADD, message.mx);
  }

  @Override
  public void process(IgaElement element, DirectionTree tree) {
    partialBackwardsSubstitution(element, 2, 6);
    swapDofs(element, 0, 2, 6);
    swapDofs(element, 1, 3, 6);
  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    public final TransformableRegion<Double> mx;

    public BackwardsSubstituteRootMessage(long srcId, TransformableRegion<Double> mx) {
      super(srcId, BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
      this.mx = mx;
    }

    public TransformableRegion<Double> getMx() {
      return mx;
    }

  }

}
