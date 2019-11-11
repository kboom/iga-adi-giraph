package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;


public final class BackwardsSubstituteInterimOperation implements IgaOperation<BackwardsSubstituteInterimMessage> {

  public static final BackwardsSubstituteInterimOperation BACKWARDS_SUBSTITUTE_INTERIM_OPERATION
      = new BackwardsSubstituteInterimOperation();

  private static final int[] LEFT_ROWS = {0, 1, 2, 3};
  private static final int[] RIGHT_ROWS = {2, 3, 4, 5};

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    return element.withMx(FACTORY.make(ROWS_BOUND_TO_NODE, ctx.getMesh().getDofsX()));
  }

  @Override
  public BackwardsSubstituteInterimMessage sendMessage(IgaVertex dstId, IgaElement element) {
    switch (dstId.childPosition()) {
      case LEFT:
        return new BackwardsSubstituteInterimMessage(
            element.id,
            element.mx.regionByRows(LEFT_ROWS)
        );
      case RIGHT:
        return new BackwardsSubstituteInterimMessage(
            element.id,
            element.mx.regionByRows(RIGHT_ROWS)
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
  public void consumeMessage(IgaElement element, BackwardsSubstituteInterimMessage message, DirectionTree tree) {
    element.mx.regionByOffsets(2, 0).modifyMatching(ADD, message.mx);
  }

  @Override
  public void postConsume(IgaElement element, DirectionTree tree) {
    partialBackwardsSubstitution(element, 2, 6);
    swapDofs(element, 0, 2, 6);
    swapDofs(element, 1, 3, 6);
  }

  public static class BackwardsSubstituteInterimMessage extends IgaMessage {

    public final TransformableRegion<Double> mx;

    public BackwardsSubstituteInterimMessage(int srcId, TransformableRegion<Double> mx) {
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
