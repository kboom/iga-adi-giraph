package edu.agh.iga.adi.giraph.direction.io.data;

import edu.agh.iga.adi.giraph.core.IgaMessage;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IgaMessageWritable implements WritableComparable {

  private IgaMessage igaMessage;

  public IgaMessageWritable(IgaMessage igaMessage) {
    this.igaMessage = igaMessage;
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

  public IgaMessage getIgaMessage() {
    return igaMessage;
  }

  @SuppressWarnings("unused")
  public IgaMessageWritable() {

  }

}
