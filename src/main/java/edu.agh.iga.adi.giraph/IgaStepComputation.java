package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.io.data.IgaMessageWritable;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

public class IgaStepComputation extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  @Override
  public void compute(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, Iterable<IgaMessageWritable> messages) {
    vertex.voteToHalt();
  }

}
