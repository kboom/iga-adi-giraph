package edu.agh.iga.adi.giraph.direction.io.data;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.apache.hadoop.io.WritableComparable;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.core.IgaConstants.COLS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

public final class IgaElementWritable implements WritableComparable {

  private IgaElement igaElement;

  public IgaElementWritable(IgaElement igaElement) {
    this.igaElement = igaElement;
  }

  @Override
  public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeLong(igaElement.id);
    dataOutput.writeInt((int) igaElement.mb.countColumns());
    igaElement.ma.asCollectable1D().supplyTo(receiveInto(dataOutput));
    igaElement.mb.asCollectable1D().supplyTo(receiveInto(dataOutput));
    igaElement.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dofs = dataInput.readInt();
    PrimitiveDenseStore ma = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    ma.fillMatching(dataInputAccessStore(dataInput, ROWS_BOUND_TO_NODE * COLS_BOUND_TO_NODE));
    PrimitiveDenseStore mb = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, ROWS_BOUND_TO_NODE * dofs));
    PrimitiveDenseStore mx = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, dofs);
    mx.fillMatching(dataInputAccessStore(dataInput, ROWS_BOUND_TO_NODE * dofs));
    igaElement = igaElement(srcId, ma, mb, mx);
  }

  @Override
  public int compareTo(Object o) {
    IgaElementWritable other = (IgaElementWritable) o;
    return (int) (igaElement.id - other.igaElement.id);
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
