package edu.agh.iga.adi.giraph.direction.config;

import edu.agh.iga.adi.giraph.core.problem.ProblemType;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers;
import edu.agh.iga.adi.giraph.direction.computation.InitialProblemType;
import org.apache.commons.lang3.StringUtils;
import org.apache.giraph.comm.flow_control.StaticFlowControl;
import org.apache.giraph.comm.netty.NettyClient;
import org.apache.giraph.conf.*;
import org.apache.giraph.worker.MemoryObserver;
import org.apache.hadoop.conf.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.io.Files.createTempDir;
import static edu.agh.iga.adi.giraph.core.problem.ProblemType.PROJECTION;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.InitialProblemType.CONSTANT;
import static java.util.stream.Collectors.joining;
import static org.apache.giraph.conf.GiraphConstants.MIN_WORKERS;
import static org.apache.giraph.conf.GiraphConstants.NUM_OUTPUT_THREADS;
import static org.apache.giraph.master.BspServiceMaster.NUM_MASTER_ZK_INPUT_SPLIT_THREADS;

public class IgaConfiguration implements BulkConfigurator {

  public static final BooleanConfOption CONFIGURE_JAVA_OPTS = new BooleanConfOption("giraph.configureJavaOpts", true, "Whether to configure java opts");

  public static final BooleanConfOption USE_G1_COLLECTOR = new BooleanConfOption("iga.useG1", false, "Use G1GC collector");

  public static final IntConfOption WORKER_CORES = new IntConfOption("iga.cores", 1, "The number of cores per worker");
  public static final IntConfOption WORKER_MEMORY = new IntConfOption("iga.memory", 1, "The amount of memory per worker in gigabytes");

  public static final FloatConfOption NEW_GEN_MEMORY_FRACTION = new FloatConfOption("iga.newGenMemoryFraction", 0.1f, "Fraction of total mapper memory to use for new generation");
  public static final FloatConfOption CORES_FRACTION_DURING_COMMUNICATION = new FloatConfOption("iga.coresFractionDuringCommunication", 0.7f, "Fraction of mapper cores to use for threads which overlap with" +
      " network communication");

  public static final StrConfOption JAVA_JOB_OPTIONS = new StrConfOption("iga.worker.java.opts", null, "Java options passed to the workers");

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");
  public static final EnumConfOption<ProblemType> PROBLEM_TYPE = new EnumConfOption<>("iga.problem.type", ProblemType.class, PROJECTION, "The type of the problem to simulate");
  public static final EnumConfOption<InitialProblemType> INITIAL_PROBLEM_TYPE = new EnumConfOption<>("iga.problem.initial.type", InitialProblemType.class, CONSTANT, "The type of the initial surface to generate");
  public static final FloatConfOption STEP_DELTA = new FloatConfOption("iga.step.delta", 0.000000001f, "The length of the time step");
  public static final IntConfOption STEP_COUNT = new IntConfOption("iga.problem.steps", 1, "The number of steps to run");
  public static final IntConfOption HEIGHT_PARTITIONS = new IntConfOption("iga.tree.partition.size", 1, "The height of tree partitions");
  public static final StrConfOption FIRST_INITIALISATION_TYPE = new StrConfOption("iga.initialisation.type", SURFACE_PROBLEM.getType(), "The type of initialisation - " + resolverTypes() + " - use surface if you initialise the leaves or coefficients if you initialize the branches");
  public static final StrConfOption COEFFICIENTS_INPUT = new StrConfOption("mapred.input.dir", createTempDir().getPath(), "The (HDFS) directory to read the coefficients from");
  public static final StrConfOption COEFFICIENTS_OUTPUT = new StrConfOption("mapred.output.dir", createTempDir().getPath(), "The (HDFS) directory to put the coefficients to");
  public static final StrConfOption ZK_DIR = new StrConfOption("giraph.zkDir", createTempDir().getPath(), "The zookeeper directory to put coefficients to");

  private static String resolverTypes() {
    return Stream.of(IgaComputationResolvers.values()).map(IgaComputationResolvers::getType).collect(joining(","));
  }

  @Override
  public void configure(GiraphConfiguration conf) {
    int workers = conf.getInt(MIN_WORKERS, -1);
    int cores = WORKER_CORES.get(conf);

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

    // Limit number of open requests to 2000
    NettyClient.LIMIT_NUMBER_OF_OPEN_REQUESTS.setIfUnset(conf, true);
    StaticFlowControl.MAX_NUMBER_OF_OPEN_REQUESTS.setIfUnset(conf, 100);
    // Pooled allocator in netty is faster
    GiraphConstants.NETTY_USE_POOLED_ALLOCATOR.setIfUnset(conf, true);
    // Turning off auto read is faster
    GiraphConstants.NETTY_AUTO_READ.setIfUnset(conf, false);

    // Synchronize full gc calls across workers
    MemoryObserver.USE_MEMORY_OBSERVER.setIfUnset(conf, true);

    // Increase number of partitions per compute thread
    GiraphConstants.MIN_PARTITIONS_PER_COMPUTE_THREAD.setIfUnset(conf, 3);

    // Prefer ip addresses
    GiraphConstants.PREFER_IP_ADDRESSES.setIfUnset(conf, true);

    // Track job progress
    GiraphConstants.TRACK_JOB_PROGRESS_ON_CLIENT.setIfUnset(conf, true);
    // Thread-level debugging for easier understanding
    GiraphConstants.LOG_THREAD_LAYOUT.setIfUnset(conf, true);
    // Enable tracking and printing of metrics
    GiraphConstants.METRICS_ENABLE.setIfUnset(conf, true);

    if (CONFIGURE_JAVA_OPTS.get(conf)) {
      List<String> javaOpts = getMemoryJavaOpts(conf);
      javaOpts.addAll(getGcJavaOpts(conf));
      JAVA_JOB_OPTIONS.set(conf, StringUtils.join(javaOpts, " "));
    }
  }

  private static List<String> getMemoryJavaOpts(Configuration conf) {
    int memoryGb = WORKER_MEMORY.get(conf);
    List<String> javaOpts = new ArrayList<>();
    // Set xmx and xms to the same value
    javaOpts.add("-Xms" + memoryGb + "g");
    javaOpts.add("-Xmx" + memoryGb + "g");
    // Non-uniform memory allocator (great for multi-threading and appears to
    // have no impact when single threaded)
    javaOpts.add("-XX:+UseNUMA");
    return javaOpts;
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
      gcJavaOpts.add("-XX:NewSize=" + newGenMemoryGb + "g");
      gcJavaOpts.add("-XX:MaxNewSize=" + newGenMemoryGb + "g");
    }
    return gcJavaOpts;
  }

}
