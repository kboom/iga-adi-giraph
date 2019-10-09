package edu.agh.iga.adi.giraph.calculator.memory;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.assertj.EitherAssertions.assertThatEither;
import static edu.agh.iga.adi.giraph.calculator.memory.DummyHandles.DUMMY_MEMORY_HANDLE;
import static edu.agh.iga.adi.giraph.calculator.memory.Memory.*;
import static edu.agh.iga.adi.giraph.calculator.memory.SystemMemory.systemMemory;
import static org.assertj.core.api.Assertions.assertThat;

class SystemMemoryTest {

  private static final SystemMemory INITIAL_SYSTEM_MEMORY = systemMemory(
      SystemMemoryCreated.builder()
          .totalMemory(ONE_GB_MEMORY)
          .build()
  );

  private static final SystemMemoryAllocated QUARTER_ALLOCATION = SystemMemoryAllocated.builder()
      .memory(ONE_KB_MEMORY.times(256))
      .handle(DUMMY_MEMORY_HANDLE)
      .build();

  private static final SystemMemory ALLOCATED_SYSTEM_MEMORY = systemMemory(
      SystemMemoryCreated.builder()
          .totalMemory(ONE_GB_MEMORY)
          .build()
  ).apply(QUARTER_ALLOCATION).get();

  @Test
  void canAllocateMemory() {
    assertThatEither(INITIAL_SYSTEM_MEMORY.allocate(ONE_MB_MEMORY, DUMMY_MEMORY_HANDLE))
        .hasRight(event -> assertThat(event)
            .isEqualToComparingFieldByField(
                SystemMemoryAllocated.builder()
                    .handle(DUMMY_MEMORY_HANDLE)
                    .memory(ONE_MB_MEMORY)
                    .build()
            )
        );
  }

  @Test
  void canFreeMemory() {
    assertThatEither(ALLOCATED_SYSTEM_MEMORY.free(DUMMY_MEMORY_HANDLE))
        .hasRight(event -> assertThat(event)
            .isEqualToComparingFieldByField(
                new SystemMemoryFreed(DUMMY_MEMORY_HANDLE)
            )
        );
  }

}