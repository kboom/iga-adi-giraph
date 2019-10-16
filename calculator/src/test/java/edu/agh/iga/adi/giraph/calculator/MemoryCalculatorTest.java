package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Problem;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.MemoryCalculator.memoryRequirementsFor;
import static edu.agh.iga.adi.giraph.calculator.core.Memory.bytes;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryCalculatorTest {

  private static final Problem PROBLEM_12 = Problem.builder()
      .size(12)
      .workers(1)
      .build();

  private static final Problem PROBLEM_24 = Problem.builder()
      .size(24)
      .workers(1)
      .build();

  private static final Problem PROBLEM_6144 = Problem.builder()
      .size(6144)
      .workers(1)
      .build();

  private static final Problem PROBLEM_12288 = Problem.builder()
      .size(12288)
      .workers(1)
      .build();

  @Test
  void memoryRequirementsFor12Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_12)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_12)
            .totalMemory(bytes(32128))
            .workerMemory(bytes(32128))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor24Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_24)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_24)
            .totalMemory(bytes(123008))
            .workerMemory(bytes(123008))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor6144Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_6144)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_6144)
            .totalMemory(bytes(8996049344L)) // 8579 MB
            .workerMemory(bytes(8996049344L))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor12288Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_12288)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_12288)
            .totalMemory(bytes(36527502784L)) // 34835 MB
            .workerMemory(bytes(36527502784L))
            .build()
    );
  }


}