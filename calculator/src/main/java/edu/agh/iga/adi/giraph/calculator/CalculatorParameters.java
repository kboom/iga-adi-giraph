package edu.agh.iga.adi.giraph.calculator;

import com.beust.jcommander.Parameter;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
class CalculatorParameters {

  @Parameter(names = "--help", help = true)
  private boolean help;

  @Parameter(names = {"-w", "--workers"}, description = "The number of workers used")
  @Builder.Default
  private Integer workers = 2;

  @Parameter(names = {"--sizes"}, description = "The number of mesh sizes to compute the statistics for")
  @Builder.Default
  private Integer meshSizes = 13;

  @Parameter(description = "Available workers")
  @Builder.Default
  private List<String> availableWorkers;

}
