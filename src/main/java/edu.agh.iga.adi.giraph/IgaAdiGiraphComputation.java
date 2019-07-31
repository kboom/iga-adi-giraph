package edu.agh.iga.adi.giraph;

import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

public class IgaAdiGiraphComputation extends BasicComputation<LongWritable, VertexWritable, EdgeWritable, MessageWritable> {

  @Override
  public void compute(Vertex<LongWritable, VertexWritable, EdgeWritable> vertex, Iterable<MessageWritable> messages) {
    vertex.voteToHalt();
  }

}
