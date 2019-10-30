package edu.agh.iga.adi.giraph.direction.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import edu.agh.iga.adi.giraph.core.problem.ProblemType;
import edu.agh.iga.adi.giraph.direction.IgaPartitionerFactory;
import edu.agh.iga.adi.giraph.direction.IgaWorkerContext;
import edu.agh.iga.adi.giraph.direction.IterativeComputation;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers;
import edu.agh.iga.adi.giraph.direction.computation.InitialProblemType;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import edu.agh.iga.adi.giraph.direction.io.IgaEdgeInputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.direction.performance.MemoryLogger;
import lombok.val;
import org.apache.giraph.comm.flow_control.StaticFlowControl;
import org.apache.giraph.conf.*;
import org.apache.giraph.io.VertexInputFormat;
import org.apache.giraph.partition.ByteArrayPartition;
import org.apache.giraph.worker.MemoryObserver;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.io.Files.createTempDir;
import static edu.agh.iga.adi.giraph.core.problem.ProblemType.PROJECTION;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.InitialProblemType.CONSTANT;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.giraph.comm.messages.MessageEncodeAndStoreType.BYTEARRAY_PER_PARTITION;
import static org.apache.giraph.comm.netty.NettyClient.LIMIT_NUMBER_OF_OPEN_REQUESTS;
import static org.apache.giraph.conf.GiraphConstants.*;
import static org.apache.giraph.master.BspServiceMaster.NUM_MASTER_ZK_INPUT_SPLIT_THREADS;
import static org.apache.giraph.partition.PartitionBalancer.PARTITION_BALANCE_ALGORITHM;
import static org.apache.giraph.partition.PartitionBalancer.STATIC_BALANCE_ALGORITHM;
import static org.apache.log4j.Logger.getLogger;

public class IgaConfiguration {

  private static final Logger LOG = getLogger(IgaConfiguration.class);

  public static final BooleanConfOption CONFIGURE_JAVA_OPTS = new BooleanConfOption("giraph.configureJavaOpts", true, "Whether to configure java opts");

  public static final BooleanConfOption STORE_SOLUTION = new BooleanConfOption("iga.storeSolution", true, "Whether to store the solution or not.");

  public static final BooleanConfOption USE_G1_COLLECTOR = new BooleanConfOption("iga.useG1", false, "Use G1GC " +
      "collector"); // it crashes the solver for larger computation sizes

  public static final IntConfOption WORKER_CORES = new IntConfOption("iga.cores", 1, "The number of cores per worker");
  public static final IntConfOption WORKER_MEMORY = new IntConfOption("iga.memory", 1, "The amount of memory per worker in gigabytes");

  public static final FloatConfOption NEW_GEN_MEMORY_FRACTION = new FloatConfOption("iga.newGenMemoryFraction", 0.1f, "Fraction of total mapper memory to use for new generation");
  public static final FloatConfOption CORES_FRACTION_DURING_COMMUNICATION = new FloatConfOption("iga" +
      ".coresFractionDuringCommunication", 1f, "Fraction of mapper cores to use for threads which overlap with" +
      " network communication");

  public static final StrConfOption JAVA_JOB_OPTIONS = new StrConfOption("container.java.opts", null, "Java options " +
      "passed " +
      "to the workers");

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");
  public static final EnumConfOption<ProblemType> PROBLEM_TYPE = new EnumConfOption<>("iga.problem.type", ProblemType.class, PROJECTION, "The type of the problem to simulate");
  public static final EnumConfOption<InitialProblemType> INITIAL_PROBLEM_TYPE = new EnumConfOption<>("iga.problem.initial.type", InitialProblemType.class, CONSTANT, "The type of the initial surface to generate");
  public static final FloatConfOption STEP_DELTA = new FloatConfOption("iga.step.delta", 0.000000001f, "The length of the time step");
  public static final IntConfOption STEP_COUNT = new IntConfOption("iga.problem.steps", 1, "The number of steps to run");
  public static final IntConfOption HEIGHT_PARTITIONS = new IntConfOption("iga.tree.partition.size", 1, "The height of tree partitions");
  public static final StrConfOption FIRST_INITIALISATION_TYPE = new StrConfOption("iga.initialisation.type", SURFACE_PROBLEM.getType(), "The type of initialisation - " + resolverTypes() + " - use surface if you initialise the leaves or coefficients if you initialize the branches");
  public static final StrConfOption COEFFICIENTS_INPUT = new StrConfOption("giraph.vertex.input.dir", createTempDir().getPath(), "The (HDFS) directory to read the coefficients from");
  public static final StrConfOption COEFFICIENTS_OUTPUT = new StrConfOption("mapred.output.dir", createTempDir().getPath(), "The (HDFS) directory to put the coefficients to");
  public static final StrConfOption ZK_DIR = new StrConfOption("giraph.zkDir", createTempDir().getPath(), "The zookeeper directory to put coefficients to");

