package edu.agh.iga.adi.giraph;

import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.yarn.GiraphYarnClient;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.direction.IgaGiraphJobFactory.injectSolverConfiguration;
import static java.util.stream.StreamSupport.stream;

public class IgaSolverTool extends Configured implements Tool {

  private static final Logger LOG = Logger.getLogger(IgaSolverTool.class);

  public static void main(String[] args) throws Exception {
    System.exit(ToolRunner.run(
        new IgaSolverTool(),
        args
    ));
  }

  @Override
  public int run(String[] strings) {
    val conf = injectSolverConfiguration(new GiraphConfiguration(getConf()));

    Stream.of(strings).filter(s -> s.contains("=")).forEach(s -> {
      String[] tokens = s.split("=");
      String key = tokens[0];
      String value = tokens[1];
      LOG.info("Setting " + key + " to " + value);
      conf.set(key, value);
    });

    stream(conf.spliterator(), false)
        .map(e -> e.getKey() + ":" + e.getValue())
        .forEach(LOG::info);

    try {
      val job = new GiraphYarnClient(conf, IgaSolverTool.class.getName());
      return job.run(true) ? 1 : -1;
    } catch (Exception e) {
      LOG.error("Could not run computations", e);
      return -1;
    }
  }

}
