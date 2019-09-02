package edu.agh.iga.adi.giraph.core.operations.transposition;

import edu.agh.iga.adi.giraph.core.*;
import lombok.Getter;
import lombok.val;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaConstants.LEAF_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients.COEFFICIENTS;
import static edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TranspositionIgaMessage;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.ojalgo.function.constant.PrimitiveMath.MULTIPLY;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class TranspositionIgaOperation implements IgaOperation<TranspositionIgaMessage> {

  public static final TranspositionIgaOperation TRANSPOSITION_IGA_OPERATION
      = new TranspositionIgaOperation();

  @Override
  public TranspositionIgaMessage sendMessage(IgaVertex dst, IgaElement element) {
    val leftOffset = (int) dst.offsetLeft();
    val columns = element.mx.regionByColumns(leftOffset, leftOffset + 1, leftOffset + 2);
    if (isLeading(dst, element)) {
      return new TranspositionIgaMessage(element.id, columns.regionByRows(0, 1, 2, 3, 4));
    } else {
      return new TranspositionIgaMessage(element.id, columns.regionByRows(1, 2, 3));
    }
  }

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    val ma = FACTORY.makeZero(LEAF_SIZE, LEAF_SIZE);
    COEFFICIENTS.supplyTo(ma);
    return igaElement(
        vertex.id(),
        ma,
        FACTORY.makeZero(LEAF_SIZE, ctx.getMesh().getDofsX()),
        null
    );
  }

  @Override
  public void consumeMessage(IgaElement element, TranspositionIgaMessage message, DirectionTree tree) {
    val srcId = (int) message.getSrcId();
    val mxp = message.mxp;
    val src = vertexOf(tree, srcId);
    val dst = vertexOf(tree, element.id);
    val mo = (int) src.offsetLeft();
    val pp = new PartitionProvider(dst, (int) mxp.countRows());
    val targetBlock = element.mb
        .regionByRows(0, 1, 2)
        .regionByOffsets(0, min(1, mo) * 5 + (max(1, mo) - 1) * 3)
        .regionByLimits(3, mo + (int) mxp.countRows());

    targetBlock.fillMatching(pp, MULTIPLY, mxp.regionByTransposing());
  }

  private boolean isLeading(IgaVertex dst, IgaElement element) {
    return vertexOf(dst.getTree(), element.id).offsetLeft() == 0;
  }

  public static class TranspositionIgaMessage extends IgaMessage {

    @Getter
    public final TransformableRegion<Double> mxp;

    public TranspositionIgaMessage(long srcId, TransformableRegion<Double> mxp) {
      super(srcId, TRANSPOSITION_IGA_OPERATION);
      this.mxp = mxp;
    }

  }

}
