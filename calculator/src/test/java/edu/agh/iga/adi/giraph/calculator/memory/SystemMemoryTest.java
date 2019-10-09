package edu.agh.iga.adi.giraph.calculator.memory;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.assertj.EitherAssertions.assertThatEither;
import static edu.agh.iga.adi.giraph.calculator.memory.DummyHandles.DUMMY_MEMORY_HANDLE;
import static edu.agh.iga.adi.giraph.calculator.memory.Memory.ONE_GB_MEMORY;
import static edu.agh.iga.adi.giraph.calculator.memory.Memory.ONE_MB_MEMORY;
import static edu.agh.iga.adi.giraph.calculator.memory.SystemMemory.systemMemory;
import static org.assertj.core.api.Assertions.assertThat;

class SystemMemoryTest {

  private static final SystemMemory INITIAL_SYSTEM_MEMORY = systemMemory(
      SystemMemoryCreated.builder()
          .totalMemory(ONE_GB_MEMORY)
          .build()
  );

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

}