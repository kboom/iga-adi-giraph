package edu.agh.iga.adi.giraph;

import org.apache.giraph.graph.Vertex;

import java.util.Iterator;

public final class GraphPartition implements Iterator<Vertex<VertexIdWritable, VertexWritable, EdgeWritable>> {

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Vertex<VertexIdWritable, VertexWritable, EdgeWritable> next() {
    return null;
  }

}
