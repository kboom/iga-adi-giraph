package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;


public final class BackwardsSubstituteInterimOperation implements IgaOperation<BackwardsSubstituteInterimMessage> {

  public static final BackwardsSubstituteInterimOperation BACKWARDS_SUBSTITUTE_INTERIM_OPERATION
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
  public void postConsume(IgaElement element, DirectionTree tree) {
    partialBackwardsSubstitution(element, 2, 6);
    swapDofs(element, 0, 2, 6);
    swapDofs(element, 1, 3, 6);
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
