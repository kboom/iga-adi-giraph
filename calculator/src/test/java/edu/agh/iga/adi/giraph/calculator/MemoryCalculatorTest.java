package edu.agh.iga.adi.giraph.calculator;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.MemoryCalculator.memoryRequirementsFor;
import static edu.agh.iga.adi.giraph.calculator.MemoryRequirements.memoryRequirements;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryCalculatorTest {

  @Test
  void memoryRequirementsFor12Elements() {
    assertThat(memoryRequirementsFor(
        Problem.builder()
            .size(12)
            .workers(1)
            .build()
    )).isEqualToComparingFieldByField(
        memoryRequirements()
            .problemSize(12)
            .workers(1)
            .totalMemoryMB(1024)
            .workerMemoryMB(1024)
            .build()
    );
  }


}