  private static String resolverTypes() {
    return Stream.of(IgaComputationResolvers.values()).map(IgaComputationResolvers::getType).collect(joining(","));
  }

  private static final Map<String, Class<? extends VertexInputFormat>> inputFormatsByInitType = ImmutableMap.of(
      SURFACE_PROBLEM.getType(), InMemoryStepInputFormat.class,
      COEFFICIENTS_PROBLEM.getType(), StepVertexInputFormat.class
  );

  public static GiraphConfiguration igaConfiguration(GiraphConfiguration conf) {
    LOG.info("Configuring giraph");

    solverOptions(conf);
    generalTuning(conf);
//    USE_MESSAGE_SIZE_ENCODING.set(conf, true); // todo not sure about this
    resiliencySettings(conf);

    HDFS_FILE_CREATION_RETRY_WAIT_MS.set(conf, 1000);

//    USE_MESSAGE_SIZE_ENCODING.set(conf, true);
    // todo out edge classes with custom typeops
//    conf.setOutEdgesClass(ByteArrayEdges.class);

    // todo message store
//    MESSAGE_STORE_FACTORY_CLASS.set(conf, InMemoryMessageStoreFactory.class); // already creates
//    LongByteArrayMessageStore by default
    setPartitioning(conf);
    return conf;
  }

  private static void setPartitioning(GiraphConfiguration conf) {
    // Seems that no re-balancing is required
    conf.setIfUnset(PARTITION_BALANCE_ALGORITHM, STATIC_BALANCE_ALGORITHM);
    conf.setGraphPartitionerFactoryClass(IgaPartitionerFactory.class);
  }

  private static void resiliencySettings(GiraphConfiguration conf) {
    WAIT_TASK_DONE_TIMEOUT_MS.setIfUnset(conf, (int) MINUTES.toMillis(1));
    MAX_MASTER_SUPERSTEP_WAIT_MSECS.setIfUnset(conf, (int) MINUTES.toMillis(1));
    MAX_REQUEST_MILLISECONDS.setIfUnset(conf, (int) MINUTES.toMinutes(15));
    NETTY_MAX_CONNECTION_FAILURES.setIfUnset(conf, 1000);
    WAIT_TIME_BETWEEN_CONNECTION_RETRIES_MS.setIfUnset(conf, 100);
    conf.setIfUnset("giraph.resendTimedOutRequests", "true");
    conf.setIfUnset("giraph.waitForOtherWorkersMsec", valueOf(MINUTES.toMillis(60)));
  }

  private static void solverOptions(GiraphConfiguration conf) {
    conf.setComputationClass(InitialComputation.class);
    conf.setMasterComputeClass(IterativeComputation.class);
    conf.setWorkerContextClass(IgaWorkerContext.class);
    conf.setEdgeInputFormatClass(IgaEdgeInputFormat.class);
    conf.setVertexInputFormatClass(inputFormatsByInitType.get(FIRST_INITIALISATION_TYPE.get(conf)));
    conf.setVertexOutputFormatClass(StepVertexOutputFormat.class);
    conf.addWorkerObserverClass(MemoryLogger.class);
    conf.setPartitionClass(ByteArrayPartition.class);
    conf.setYarnLibJars(currentJar());
    STATIC_GRAPH.set(conf, true);
    VERTEX_ID_CLASS.set(conf, IntWritable.class);
    VERTEX_VALUE_CLASS.set(conf, IgaElementWritable.class);
    EDGE_VALUE_CLASS.set(conf, IgaOperationWritable.class);
    OUTGOING_MESSAGE_VALUE_CLASS.set(conf, IgaMessageWritable.class);
    MAX_NUMBER_OF_SUPERSTEPS.set(conf, MAX_VALUE);
    USE_SUPERSTEP_COUNTERS.set(conf, false);
    conf.setDoOutputDuringComputation(true); // to support multiple steps, we're not using checkpoints, we can just restart the job where we left off from the last step (load saved coefficients)
    VERTEX_OUTPUT_FORMAT_THREAD_SAFE.set(conf, false); // is not thread safe
  }

