package edu.agh.iga.adi.giraph.core.operations.setup;

import edu.agh.iga.adi.giraph.core.*;
import lombok.Getter;
import lombok.val;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.core.IgaConstants.LEAF_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.IgaVertexType.vertexType;
import static edu.agh.iga.adi.giraph.core.operations.setup.TranspositionIgaOperation.TranspositionIgaMessage;
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
      return new TranspositionIgaMessage(element.id, columns.regionByRows(2, 3, 4));
    }
  }

  @Override
  public IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    val ma = FACTORY.makeZero(LEAF_SIZE, LEAF_SIZE);
    ctx.getMethodCoefficients().coefficients().supplyTo(ma);
    return igaElement(
        vertex.id(),
        ma,
        FACTORY.makeZero(LEAF_SIZE, ctx.getMesh().getDofsY()),
        null
    );
  }

  @Override
  public void consumeMessage(IgaElement element, TranspositionIgaMessage message, DirectionTree tree) {
    val srcId = (int) message.getSrcId();
    val dstId = element.id;
    val mxp = message.mxp;

    val srcVType = vertexType(tree, srcId);

    val mo = (int) srcVType.offsetLeft(tree, srcId);
    val pp = new PartitionProvider(dstId, tree, (int) mxp.countRows());

    val targetBlock = element.mb
        .regionByRows(0, 1, 2)
        .regionByOffsets(0, min(1, mo) * 5 + (max(1, mo) - 1) * 3)
        .regionByLimits(3, mo + (int) mxp.countRows());

    targetBlock.fillMatching(pp, MULTIPLY, mxp.regionByTransposing());
  }

  private boolean isLeading(IgaVertex dst, IgaElement element) {
    return vertexType(dst.getTree(), element.id).isLeading(dst.getTree(), element.id);
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
