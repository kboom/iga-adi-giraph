package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

/**
 * Kicks off the cascade of operations at the leaf vertices.
 * All vertices are voted to halt so that only the messages wake them up.
 */
public final class InitialComputation
    extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    final IgaElement element = vertex.getValue().getElement();
    vertex.getEdges().forEach(edge -> {
      LongWritable dstId = edge.getTargetVertexId();
      sendMessage(dstId, new IgaMessageWritable(edge.getValue().getIgaOperation().sendMessage(dstId.get(), element)));
    });
    vertex.voteToHalt();
  }

}
