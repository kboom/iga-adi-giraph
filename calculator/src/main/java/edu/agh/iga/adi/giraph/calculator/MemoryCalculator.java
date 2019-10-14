package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Problem;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemory;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryCreated;
import lombok.NoArgsConstructor;
import lombok.val;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.INFINITE_MEMORY;
import static edu.agh.iga.adi.giraph.calculator.core.Solver.runSolver;
import static edu.agh.iga.adi.giraph.calculator.core.system.SystemMemory.systemMemory;

@NoArgsConstructor
class MemoryCalculator {

  static MemoryRequirements memoryRequirementsFor(Problem problem) {
    val initialMemory = infiniteMemory();
    val systemMemory = runSolver(problem, initialMemory);
  }

  private static SystemMemory infiniteMemory() {
    return systemMemory(
        SystemMemoryCreated.builder()
            .totalMemory(INFINITE_MEMORY)
            .build()
    );
  }

}
