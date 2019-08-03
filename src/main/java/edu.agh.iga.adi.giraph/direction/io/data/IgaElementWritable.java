package edu.agh.iga.adi.giraph.direction.io.data;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;

public final class IgaElementWritable implements WritableComparable {

  private IgaElement igaElement;

  public IgaElementWritable(IgaElement igaElement) {
    this.igaElement = igaElement;
  }

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
