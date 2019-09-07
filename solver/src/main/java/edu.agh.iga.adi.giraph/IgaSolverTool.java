package edu.agh.iga.adi.giraph;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import static edu.agh.iga.adi.giraph.direction.IgaGiraphJobFactory.igaJob;

public class IgaSolverTool implements Tool {

  private Configuration config;

  public static void main(String[] args) throws Exception {
    ToolRunner.run(new IgaSolverTool(), args);
  }

  @Override
  public int run(String[] strings) {
    try {
      igaJob(giraphConfiguration()).run(true);
      return 0;
    } catch (Exception e) {
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
