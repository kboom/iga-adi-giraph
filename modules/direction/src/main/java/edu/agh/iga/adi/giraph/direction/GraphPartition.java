package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import java.util.Iterator;

public final class GraphPartition implements Iterator<Vertex<LongWritable, IgaElementWritable, IgaOperationWritable>> {

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> next() {
    return null;
  }

}
