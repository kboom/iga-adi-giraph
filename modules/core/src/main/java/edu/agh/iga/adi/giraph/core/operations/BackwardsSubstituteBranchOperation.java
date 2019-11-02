package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.*;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialBackwardsSubstitution;
import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.swapDofs;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class BackwardsSubstituteBranchOperation implements IgaOperation<BackwardsSubstituteBranchMessage> {

  public static final BackwardsSubstituteBranchOperation BACKWARDS_SUBSTITUTE_BRANCH_OPERATION
      = new BackwardsSubstituteBranchOperation();

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    return element.withMx(FACTORY.make(5, ctx.getMesh().getDofsX()));
  }

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
  public IgaElement postSend(IgaElement element, DirectionTree tree) {
    return null;
  }

  @Override
  public void consumeMessage(IgaElement element, BackwardsSubstituteBranchMessage message, DirectionTree tree) {
    element.mx.regionByLimits(5, (int) element.mx.countColumns()).regionByOffsets(1, 0).modifyMatching(ADD, message.mx);
  }

  @Override
  public void postConsume(IgaElement element, DirectionTree tree) {
    partialBackwardsSubstitution(element, 1, 5);
    swapDofs(element, 0, 1, 5);
    swapDofs(element, 1, 2, 5);
  }

  public static class BackwardsSubstituteBranchMessage extends IgaMessage {

    public final TransformableRegion<Double> mx;

    public BackwardsSubstituteBranchMessage(int srcId, TransformableRegion<Double> mx) {
      super(srcId, BACKWARDS_SUBSTITUTE_BRANCH_OPERATION);
      this.mx = mx;
    }

  }

  @Override
  public String toString() {
    return "BackwardsSubstituteBranchOperation";
  }

}
