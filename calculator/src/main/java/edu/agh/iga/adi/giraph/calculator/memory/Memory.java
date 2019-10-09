package edu.agh.iga.adi.giraph.calculator.memory;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

import static lombok.AccessLevel.PRIVATE;

@Value
@Wither
@RequiredArgsConstructor(access = PRIVATE)
public class Memory {

  public static final Memory ZERO_MEMORY = megabytes(0);
  public static final Memory ONE_MB_MEMORY = megabytes(1);
  public static final Memory ONE_GB_MEMORY = gigabytes(1);

  private static final int ONE_MB = 1024 * 1024;

  private int bytes;

  public static Memory megabytes(int mb) {
    return new Memory(mb * ONE_MB);
  }

  public static Memory gigabytes(int gb) {
    return new Memory(gb * ONE_MB * ONE_MB);
  }

  public int getMegabytes() {
    return bytes / ONE_MB;
  }

}
