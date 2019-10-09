package edu.agh.iga.adi.giraph.calculator.memory;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.calculator.memory.Memory.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryTest {

  @Test
  void oneKilobyteIsEqualTo1024Bytes() {
    assertThat(ONE_KB_MEMORY).isEqualTo(bytes(1024));
  }

  @Test
  void oneMegabyteIsEqualTo1024Kilobytes() {
    assertThat(ONE_MB_MEMORY).isEqualTo(kilobytes(1024));
  }

  @Test
  void oneGigabyteIsEqualTo1024Megabytes() {
    assertThat(ONE_GB_MEMORY).isEqualTo(megabytes(1024));
  }

}