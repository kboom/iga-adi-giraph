package edu.agh.iga.adi.giraph.direction.io.data;

import org.ojalgo.structure.Access1D;

import java.io.DataInput;
import java.io.IOException;

public class DataInputAccessStore implements Access1D<Double> {

  private final DataInput dataInput;
  private final long count;

  private DataInputAccessStore(DataInput dataInput, long count) {
    this.dataInput = dataInput;
    this.count = count;
  }

  public static DataInputAccessStore dataInputAccessStore(DataInput dataInput, long count) {
    return new DataInputAccessStore(dataInput, count);
  }

  @Override
  public double doubleValue(long index) {
    // assume its sequential
    try {
      return dataInput.readDouble();
    } catch (IOException e) {
      throw new IllegalStateException("Could not read value", e);
    }
  }

  @Override
  public Double get(long index) {
    throw new UnsupportedOperationException("Does not cache the values so cannot seek to any index");
  }

  @Override
  public long count() {
    return count;
  }

}
