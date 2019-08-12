package edu.agh.iga.adi.giraph.direction.io;

import org.apache.hadoop.mapreduce.InputSplit;

public class IgaInputSplit extends InputSplit {

  private long root;
  private int height;

  public IgaInputSplit(long root, int height) {
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

  public long getRoot() {
    return root;
  }

  public int getHeight() {
    return height;
  }
}
