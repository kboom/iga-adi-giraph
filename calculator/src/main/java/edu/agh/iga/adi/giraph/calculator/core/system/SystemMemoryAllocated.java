package edu.agh.iga.adi.giraph.calculator.core.system;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SystemMemoryAllocated implements SystemMemoryEvent {
  MemoryHandle handle;
  Memory memory;
}
