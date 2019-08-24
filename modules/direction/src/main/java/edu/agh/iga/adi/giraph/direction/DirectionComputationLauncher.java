package edu.agh.iga.adi.giraph.direction;

import org.apache.giraph.GiraphRunner;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.util.ToolRunner;

public final class DirectionComputationLauncher {

  /**
   * Use this or GiraphDriverTool implements Tool (example from the book)
   */
  public static void solveDirection(DirectionComputationConfig config) {
    GiraphRunner giraphRunner = new GiraphRunner();
    giraphRunner.setConf(new GiraphConfiguration());
    ((GiraphConfiguration) giraphRunner.getConf()).setMaxNumberOfSupersteps(100);
    try {
      ToolRunner.run(giraphRunner, new String[]{});
    } catch (Exception e) {
      throw new IllegalStateException("Could not run direction computation", e);
    }
  }

}
