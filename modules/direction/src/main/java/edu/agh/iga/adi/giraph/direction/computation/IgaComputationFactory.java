package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.factories.DefaultComputationFactory;
import org.apache.giraph.graph.Computation;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;

public final class IgaComputationFactory
    extends DefaultComputationFactory<LongWritable, IgaElementWritable, IgaOperationWritable> {

  @Override
  public Computation<LongWritable, IgaElementWritable, IgaOperationWritable, Writable, Writable> createComputation(
      ImmutableClassesGiraphConfiguration<LongWritable, IgaElementWritable, IgaOperationWritable> conf
  ) {
    return super.createComputation(conf);
  }

}
