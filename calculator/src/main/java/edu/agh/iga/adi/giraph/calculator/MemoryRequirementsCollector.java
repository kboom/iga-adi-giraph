package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Problem;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@NoArgsConstructor
class MemoryRequirementsCollector {

  private static final int MIN_PROBLEM_SIZE = 12;
  private static final int PROBLEM_SIZE_MULTIPLIER = 2;

  static List<MemoryRequirements> listRequirementsFor(CalculatorParameters parameters) {
    return problems(parameters)
        .map(MemoryCalculator::memoryRequirementsFor)
        .collect(toList());
  }

  private static Stream<Problem> problems(CalculatorParameters parameters) {
    return problemSizes(parameters)
        .flatMap(problemSize -> combineWithWorkers(parameters, problemSize));
  }

  private static Stream<Integer> problemSizes(CalculatorParameters parameters) {
    return range(0, parameters.getMeshSizes())
        .map(MemoryRequirementsCollector::nthProblemSize)
        .boxed();
  }

  private static int nthProblemSize(int power) {
    return (int) (MIN_PROBLEM_SIZE * Math.pow(PROBLEM_SIZE_MULTIPLIER, power));
  }

  private static Stream<Problem> combineWithWorkers(CalculatorParameters parameters, Integer problemSize) {
    return parameters.getAvailableWorkers()
        .stream()
        .map(Integer::parseInt)
        .map(
            workers ->
                Problem.builder()
                    .size(problemSize)
                    .workers(workers)
                    .build()
        );
  }

}
