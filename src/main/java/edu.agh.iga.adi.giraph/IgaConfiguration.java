package edu.agh.iga.adi.giraph;

import org.apache.giraph.conf.IntConfOption;

public class IgaConfiguration {

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");
  public static final IntConfOption HEIGHT_PARTITIONS = new IntConfOption("iga.tree.partition.size", 1, "The height of tree partitions");

}
