package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.direction.IgaPartitionerFactory;
import edu.agh.iga.adi.giraph.direction.io.IgaEdgeInputFormat;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.io.VertexInputFormat;
import org.apache.giraph.io.VertexOutputFormat;
import org.apache.giraph.job.GiraphJob;
import org.apache.giraph.master.MasterCompute;
import org.apache.giraph.worker.WorkerContext;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.Integer.MAX_VALUE;
import static org.apache.giraph.conf.GiraphConstants.*;

public class GiraphTestJob {

  private final GiraphJob job;
  private final DirManager dirManager;

  private GiraphTestJob(GiraphJob job, DirManager dirManager) {
    this.job = job;
    this.dirManager = dirManager;
  }

  public boolean run() {
    dirManager.recreateDirectories();
    try {
      return job.run(true);
    } catch (Exception e) {
      throw new IllegalStateException("Could not run the job", e);
    }
  }

  public static GiraphJobBuilder giraphJob() {
    return new GiraphJobBuilder();
  }

  public static class GiraphJobBuilder {

    private Class<? extends MasterCompute> computationClazz;
    private Class<? extends VertexInputFormat> vertexInputFormatClazz;
    private Class<? extends VertexOutputFormat> vertexOutputFormatClazz;
    private Class<? extends WorkerContext> workerContextClazz;
    private Function<GiraphConfiguration, DirManager> dirManagerFunction = DirManager::standardDirManager;
    private Consumer<GiraphConfiguration> configurationModifier = (conf) -> {
    };

    public GiraphJobBuilder workerContextClazz(Class<? extends WorkerContext> workerContextClazz) {
      this.workerContextClazz = workerContextClazz;
      return this;
    }

    public GiraphJobBuilder computationClazz(Class<? extends MasterCompute> computationClazz) {
      this.computationClazz = computationClazz;
      return this;
    }

    public GiraphJobBuilder vertexInputFormatClazz(Class<? extends VertexInputFormat> vertexInputFormatClazz) {
      this.vertexInputFormatClazz = vertexInputFormatClazz;
      return this;
    }

    public GiraphJobBuilder vertexOutputFormatClazz(Class<? extends VertexOutputFormat> vertexOutputFormatClazz) {
      this.vertexOutputFormatClazz = vertexOutputFormatClazz;
      return this;
    }

    public GiraphJobBuilder dirManager(Function<GiraphConfiguration, DirManager> fn) {
      this.dirManagerFunction = fn;
      return this;
    }

    public GiraphJobBuilder configuration(Consumer<GiraphConfiguration> configurationModifier) {
      this.configurationModifier = configurationModifier;
      return this;
    }

    public GiraphTestJob build() {
      GiraphConfiguration conf = createConfiguration();
      configurationModifier.accept(conf);
      GiraphJob job = createJob(conf);
      return new GiraphTestJob(job, dirManagerFunction.apply(conf));
    }

    private GiraphJob createJob(GiraphConfiguration conf) {
      try {
        return new GiraphJob(conf, "test");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }

    private GiraphConfiguration createConfiguration() {
      GiraphConfiguration conf = new GiraphConfiguration();
      conf.setMasterComputeClass(computationClazz);
      conf.setVertexInputFormatClass(vertexInputFormatClazz);
      conf.setVertexOutputFormatClass(vertexOutputFormatClazz);
      conf.setWorkerContextClass(workerContextClazz);
      conf.setLocalTestMode(true);
      conf.setMaxNumberOfSupersteps(3);
      conf.setMaxMasterSuperstepWaitMsecs(30 * 1000);
      conf.setEventWaitMsecs(3 * 1000);
      conf.setEdgeInputFormatClass(IgaEdgeInputFormat.class);
      conf.setGraphPartitionerFactoryClass(IgaPartitionerFactory.class);
      VERTEX_ID_CLASS.set(conf, LongWritable.class);
      VERTEX_VALUE_CLASS.set(conf, IgaElementWritable.class);
      EDGE_VALUE_CLASS.set(conf, IgaOperationWritable.class);
      OUTGOING_MESSAGE_VALUE_CLASS.set(conf, IgaMessageWritable.class);
      ZOOKEEPER_SERVERLIST_POLL_MSECS.set(conf, 500);
      MAX_NUMBER_OF_SUPERSTEPS.set(conf, MAX_VALUE);
      SPLIT_MASTER_WORKER.set(conf, false);
      LOCAL_TEST_MODE.set(conf, true);
      conf.set(MAX_WORKERS, "1");
      return conf;
    }

  }

}
