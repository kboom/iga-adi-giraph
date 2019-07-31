package edu.agh.iga.adi.giraph.computation;

import edu.agh.iga.adi.giraph.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

public class MergeAndEliminateRootComputation extends AbstractIgaComputation {

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> iterable
  ) {

  }

}
