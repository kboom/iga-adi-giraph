package edu.agh.iga.adi.giraph.direction.test;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.job.GiraphJob;

import java.nio.file.Path;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.*;
import static java.lang.Integer.MAX_VALUE;
import static java.util.Optional.ofNullable;
import static org.apache.giraph.conf.GiraphConstants.*;
import static org.apache.giraph.io.formats.GiraphFileInputFormat.addVertexInputPath;

@Getter
public class GiraphTestJob {

  private final GiraphJob job;
  private final GiraphConfiguration config;
  private final DirManager dirManager;

  private GiraphTestJob(GiraphJob job, GiraphConfiguration config, DirManager dirManager) {
    this.job = job;
    this.config = config;
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

    private Path inputDir;
    private Path outputDir;
    private Function<GiraphConfiguration, DirManager> dirManagerFunction = DirManager::standardDirManager;
    private Consumer<GiraphConfiguration> configurationModifier = (conf) -> {
    };

    public GiraphJobBuilder coefficientsInputDir(Path inputDir) {
      this.inputDir = inputDir;
      return this;
    }

    public GiraphJobBuilder coefficientsOutputDir(Path outputDir) {
      this.outputDir = outputDir;
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
      setInputPath(conf);
      return new GiraphTestJob(job, conf, dirManagerFunction.apply(conf));
    }

    @SneakyThrows
    private void setInputPath(GiraphConfiguration conf) {
      addVertexInputPath(conf, new org.apache.hadoop.fs.Path(COEFFICIENTS_INPUT.get(conf)));
    }

    @SneakyThrows
    private GiraphJob createJob(GiraphConfiguration conf) {
      val rand = new Random();
      return new GiraphJob(igaConfiguration(conf), "iga-test-job-" + rand.nextLong());
    }

    private GiraphConfiguration createConfiguration() {
      GiraphConfiguration conf = new GiraphConfiguration();
      conf.setLocalTestMode(true);
      conf.setMaxMasterSuperstepWaitMsecs(30 * 1000);
      conf.setEventWaitMsecs(3 * 1000);
      ofNullable(inputDir).ifPresent(dir -> COEFFICIENTS_INPUT.set(conf, dir.toString()));
      ofNullable(outputDir).ifPresent(dir -> COEFFICIENTS_OUTPUT.set(conf, dir.toString()));
      ZOOKEEPER_SERVERLIST_POLL_MSECS.set(conf, 500);
      MAX_NUMBER_OF_SUPERSTEPS.set(conf, MAX_VALUE);
      SPLIT_MASTER_WORKER.set(conf, false);
      conf.set(MAX_WORKERS, "1");
      ZOOKEEPER_SERVER_PORT.set(conf, 0);
      return conf;
    }

  }

}
