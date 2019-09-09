package edu.agh.iga.adi.giraph.direction.test;

import lombok.NoArgsConstructor;
import org.apache.hadoop.yarn.server.MiniYARNCluster;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class YarnTestClusterFactory {

  public static MiniYARNCluster localYarnCluster() {
    return new MiniYARNCluster("test", 1, 1, 1);
  }

}
