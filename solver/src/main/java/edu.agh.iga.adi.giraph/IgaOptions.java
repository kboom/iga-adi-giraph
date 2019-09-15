package edu.agh.iga.adi.giraph;

import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
class IgaOptions {

  @Parameter(description = "The input files if needed")
  private List<String> input = new ArrayList<>();

  @Parameter(names = {"-c"}, variableArity = true, description = "config")
  private List<String> config = new ArrayList<>();

  @Parameter(names = { "-w", "--workers" }, description = "The number of workers used")
  private Integer workers = 1;

  @Parameter(names = { "-i", "--input" }, description = "The path to the coefficients input directory (HDFS)")
  private String inputDirectory;

  @Parameter(names = {"-o", "--output"}, required = true, description = "The path to the coefficients output directory (HDFS)")
  private String outputDirectory;

  @Parameter(names = { "-e", "--elements" }, description = "The number of elements in one dimension")
  private Integer elements = 1;

  @Parameter(names = { "-h", "--height" }, description = "The height of each partition")
  private Integer height = 1;

  @Parameter(names = { "-t", "--type" }, description = "The type of the computations. One of {surface, projection}")
  private String type;

  @Parameter(names = "--help", help = true)
  private boolean help;

}
