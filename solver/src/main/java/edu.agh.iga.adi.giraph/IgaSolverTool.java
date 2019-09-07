package edu.agh.iga.adi.giraph;

import lombok.val;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.direction.IgaGiraphJobFactory.igaJob;

public class IgaSolverTool implements Tool {

  private static final Logger LOG = Logger.getLogger(IgaSolverTool.class);

  private Configuration config;

  public static void main(String[] args) throws Exception {
    ToolRunner.run(new IgaSolverTool(), args);
  }

  @Override
  public int run(String[] strings) {
    LOG.info("Running computations");
    val job = igaJob(giraphConfiguration());
    try {
      job.run(true);
      return 0;
    } catch (Exception e) {
      LOG.error("Could not run computations", e);
      return -1;
    }
  }

  @Override
  public void setConf(Configuration configuration) {
    config = configuration;
  }

  @Override
  public Configuration getConf() {
    return config;
  }

  private GiraphConfiguration giraphConfiguration() {
    return new GiraphConfiguration(getConf());
  }

}