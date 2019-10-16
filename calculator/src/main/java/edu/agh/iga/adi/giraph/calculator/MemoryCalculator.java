package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Problem;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemory;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryCreated;
import edu.agh.iga.adi.giraph.calculator.core.views.MaxMemoryProcessor.MaxMemoryState;
import lombok.NoArgsConstructor;
import lombok.val;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.INFINITE_MEMORY;
import static edu.agh.iga.adi.giraph.calculator.core.Solver.solverEvents;
import static edu.agh.iga.adi.giraph.calculator.core.system.SystemMemory.systemMemory;
import static edu.agh.iga.adi.giraph.calculator.core.views.MaxMemoryProcessor.MAX_MEMORY_PROCESSOR;
import static edu.agh.iga.adi.giraph.calculator.core.views.MaxMemoryProcessor.MaxMemoryState.zeroMaxMemoryState;
import static edu.agh.iga.adi.giraph.calculator.core.views.MemoryProcessorReplay.memoryProcessorReplayOf;

@NoArgsConstructor
class MemoryCalculator {

  static MemoryRequirements memoryRequirementsFor(Problem problem) {
    val totalMemory = totalMemory(problem);
    return MemoryRequirements.builder()
        .problem(problem)
        .totalMemory(totalMemory.getMax())
        .workerMemory(totalMemory.getMax().divide(problem.getWorkers()))
        .build();
  }

  private static MaxMemoryState totalMemory(Problem problem) {
    return memoryProcessorReplayOf(solverEvents(problem, infiniteMemory()))
        .foldEvents(MAX_MEMORY_PROCESSOR, zeroMaxMemoryState());
  }

  private static SystemMemory infiniteMemory() {
    return systemMemory(
        SystemMemoryCreated.builder()
            .totalMemory(INFINITE_MEMORY)
            .build()
    );
  }

}
