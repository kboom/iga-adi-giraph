package edu.agh.iga.adi.giraph;

import org.apache.giraph.GiraphRunner;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.hadoop.util.ToolRunner;

public class IgaLauncher {

  /**
   * Use this or GiraphDriverTool implements Tool (example from the book)
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    GiraphRunner giraphRunner = new GiraphRunner();
    giraphRunner.setConf(new GiraphConfiguration());
    ((GiraphConfiguration) giraphRunner.getConf()).setMaxNumberOfSupersteps(100);
    ToolRunner.run(giraphRunner, args);
  }

}
