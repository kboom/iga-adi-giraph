package edu.agh.iga.adi.giraph.calculator.core.system;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class IdMemoryHandle implements MemoryHandle {
  final String namespace;
  final int id;
}
