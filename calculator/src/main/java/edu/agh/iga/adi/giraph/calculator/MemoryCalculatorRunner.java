package edu.agh.iga.adi.giraph.calculator;

import com.beust.jcommander.JCommander;
import lombok.val;

import static java.lang.System.exit;

public class MemoryCalculatorRunner {

  private static void main(String[] strings) {
    val parameters = CalculatorParameters.builder().build();
    JCommander commander = parseParameters(parameters);
    commander.parse(strings);

    if (parameters.isHelp()) {
      commander.usage();
      exit(0);
    }
  }

  private static JCommander parseParameters(CalculatorParameters parameters) {
    return JCommander.newBuilder()
        .addObject(parameters)
        .build();
  }

}
