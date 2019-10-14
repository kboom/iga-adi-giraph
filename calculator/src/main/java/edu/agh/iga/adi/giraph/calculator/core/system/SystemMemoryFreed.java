package edu.agh.iga.adi.giraph.calculator.core.system;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class SystemMemoryFreed implements SystemMemoryEvent {
  MemoryHandle handle;
  Memory memory;
}
