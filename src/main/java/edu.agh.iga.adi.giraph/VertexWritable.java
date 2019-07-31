package edu.agh.iga.adi.giraph;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;

public final class VertexWritable implements WritableComparable {

  @Override
  public void write(DataOutput dataOutput) {

  }

  @Override
  public void readFields(DataInput dataInput) {

  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

}
