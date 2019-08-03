package edu.agh.iga.adi.giraph.direction.io.data;

import edu.agh.iga.adi.giraph.core.IgaOperation;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IgaOperationWritable implements WritableComparable {

  private IgaOperation igaOperation;

  public IgaOperationWritable(IgaOperation igaOperation) {
    this.igaOperation = igaOperation;
  }

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

  public IgaOperation getIgaOperation() {
    return igaOperation;
  }
}
