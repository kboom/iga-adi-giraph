package edu.agh.iga.adi.giraph.direction;

import com.google.common.collect.ImmutableMap;
import edu.agh.iga.adi.giraph.direction.io.IgaEdgeInputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.NoArgsConstructor;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.io.VertexInputFormat;
import org.apache.giraph.job.GiraphJob;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;
import java.util.Map;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.INITIALISATION_TYPE;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static java.lang.Integer.MAX_VALUE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.giraph.conf.GiraphConstants.*;

@NoArgsConstructor(access = PRIVATE)
public class IgaGiraphJobFactory {

  private static final Map<String, Class<? extends VertexInputFormat>> inputFormatsByInitType = ImmutableMap.of(
      SURFACE_PROBLEM.getType(), InMemoryStepInputFormat.class,
      COEFFICIENTS_PROBLEM.getType(), StepVertexInputFormat.class
  );

  public static GiraphJob igaJob(GiraphConfiguration configuration) {
    return doCreateJob(injectSolverConfiguration(configuration));
  }

  /*
   * https://giraph.apache.org/options.html
   */
  private static GiraphConfiguration injectSolverConfiguration(GiraphConfiguration conf) {
    conf.setMasterComputeClass(StepComputation.class);
    conf.setWorkerContextClass(IgaWorkerContext.class);
    conf.setEdgeInputFormatClass(IgaEdgeInputFormat.class);
    conf.setVertexInputFormatClass(inputFormatsByInitType.get(INITIALISATION_TYPE.get(conf)));
    conf.setVertexOutputFormatClass(StepVertexOutputFormat.class);
    conf.setGraphPartitionerFactoryClass(IgaPartitionerFactory.class);
    VERTEX_ID_CLASS.set(conf, LongWritable.class);
    VERTEX_VALUE_CLASS.set(conf, IgaElementWritable.class);
    EDGE_VALUE_CLASS.set(conf, IgaOperationWritable.class);
    OUTGOING_MESSAGE_VALUE_CLASS.set(conf, IgaMessageWritable.class);
    MAX_NUMBER_OF_SUPERSTEPS.set(conf, MAX_VALUE);
    IS_PURE_YARN_JOB.set(conf, true);
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
