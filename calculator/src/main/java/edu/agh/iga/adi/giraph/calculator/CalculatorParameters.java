package edu.agh.iga.adi.giraph.calculator;

import com.beust.jcommander.Parameter;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Builder
@Value
class CalculatorParameters {

  @Parameter(names = "--help", help = true)
  boolean help;

  @Parameter(names = {"-w", "--workers"}, description = "The number of workers used")
  @Builder.Default
  Integer workers = 1;

  @Parameter(names = {"--sizes"}, description = "The number of mesh sizes to compute the statistics for")
  @Builder.Default
  Integer meshSizes = 10;

  @Parameter(description = "Available workers")
  @Builder.Default
  List<String> availableWorkers = new ArrayList<>();

}
