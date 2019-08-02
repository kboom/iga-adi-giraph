package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

public class BackwardsSubstituteInterimComputation extends AbstractIgaComputation {

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> iterable
  ) {

  }

}
