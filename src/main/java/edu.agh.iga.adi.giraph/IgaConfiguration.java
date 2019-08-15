package edu.agh.iga.adi.giraph;

import com.google.common.io.Files;
import org.apache.giraph.conf.IntConfOption;
import org.apache.giraph.conf.StrConfOption;

public class IgaConfiguration {

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");
  public static final IntConfOption HEIGHT_PARTITIONS = new IntConfOption("iga.tree.partition.size", 1, "The height of tree partitions");
  public static final StrConfOption COEFFICIENTS_OUTPUT = new StrConfOption("mapred.output.dir", Files.createTempDir().getPath(), "The (HDFS) directory to put coefficients to");

}
