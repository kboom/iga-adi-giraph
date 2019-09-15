package edu.agh.iga.adi.giraph;

import com.beust.jcommander.JCommander;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.job.GiraphConfigurationValidator;
import org.apache.giraph.yarn.GiraphYarnClient;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.IgaGiraphJobFactory.injectSolverConfiguration;
import static java.lang.System.exit;
import static java.util.Optional.ofNullable;
import static java.util.stream.StreamSupport.stream;

public class IgaSolverTool extends Configured implements Tool {

  private static final Logger LOG = Logger.getLogger(IgaSolverTool.class);

  public static void main(String[] args) throws Exception {
    exit(ToolRunner.run(
        new IgaSolverTool(),
        args
    ));
  }

  @Override
  public int run(String[] strings) {
    LOG.info("Hello bitches!" + strings);
    val giraphConf = new GiraphConfiguration(getConf());
    giraphConf.set("fs.hdfs.impl",
        org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
    );
    giraphConf.set("fs.file.impl",
        org.apache.hadoop.fs.LocalFileSystem.class.getName()
    );
    injectSolverConfiguration(giraphConf);
    populateCustomConfiguration(giraphConf, processOptions(strings));
    printConfiguration(giraphConf);
    validateConfiguration(giraphConf);
    return runJob(giraphConf);
  }

  private static IgaOptions processOptions(String[] strings) {
    IgaOptions o = new IgaOptions();
    JCommander commander = JCommander.newBuilder()
        .addObject(o)
        .build();
    commander.parse(strings);

    LOG.info("Parsed config" + o.toString());

    if (o.isHelp()) {
      commander.usage();
      exit(0);
    }

    return o;
  }

  private void populateCustomConfiguration(GiraphConfiguration config, IgaOptions options) {
    PROBLEM_SIZE.set(config, options.getElements());
    HEIGHT_PARTITIONS.set(config, options.getHeight());
    FIRST_INITIALISATION_TYPE.set(config, options.getType());
    ofNullable(options.getInputDirectory()).ifPresent(i -> addInput(config, i));
    COEFFICIENTS_OUTPUT.set(config, options.getOutputDirectory());
    config.setWorkerConfiguration(options.getWorkers(), options.getWorkers(), 100);

    options.getConfig()
        .stream()
        .map(v -> v.split("="))
        .forEach(v -> config.set(v[0], v[1]));
  }

  @SneakyThrows
  private void addInput(GiraphConfiguration config, String i) {
    config.set("mapreduce.output.fileoutputformat.outputdir", i);
//    addVertexInputPath(config, new Path(i));
  }

//  private void populateCustomConfiguration(GiraphConfiguration conf, String[] strings) {
//    Stream.of(strings).filter(s -> s.contains("=")).forEach(s -> {
//      String[] tokens = s.split("=");
//      String key = tokens[0];
//      String value = tokens[1];
//      conf.set(key, value);
//    });
//  }

  private int runJob(GiraphConfiguration conf) {
    try {
      val job = new GiraphYarnClient(conf, IgaSolverTool.class.getName());
      return job.run(true) ? 1 : -1;
    } catch (Exception e) {
      LOG.error("Could not run computations", e);
      return -1;
    }
  }

  private void printConfiguration(GiraphConfiguration conf) {
    stream(conf.spliterator(), false)
        .map(e -> e.getKey() + ":" + e.getValue())
        .forEach(LOG::info);
  }

  private void validateConfiguration(GiraphConfiguration conf) {
    @SuppressWarnings("rawtypes")
    GiraphConfigurationValidator<?, ?, ?, ?, ?> gtv = new GiraphConfigurationValidator(conf);
    gtv.validateConfiguration();
  }

}