  private static void generalTuning(GiraphConfiguration conf) {
    int workers = conf.getInt(MIN_WORKERS, -1);
    int cores = WORKER_CORES.get(conf);
    int workerMemoryGB = WORKER_MEMORY.get(conf);

    ASYNC_MESSAGE_STORE_THREADS_COUNT.setIfUnset(conf, cores);

    conf.setInt("giraph.yarn.task.heap.mb", workerMemoryGB * ONE_KB);

    conf.setIfUnset(NUM_MASTER_ZK_INPUT_SPLIT_THREADS, Integer.toString(cores));
    NUM_OUTPUT_THREADS.setIfUnset(conf, cores);

    int threadsDuringCommunication = Math.max(1,
        (int) (cores * CORES_FRACTION_DURING_COMMUNICATION.get(conf)));
    // Input overlaps with communication, set threads properly
    GiraphConstants.NUM_INPUT_THREADS.setIfUnset(
        conf, threadsDuringCommunication);
    // Compute overlaps with communication, set threads properly
    GiraphConstants.NUM_COMPUTE_THREADS.setIfUnset(
        conf, threadsDuringCommunication);
    // Netty server threads are the ones adding messages to stores,
    // or adding vertices and edges to stores during input,
    // these are expensive operations so set threads properly
    GiraphConstants.NETTY_SERVER_THREADS.setIfUnset(
        conf, threadsDuringCommunication);

    // Ensure we can utilize all communication threads by having enough
    // channels per server, in cases when we have just a few machines
    GiraphConstants.CHANNELS_PER_SERVER.setIfUnset(conf,
        Math.max(1, 2 * threadsDuringCommunication / workers));

    customConfig(conf);

    // Pooled allocator in netty is faster
    GiraphConstants.NETTY_USE_POOLED_ALLOCATOR.setIfUnset(conf, true);
    // Turning off auto read is faster
    GiraphConstants.NETTY_AUTO_READ.setIfUnset(conf, false);

    // Synchronize full gc calls across workers
    MemoryObserver.USE_MEMORY_OBSERVER.setIfUnset(conf, true);
    MemoryObserver.MIN_MS_BETWEEN_FULL_GCS.setIfUnset(conf, 10 * 1000);

    // Increase number of partitions per compute thread
    GiraphConstants.MIN_PARTITIONS_PER_COMPUTE_THREAD.setIfUnset(conf, 3);

    // Prefer ip addresses
    GiraphConstants.PREFER_IP_ADDRESSES.setIfUnset(conf, true);

    // Track job progress
//    GiraphConstants.TRACK_JOB_PROGRESS_ON_CLIENT.setIfUnset(conf, true); // todo this might cause problems in YARN

    // Thread-level debugging for easier understanding
    GiraphConstants.LOG_THREAD_LAYOUT.setIfUnset(conf, true);
    // Enable tracking and printing of metrics
    GiraphConstants.METRICS_ENABLE.setIfUnset(conf, true);

    conf.set("giraph.msgRequestWarningThreshold", "1");

    if (CONFIGURE_JAVA_OPTS.get(conf)) {
      List<String> javaOpts = getMemoryJavaOpts(conf);
      javaOpts.addAll(getGcJavaOpts(conf));
      javaOpts.addAll(tuningJavaOpts());
      javaOpts.addAll(observabilityJavaOpts());
      val options = join(javaOpts, " ");
      JAVA_JOB_OPTIONS.setIfUnset(conf, options);
      LOG.info("Configuring java options: " + JAVA_JOB_OPTIONS.get(conf));
    } else {
      LOG.info("Using default java options");
    }

    /**
     * Netty tuning (custom, not verified other than the buffer sizes)
     */
    NETTY_USE_DIRECT_MEMORY.setIfUnset(conf, true); // this allows to avoid copying objects before sending, but needs
    // some free space

    REQUEST_SIZE_WARNING_THRESHOLD.setIfUnset(conf, 1);
    NETTY_CLIENT_THREADS.setIfUnset(conf, threadsDuringCommunication);
    CLIENT_RECEIVE_BUFFER_SIZE.setIfUnset(conf, 32 * ONE_MB);
    CLIENT_SEND_BUFFER_SIZE.setIfUnset(conf, 32 * ONE_MB);
    SERVER_SEND_BUFFER_SIZE.setIfUnset(conf, 32 * ONE_MB);
    SERVER_RECEIVE_BUFFER_SIZE.setIfUnset(conf, 64 * ONE_MB);
    MAX_MSG_REQUEST_SIZE.setIfUnset(conf, 64 * ONE_MB);
    MAX_VERTEX_REQUEST_SIZE.setIfUnset(conf, 64 * ONE_MB);
    MAX_EDGE_REQUEST_SIZE.setIfUnset(conf, 64 * ONE_MB);

    USE_MESSAGE_SIZE_ENCODING.setIfUnset(conf, true);

    MESSAGE_ENCODE_AND_STORE_TYPE.setIfUnset(conf, BYTEARRAY_PER_PARTITION);
  }

