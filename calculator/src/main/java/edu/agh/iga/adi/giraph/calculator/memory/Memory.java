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
  public static final Memory ONE_KB_MEMORY = kilobytes(1);
  public static final Memory ONE_MB_MEMORY = megabytes(1);
  public static final Memory ONE_GB_MEMORY = gigabytes(1);

  private static final int THREE_ORDERS = 1024;
  private static final int ONE_MB = THREE_ORDERS * THREE_ORDERS;

  private int bytes;

  public static Memory bytes(int b) {
    return new Memory(b);
  }

  public static Memory kilobytes(int kb) {
    return bytes(THREE_ORDERS).times(kb);
  }

  public static Memory megabytes(int mb) {
    return kilobytes(THREE_ORDERS).times(mb);
  }

  public static Memory gigabytes(int gb) {
    return megabytes(THREE_ORDERS).times(gb);
  }

  public boolean isNotGraterThan(Memory other) {
    return bytes <= other.bytes;
  }

  public Memory times(int times) {
    return bytes(bytes * times);
  }

  public static Memory sum(Memory left, Memory right) {
    return bytes(left.bytes + right.bytes);
  }

}
