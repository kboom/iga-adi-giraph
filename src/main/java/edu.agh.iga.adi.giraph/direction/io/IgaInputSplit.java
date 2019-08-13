package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.IgaVertex;
import org.apache.hadoop.mapreduce.InputSplit;

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
}
