package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.io.IgaEdgeInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.NoArgsConstructor;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.job.GiraphJob;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;

import static java.lang.Integer.MAX_VALUE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.giraph.conf.GiraphConstants.*;

@NoArgsConstructor(access = PRIVATE)
public class IgaGiraphJobFactory {

  public static GiraphJob igaJob(GiraphConfiguration configuration) {
    return doCreateJob(injectSolverConfiguration(configuration));
  }

  private static GiraphConfiguration injectSolverConfiguration(GiraphConfiguration conf) {
    conf.setMasterComputeClass(StepComputation.class);
    conf.setWorkerContextClass(IgaWorkerContext.class);
    conf.setEdgeInputFormatClass(IgaEdgeInputFormat.class);
    conf.setVertexInputFormatClass(StepVertexInputFormat.class);
    conf.setVertexOutputFormatClass(StepVertexOutputFormat.class);
    conf.setGraphPartitionerFactoryClass(IgaPartitionerFactory.class);
    VERTEX_ID_CLASS.set(conf, LongWritable.class);
    VERTEX_VALUE_CLASS.set(conf, IgaElementWritable.class);
    EDGE_VALUE_CLASS.set(conf, IgaOperationWritable.class);
    OUTGOING_MESSAGE_VALUE_CLASS.set(conf, IgaMessageWritable.class);
    MAX_NUMBER_OF_SUPERSTEPS.set(conf, MAX_VALUE);
    return conf;
  }

  private static GiraphJob doCreateJob(GiraphConfiguration conf) {
    try {
      return new GiraphJob(conf, "test");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
