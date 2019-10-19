package edu.agh.iga.adi.giraph.calculator;

import com.beust.jcommander.Parameter;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.beust.jcommander.internal.Lists.newArrayList;

@Builder
@Getter
class CalculatorParameters {

  @Parameter(names = "--help", help = true)
  private boolean help;

  @Parameter(names = {"-w", "--workers"}, description = "The number of workers used")
  @Builder.Default
  private List<Integer> workers = newArrayList(1, 2, 4);

  @Parameter(names = {"--sizes"}, description = "The number of mesh sizes to compute the statistics for")
  @Builder.Default
  private Integer meshSizes = 13;

}
