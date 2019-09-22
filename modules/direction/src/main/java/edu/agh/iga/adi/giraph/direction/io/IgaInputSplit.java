package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;

public class IgaInputSplit extends InputSplit implements Writable {

  private static final String[] EMPTY_LOCATION = new String[0];

  private IgaVertex root;
  private int height;

  public IgaInputSplit(IgaVertex root, int height) {
    this.root = root;
    this.height = height;
  }

  @Override
  public long getLength() {
    return 0;
  }

  @Override
  public String[] getLocations() {
    return EMPTY_LOCATION;
  }

  public IgaVertex getRoot() {
    return root;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IgaInputSplit that = (IgaInputSplit) o;
    return height == that.height &&
        Objects.equals(root, that.root);
  }

  @Override
  public int hashCode() {
    return Objects.hash(root, height);
  }

  @Override
  public String toString() {
    return root.toString() + "/" + height;
  }

  @Override
  public void write(DataOutput dataOutput) throws IOException {
    dataOutput.writeInt(root.getTree().getProblemSize());
    dataOutput.writeLong(root.id());
    dataOutput.writeInt(height);
  }

  @Override
  public void readFields(DataInput dataInput) throws IOException {
    final int problemSize = dataInput.readInt();
    final long rootId = dataInput.readLong();
    root = vertexOf(new DirectionTree(problemSize), rootId);
    height = dataInput.readInt();
  }

  @SuppressWarnings("unused")
  public IgaInputSplit() {
  }

}
