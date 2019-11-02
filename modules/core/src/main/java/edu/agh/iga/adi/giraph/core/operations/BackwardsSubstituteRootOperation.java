package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class BackwardsSubstituteRootOperation implements IgaOperation<BackwardsSubstituteRootMessage> {

  public static final BackwardsSubstituteRootOperation BACKWARDS_SUBSTITUTE_ROOT_OPERATION
      = new BackwardsSubstituteRootOperation();

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    return element.withMx(FACTORY.make(ROWS_BOUND_TO_NODE, ctx.getMesh().getDofsX()));
  }

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
  public IgaElement postSend(IgaElement element, DirectionTree tree) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteRootMessage message, DirectionTree tree) {
    element.mx.regionByOffsets(2, 0).modifyMatching(ADD, message.mx);
  }

  @Override
  public void postConsume(IgaElement element, DirectionTree tree) {
    partialBackwardsSubstitution(element, 2, 6);
    swapDofs(element, 0, 2, 6);
    swapDofs(element, 1, 3, 6);
  }

  public static class BackwardsSubstituteRootMessage extends IgaMessage {

    public final TransformableRegion<Double> mx;

    public BackwardsSubstituteRootMessage(int srcId, TransformableRegion<Double> mx) {
      super(srcId, BACKWARDS_SUBSTITUTE_ROOT_OPERATION);
      this.mx = mx;
    }

  }

  @Override
  public String toString() {
    return "BackwardsSubstituteRootOperation";
  }

}
