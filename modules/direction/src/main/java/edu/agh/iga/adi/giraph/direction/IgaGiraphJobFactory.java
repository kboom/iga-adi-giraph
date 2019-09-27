package edu.agh.iga.adi.giraph.direction;

import com.google.common.collect.ImmutableMap;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import edu.agh.iga.adi.giraph.direction.io.IgaEdgeInputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.direction.performance.MemoryLogger;
import lombok.NoArgsConstructor;
import org.apache.giraph.comm.messages.InMemoryMessageStoreFactory;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.edge.ByteArrayEdges;
import org.apache.giraph.io.VertexInputFormat;
import org.apache.giraph.job.GiraphJob;
import org.apache.hadoop.io.LongWritable;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.FIRST_INITIALISATION_TYPE;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.currentTimeMillis;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.giraph.conf.GiraphConstants.*;

@NoArgsConstructor(access = PRIVATE)
public class IgaGiraphJobFactory {

  private static final Map<String, Class<? extends VertexInputFormat>> inputFormatsByInitType = ImmutableMap.of(
      SURFACE_PROBLEM.getType(), InMemoryStepInputFormat.class,
      COEFFICIENTS_PROBLEM.getType(), StepVertexInputFormat.class
  );

  public static GiraphJob igaMapReduceJob(GiraphConfiguration configuration) {
    try {
      return new GiraphJob(injectSolverConfiguration(configuration), "iga-adi-" + new Timestamp(currentTimeMillis()));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Based on org.apache.giraph.conf.FacebookConfiguration
   * @param conf
   * @return
   */
  public static GiraphConfiguration injectSolverConfiguration(GiraphConfiguration conf) {
    conf.setComputationClass(InitialComputation.class);
    conf.setMasterComputeClass(IterativeComputation.class);
    conf.setWorkerContextClass(IgaWorkerContext.class);
    conf.setEdgeInputFormatClass(IgaEdgeInputFormat.class);
    conf.setVertexInputFormatClass(inputFormatsByInitType.get(FIRST_INITIALISATION_TYPE.get(conf)));
    conf.setVertexOutputFormatClass(StepVertexOutputFormat.class);
    conf.setGraphPartitionerFactoryClass(IgaPartitionerFactory.class);
    conf.addWorkerObserverClass(MemoryLogger.class);
    conf.setYarnLibJars(currentJar());

    NETTY_CLIENT_THREADS.set(conf, conf.getMaxWorkers());
    CLIENT_SEND_BUFFER_SIZE.set(conf, ONE_MB);
    CLIENT_RECEIVE_BUFFER_SIZE.set(conf, ONE_MB);
    NETTY_SERVER_THREADS.set(conf, 4 * conf.getMaxWorkers());
    SERVER_SEND_BUFFER_SIZE.set(conf, ONE_MB);
    SERVER_RECEIVE_BUFFER_SIZE.set(conf, ONE_MB);
    MAX_MSG_REQUEST_SIZE.set(conf, ONE_MB);
    REQUEST_SIZE_WARNING_THRESHOLD.set(conf, 1);
    MAX_VERTEX_REQUEST_SIZE.set(conf, ONE_MB);
    MAX_EDGE_REQUEST_SIZE.set(conf, ONE_MB);

    // todo does it help?
    USE_MESSAGE_SIZE_ENCODING.set(conf, true);
    
    // todo out edge classes with custom typeops
    conf.setOutEdgesClass(ByteArrayEdges.class);
//    conf.setOutEdgesClass(IdAndValueArrayEdges.class); // todo custom {@link TypeOps}

    conf.setDoOutputDuringComputation(true); // to support multiple steps, we're not using checkpoints, we can just restart the job where we left off from the last step (load saved coefficients)
    VERTEX_OUTPUT_FORMAT_THREAD_SAFE.set(conf, false); // is not thread safe

    // todo message store
    MESSAGE_STORE_FACTORY_CLASS.set(conf, InMemoryMessageStoreFactory.class);
    ASYNC_MESSAGE_STORE_THREADS_COUNT.set(conf, 8);


    STATIC_GRAPH.set(conf, true);
    VERTEX_ID_CLASS.set(conf, LongWritable.class);
    VERTEX_VALUE_CLASS.set(conf, IgaElementWritable.class);
    EDGE_VALUE_CLASS.set(conf, IgaOperationWritable.class);
    OUTGOING_MESSAGE_VALUE_CLASS.set(conf, IgaMessageWritable.class);
    MAX_NUMBER_OF_SUPERSTEPS.set(conf, MAX_VALUE);
    USE_SUPERSTEP_COUNTERS.set(conf, false); // todo enable this for detailed breakdown of computation times per superstep
    USE_INPUT_SPLIT_LOCALITY.set(conf, true);
    NETTY_USE_DIRECT_MEMORY.set(conf, true);
    NETTY_USE_POOLED_ALLOCATOR.set(conf, true);
    WAIT_TASK_DONE_TIMEOUT_MS.set(conf, 0); // no need to wait
    HDFS_FILE_CREATION_RETRY_WAIT_MS.set(conf, 100);
    return conf;
  }

  private static String currentJar() {
    return new File(IgaGiraphJobFactory.class.getProtectionDomain()
        .getCodeSource()
        .getLocation()
        .getPath()).getName();
  }

}
