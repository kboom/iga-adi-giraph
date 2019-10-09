package edu.agh.iga.adi.giraph.calculator;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.MemoryRequirementsCollector.listRequirementsFor;
import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryRequirementsCollectorTest {

  @Test
  void producesNoRequirementsForNoWorkers() {
    assertThat(listRequirementsFor(
        CalculatorParameters.builder()
            .availableWorkers(emptyList())
            .build()
    )).isEmpty();
  }

  @Test
  void producesNoRequirementsForNoProblemSizes() {
    assertThat(listRequirementsFor(
        CalculatorParameters.builder()
            .meshSizes(0)
            .build()
    )).isEmpty();
  }

  @Test
  void producesOneRequirementForSingleWorkerAndProblemSize() {
    assertThat(listRequirementsFor(
        CalculatorParameters.builder()
            .availableWorkers(singletonList(valueOf(1)))
            .meshSizes(1)
            .build()
    )).hasSize(1);
  }

  @Test
  void producesAsManyRequirementsAsCombinationsOfWorkersAndProblemSizes() {
    assertThat(listRequirementsFor(
        CalculatorParameters.builder()
            .availableWorkers(singletonList(valueOf(2)))
            .meshSizes(3)
            .build()
    )).hasSize(6);
  }

}