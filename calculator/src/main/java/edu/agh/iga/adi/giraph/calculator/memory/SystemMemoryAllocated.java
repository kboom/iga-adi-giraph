package edu.agh.iga.adi.giraph.calculator.memory;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SystemMemoryAllocated implements SystemMemoryEvent {
  MemoryHandle handle;
  Memory memory;
}
