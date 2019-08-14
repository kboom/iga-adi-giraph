package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.IgaVertex;
import org.apache.hadoop.mapreduce.InputSplit;

import java.util.Objects;

public class IgaInputSplit extends InputSplit {

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
    return new String[0];
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
}
