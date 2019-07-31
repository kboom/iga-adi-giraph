package edu.agh.iga.adi.giraph;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EdgeWritable implements WritableComparable {

  @Override
  public void write(DataOutput dataOutput) throws IOException {

  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {

  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

}
