package edu.agh.iga.adi.giraph.calculator;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "memoryRequirements")
class MemoryRequirements {

  int problemSize;
  int workers;
  int totalMemoryMB;
  int workerMemoryMB;

}
