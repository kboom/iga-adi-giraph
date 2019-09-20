package edu.agh.iga.adi.giraph.direction.config;

import edu.agh.iga.adi.giraph.core.problem.ProblemType;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers;
import edu.agh.iga.adi.giraph.direction.computation.InitialProblemType;
import org.apache.giraph.conf.EnumConfOption;
import org.apache.giraph.conf.FloatConfOption;
import org.apache.giraph.conf.IntConfOption;
import org.apache.giraph.conf.StrConfOption;

import java.util.stream.Stream;

import static com.google.common.io.Files.createTempDir;
import static edu.agh.iga.adi.giraph.core.problem.ProblemType.PROJECTION;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.InitialProblemType.CONSTANT;
import static java.util.stream.Collectors.joining;

public class IgaConfiguration {

  public static final IntConfOption PROBLEM_SIZE = new IntConfOption("iga.problem.size", 12, "The number of elements in one direction");
  public static final EnumConfOption<ProblemType> PROBLEM_TYPE = new EnumConfOption<>("iga.problem.type", ProblemType.class, PROJECTION, "The type of the problem to simulate");
  public static final EnumConfOption<InitialProblemType> INITIAL_PROBLEM_TYPE = new EnumConfOption<>("iga.problem.initial.type", InitialProblemType.class, CONSTANT, "The type of the initial surface to generate");
  public static final FloatConfOption STEP_DELTA = new FloatConfOption("iga.step.delta", 0.000000001f, "The length of the time step");
  public static final IntConfOption STEP_COUNT = new IntConfOption("iga.problem.steps", 1, "The number of steps to run");
  public static final IntConfOption HEIGHT_PARTITIONS = new IntConfOption("iga.tree.partition.size", 1, "The height of tree partitions");
  public static final StrConfOption FIRST_INITIALISATION_TYPE = new StrConfOption("iga.initialisation.type", SURFACE_PROBLEM.getType(), "The type of initialisation - " + resolverTypes() + " - use surface if you initialise the leaves or coefficients if you initialize the branches");
  public static final StrConfOption COEFFICIENTS_INPUT = new StrConfOption("mapred.input.dir", createTempDir().getPath(), "The (HDFS) directory to read the coefficients from");
  public static final StrConfOption COEFFICIENTS_OUTPUT = new StrConfOption("mapred.output.dir", createTempDir().getPath(), "The (HDFS) directory to put the coefficients to");
  public static final StrConfOption ZK_DIR = new StrConfOption("giraph.zkDir", createTempDir().getPath(), "The zookeeper directory to put coefficients to");

  private static String resolverTypes() {
    return Stream.of(IgaComputationResolvers.values()).map(IgaComputationResolvers::getType).collect(joining(","));
  }

}
