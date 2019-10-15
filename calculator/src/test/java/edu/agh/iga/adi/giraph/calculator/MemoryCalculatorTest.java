package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Problem;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.MemoryCalculator.memoryRequirementsFor;
import static edu.agh.iga.adi.giraph.calculator.core.Memory.megabytes;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryCalculatorTest {

  private static final Problem PROBLEM_12 = Problem.builder()
      .size(12)
      .workers(1)
      .build();

  @Test
  void memoryRequirementsFor12Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_12)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_12)
            .totalMemory(megabytes(1024))
            .workerMemory(megabytes(1024))
            .build()
    );
  }


}