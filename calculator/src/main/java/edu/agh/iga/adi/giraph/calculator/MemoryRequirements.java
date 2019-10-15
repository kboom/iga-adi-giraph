package edu.agh.iga.adi.giraph.calculator;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import edu.agh.iga.adi.giraph.calculator.core.Problem;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Delegate;

@Value
@Builder
class MemoryRequirements {

  @Delegate
  Problem problem;
  Memory totalMemory;
  Memory workerMemory;

}
