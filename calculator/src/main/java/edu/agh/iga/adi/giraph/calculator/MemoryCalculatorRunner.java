package edu.agh.iga.adi.giraph.calculator;

import com.beust.jcommander.JCommander;
import edu.agh.iga.adi.giraph.calculator.core.Problem;
import lombok.val;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.exit;

public class MemoryCalculatorRunner {


  public static void main(String[] strings) {
    val parameters = CalculatorParameters.builder().build();
    JCommander commander = parseParameters(parameters);
    commander.parse(strings);

    if (parameters.isHelp()) {
      commander.usage();
      exit(0);
    }

    print(parameters);
  }

  private static void print(CalculatorParameters parameters) {
    memoryRequirements(parameters)
        .map(MemoryRequirementsFormatter::format)
        .forEach(System.out::println);
  }

  private static Stream<MemoryRequirements> memoryRequirements(CalculatorParameters parameters) {
    return IntStream.range(0, parameters.getMeshSizes())
        .map(p -> (int) (12 * Math.pow(2, p)))
        .boxed()
        .flatMap(size ->
            parameters.getWorkers()
                .stream()
                .map(workers ->
                    Problem.builder()
                        .size(size)
                        .workers(workers)
                        .build()
                ))
        .map(MemoryCalculator::memoryRequirementsFor);
  }

  private static JCommander parseParameters(CalculatorParameters parameters) {
    return JCommander.newBuilder()
        .addObject(parameters)
        .build();
  }

}
