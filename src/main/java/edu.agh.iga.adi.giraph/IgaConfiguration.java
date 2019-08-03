package edu.agh.iga.adi.giraph;

import org.apache.giraph.conf.IntConfOption;

public class IgaConfiguration {

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");

}
