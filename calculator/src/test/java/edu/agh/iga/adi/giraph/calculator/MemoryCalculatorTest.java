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

  private static final Problem PROBLEM_48 = Problem.builder()
      .size(48)
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
            .totalMemory(bytes(17984))
            .workerMemory(bytes(17984))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor24Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_24)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_24)
            .totalMemory(bytes(65152))
            .workerMemory(bytes(65152))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor48Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_48)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_48)
            .totalMemory(bytes(247040))
            .workerMemory(bytes(247040))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor6144Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_6144)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_6144)
            .totalMemory(bytes(3826941952L)) // 8579 MB
            .workerMemory(bytes(3826941952L))
            .build()
    );
  }

  @Test
  void memoryRequirementsFor12288Elements() {
    assertThat(memoryRequirementsFor(PROBLEM_12288)).isEqualToComparingFieldByField(
        MemoryRequirements.builder()
            .problem(PROBLEM_12288)
            .totalMemory(bytes(15304294400L)) // 34835 MB
            .workerMemory(bytes(15304294400L))
            .build()
    );
  }


}