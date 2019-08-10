package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

/*
case object BackwardsSubstituteInterim extends Production
  with BaseProduction[BackwardsSubstituteInterimMessage] {

  override def emit(src: IgaElement, dst: IgaElement)(implicit ctx: IgaTaskContext): BackwardsSubstituteInterimMessage = {
    (Vertex.childPositionOf(dst.v)(ctx.tree): @switch) match {
      case LEFT_CHILD => BackwardsSubstituteInterimMessage(
        MatrixFactory.ofDim(src.mX) {
          _ (2 to -1, ::) += src.mX(0 until 4, ::)
        }
      )
      case RIGHT_CHILD => BackwardsSubstituteInterimMessage(
        MatrixFactory.ofDim(src.mX) {
          _ (2 to -1, ::) += src.mX(2 until 6, ::)
        }
      )
    }
  }

  override def consume(dst: IgaElement, msg: BackwardsSubstituteInterimMessage)(implicit ctx: IgaTaskContext): Unit = {
    dst.mX :+= msg.cx

    // this probably should not be here, as this breaks encapsulation
    if(dst.v.isInstanceOf[BranchVertex]) {
      partialBackwardsSubstitution(2, 6)(dst)
      swapDofs(0, 2, 6)(dst)
      swapDofs(1, 3, 6)(dst)
    } else {
      partialBackwardsSubstitution(2, 6)(dst)
      swapDofs(0, 2, 6)(dst)
      swapDofs(1, 3, 6)(dst)
    }
  }

}
 */
public final class BackwardsSubstituteInterimOperation implements IgaOperation<BackwardsSubstituteInterimMessage> {

  static final BackwardsSubstituteInterimOperation BACKWARDS_SUBSTITUTE_INTERIM_OPERATION
      = new BackwardsSubstituteInterimOperation();

  @Override
  public BackwardsSubstituteInterimMessage sendMessage(IgaVertex dstId, IgaElement element) {
    switch (dstId.childPosition()) {
      case LEFT:
        return new BackwardsSubstituteInterimMessage(
            element.id,
            element.mx.regionByRows(0, 1, 2, 3)
        );
      case RIGHT:
        return new BackwardsSubstituteInterimMessage(
            element.id,
            element.mx.regionByRows(2, 3, 4, 5)
        );
      default:
        throw new IllegalStateException("Could not send message");
    }
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteInterimMessage message, DirectionTree tree) {
    element.mx.regionByOffsets(2, 0).modifyMatching(ADD, message.mx);
  }

  @Override
  public void process(IgaElement element, DirectionTree tree) {
    final IgaVertex vertex = vertexOf(tree, element.id);
    // todo is this really necessary? These two branches are the same!
    if (vertex.is(BranchVertex.class)) {
      partialBackwardsSubstitution(element, 2, 6);
      swapDofs(element, 0, 2, 6);
      swapDofs(element, 1, 3, 6);
    } else {
      partialBackwardsSubstitution(element, 2, 6);
      swapDofs(element, 0, 2, 6);
      swapDofs(element, 1, 3, 6);
    }
  }

  public static class BackwardsSubstituteInterimMessage extends IgaMessage {

    public final TransformableRegion<Double> mx;

    public BackwardsSubstituteInterimMessage(long srcId, TransformableRegion<Double> mx) {
      super(srcId, BACKWARDS_SUBSTITUTE_INTERIM_OPERATION);
      this.mx = mx;
    }

    public TransformableRegion<Double> getMx() {
      return mx;
    }

  }

  @Override
  public String toString() {
    return "BackwardsSubstituteInterimOperation";
  }

}
