package edu.agh.iga.adi.giraph.direction.io.data;

import org.ojalgo.function.NullaryFunction;
import org.ojalgo.structure.Access1D;
import org.ojalgo.structure.Mutate1D;

import java.io.DataOutput;
import java.io.IOException;

import static java.lang.Long.MAX_VALUE;

public final class DataOutputReceiver implements Mutate1D.Receiver<Number> {

  private final DataOutput dataOutput;

  private DataOutputReceiver(DataOutput dataOutput) {
    this.dataOutput = dataOutput;
  }

  public static DataOutputReceiver receiveInto(DataOutput output) {
    return new DataOutputReceiver(output);
  }

  @Override
  public void add(long index, double addend) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void add(long index, Number addend) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void set(long index, double value) {
    try {
      dataOutput.writeDouble(value);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write");
    }
  }

  @Override
  public void set(long index, Number value) {
    try {
      dataOutput.writeDouble(value.doubleValue());
    } catch (IOException e) {
      throw new IllegalStateException("Could not write");
    }
  }

  @Override
  public void fillOne(long index, Access1D<?> values, long valueIndex) {
    try {
      dataOutput.writeDouble(values.doubleValue(index));
    } catch (IOException e) {
      throw new IllegalStateException("Could not write");
    }
  }

  @Override
  public void fillOne(long index, Number value) {
    try {
      dataOutput.writeDouble(value.doubleValue());
    } catch (IOException e) {
      throw new IllegalStateException("Could not write");
    }
  }

  @Override
  public void fillOne(long index, NullaryFunction<Number> supplier) {
    try {
      dataOutput.writeDouble(supplier.doubleValue());
    } catch (IOException e) {
      throw new IllegalStateException("Could not write");
    }
  }

  @Override
  public long count() {
    return MAX_VALUE;
  }

}