  private static void customConfig(GiraphConfiguration conf) {
    // Limit number of open requests to 2000
    LIMIT_NUMBER_OF_OPEN_REQUESTS.setIfUnset(conf, true);
    StaticFlowControl.MAX_NUMBER_OF_OPEN_REQUESTS.setIfUnset(conf, 10000);

    // we use this instead
//    LIMIT_OPEN_REQUESTS_PER_WORKER.set(conf, true);
//    MAX_NUM_OF_UNSENT_REQUESTS.set(conf, 100);
//    MAX_NUM_OF_OPEN_REQUESTS_PER_WORKER.set(conf, 20);
  }

  private static String currentJar() {
    return new File(IgaConfiguration.class.getProtectionDomain()
        .getCodeSource()
        .getLocation()
        .getPath()).getName();
  }

  private static List<String> getMemoryJavaOpts(Configuration conf) {
    return Lists.newArrayList(
        "-XX:+UseNUMA"
    );
  }

  private static List<String> getGcJavaOpts(Configuration conf) {
    List<String> gcJavaOpts = new ArrayList<>();
    if (USE_G1_COLLECTOR.get(conf)) {
      gcJavaOpts.add("-XX:+UseG1GC");
      gcJavaOpts.add("-XX:MaxGCPauseMillis=500");
    } else {
      int newGenMemoryGb = Math.max(1, (int) (WORKER_MEMORY.get(conf) * NEW_GEN_MEMORY_FRACTION.get(conf)));
      // Use parallel gc collector
      gcJavaOpts.add("-XX:+UseParallelGC");
      gcJavaOpts.add("-XX:+UseParallelOldGC");
      // Fix new size generation
      gcJavaOpts.add("-XX:NewSize=" + newGenMemoryGb + "G");
      gcJavaOpts.add("-XX:MaxNewSize=" + newGenMemoryGb + "G");
    }
    return gcJavaOpts;
  }

  private static List<String> tuningJavaOpts() {
    return newArrayList(
        "-server",
        "-XX:ReservedCodeCacheSize=256M"
    );
  }

  private static List<String> observabilityJavaOpts() {
    return newArrayList(
        "-XX:+UnlockDiagnosticVMOptions",
        "-XX:+PrintFlagsFinal",
        "-XX:+HeapDumpOnOutOfMemoryError",
        "-XX:HeapDumpPath=<LOG_DIR>/@taskid@.hprof",
        "-XX:OnOutOfMemoryError='free -m'"
    );
  }


}
