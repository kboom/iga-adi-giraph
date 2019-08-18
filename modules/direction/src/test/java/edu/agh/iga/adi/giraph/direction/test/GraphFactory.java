package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;

public final class GraphFactory {

  public static TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph(GiraphConfiguration config) {
    return new TestGraph<>(config);
  }

}
