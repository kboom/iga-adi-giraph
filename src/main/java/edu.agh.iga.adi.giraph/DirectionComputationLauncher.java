package edu.agh.iga.adi.giraph;

import org.apache.giraph.GiraphRunner;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;

final class DirectionComputationLauncher {

  /**
   * Use this or GiraphDriverTool implements Tool (example from the book)
   */
  static void solveDirection(DirectionComputationConfig config) {
    GiraphRunner giraphRunner = new GiraphRunner();
    giraphRunner.setConf(new GiraphConfiguration());
    ((GiraphConfiguration) giraphRunner.getConf()).setMaxNumberOfSupersteps(100);
    try {
      ToolRunner.run(giraphRunner, new String[]{});
    } catch (Exception e) {
      throw new IllegalStateException("Could not run direction computation", e);
    }
  }

  static DirectionComputationConfig computationConfig() {
    return new DirectionComputationConfig();
  }

  public static class DirectionComputationConfig {
    Path input;
    Path output;

    public DirectionComputationConfig setInput(Path input) {
      this.input = input;
      return this;
    }

    public DirectionComputationConfig setOutput(Path output) {
      this.output = output;
      return this;
    }
  }

}
