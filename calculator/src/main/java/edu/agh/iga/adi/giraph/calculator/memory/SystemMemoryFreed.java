package edu.agh.iga.adi.giraph.calculator.memory;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class SystemMemoryFreed implements SystemMemoryEvent {
  MemoryHandle handle;
}
