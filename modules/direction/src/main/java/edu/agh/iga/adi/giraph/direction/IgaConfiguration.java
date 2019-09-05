package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers;
import org.apache.giraph.conf.IntConfOption;
import org.apache.giraph.conf.StrConfOption;

import java.util.stream.Stream;

import static com.google.common.io.Files.createTempDir;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static java.util.stream.Collectors.joining;

public class IgaConfiguration {

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");
  public static final IntConfOption HEIGHT_PARTITIONS = new IntConfOption("iga.tree.partition.size", 1, "The height of tree partitions");
  public static final StrConfOption INITIALISATION_TYPE = new StrConfOption("iga.initialisation.type", SURFACE_PROBLEM.getType(), "The type of initialisation - " + resolverTypes() + " - use surface if you initialise the leaves or coefficients if you initialize the branches");
  public static final StrConfOption COEFFICIENTS_INPUT = new StrConfOption("mapred.input.dir", createTempDir().getPath(), "The (HDFS) directory to read the coefficients from");
  public static final StrConfOption COEFFICIENTS_OUTPUT = new StrConfOption("mapred.output.dir", createTempDir().getPath(), "The (HDFS) directory to put the coefficients to");
  public static final StrConfOption ZK_DIR = new StrConfOption("giraph.zkDir", createTempDir().getPath(), "The zookeeper directory to put coefficients to");

  private static String resolverTypes() {
    return Stream.of(IgaComputationResolvers.values()).map(IgaComputationResolvers::getType).collect(joining(","));
  }

}
