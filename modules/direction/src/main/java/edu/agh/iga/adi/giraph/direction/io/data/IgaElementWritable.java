package edu.agh.iga.adi.giraph.direction.io.data;

import edu.agh.iga.adi.giraph.core.IgaElement;
import lombok.val;
import org.apache.hadoop.io.WritableComparable;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public class IgaElementWritable implements WritableComparable {

  private static final int COEFFICIENTS_DEFINED = 1 << 1;
  private static final int RHS_DEFINED = 1 << 2;
  private static final int UNKNOWNS_DEFINED = 1 << 3;
  private static final int ELEMENT_NULL = 1 << 4;

  private IgaElement igaElement;

  public IgaElementWritable(IgaElement igaElement) {
    this.igaElement = igaElement;
  }

  @Override
  public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeInt(flagsOf(igaElement));

    if (igaElement == null) {
      return;
    }

    dataOutput.writeInt(igaElement.id);

    // we always hold the same number of rows for all matrices (if we store them)
    dataOutput.writeShort((short) firstNonNullMatrix(igaElement.ma, igaElement.mb, igaElement.mx).countRows());

    if (igaElement.ma != null) {
      // it is always a square matrix so there is no need to send other dimensions
      igaElement.ma.asCollectable1D().supplyTo(receiveInto(dataOutput));
    }

    if (igaElement.mb != null || igaElement.mx != null) {
      // we never shrink the columns of the matrices B and X and the row count is the same
      dataOutput.writeInt((int) firstNonNullMatrix(igaElement.mb, igaElement.mx).countColumns());

      if (igaElement.mb != null) {
        igaElement.mb.asCollectable1D().supplyTo(receiveInto(dataOutput));
      }

      if (igaElement.mx != null) {
        igaElement.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
      }
    }
  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {
    val flags = dataInput.readInt();

    if (elementNull(flags)) {
      return;
    }

    val srcId = dataInput.readInt();
    val rows = dataInput.readShort();

    val ma = coefficientsPresent(flags) ? readSquareMatrix(dataInput, rows) : null;

    if (rhsPresent(flags) || unknownsPresent(flags)) {
      val dofs = dataInput.readInt();
      val mb = rhsPresent(flags) ? readMatrix(dataInput, rows, dofs) : null;
      val mx = unknownsPresent(flags) ? readMatrix(dataInput, rows, dofs) : null;
      igaElement = igaElement(srcId, ma, mb, mx);
    } else {
      igaElement = igaElement(srcId, ma, null, null);
    }
  }

  private static PrimitiveDenseStore readSquareMatrix(DataInput dataInput, short elements) {
    PrimitiveDenseStore ma = FACTORY.makeZero(elements, elements);
    ma.fillMatching(dataInputAccessStore(dataInput, elements * elements));
    return ma;
  }

  private static PrimitiveDenseStore readMatrix(DataInput dataInput, short rows, int cols) {
    PrimitiveDenseStore ma = FACTORY.makeZero(rows, cols);
    ma.fillMatching(dataInputAccessStore(dataInput, rows * cols));
    return ma;
  }

  private static PrimitiveDenseStore firstNonNullMatrix(PrimitiveDenseStore... pds) {
    return Stream.of(pds)
        .filter(Objects::nonNull)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("None of the matrices were set"));
  }

  private static boolean coefficientsPresent(int bitmask) {
    return (bitmask & COEFFICIENTS_DEFINED) > 0;
  }

  private static boolean unknownsPresent(int bitmask) {
    return (bitmask & UNKNOWNS_DEFINED) > 0;
  }

  private static boolean rhsPresent(int bitmask) {
    return (bitmask & RHS_DEFINED) > 0;
  }

  private static boolean elementNull(int bitmask) {
    return (bitmask & ELEMENT_NULL) > 0;
  }

  private static int flagsOf(IgaElement igaElement) {
    if (igaElement == null) {
      return ELEMENT_NULL;
    } else {
      val coefficientsDefined = igaElement.ma != null ? COEFFICIENTS_DEFINED : 0;
      val rhsDefined = igaElement.mb != null ? RHS_DEFINED : 0;
      val unknownsDefined = igaElement.mx != null ? UNKNOWNS_DEFINED : 0;
      return coefficientsDefined | rhsDefined | unknownsDefined;
    }
  }

  @Override
  public int compareTo(Object o) {
    IgaElementWritable other = (IgaElementWritable) o;
    return igaElement.id - other.igaElement.id;
  }

  @SuppressWarnings("unused")
  public IgaElementWritable() {

  }

  public IgaElement getElement() {
    return igaElement;
  }

  public IgaElementWritable withValue(IgaElement igaElement) {
    this.igaElement = igaElement;
    return this;
  }

}
