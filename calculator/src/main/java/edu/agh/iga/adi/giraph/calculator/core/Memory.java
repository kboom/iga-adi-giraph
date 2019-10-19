package edu.agh.iga.adi.giraph.calculator.core;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import lombok.val;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Math.round;
import static java.lang.String.valueOf;
import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;

@Value
@Wither
@RequiredArgsConstructor(access = PRIVATE)
public class Memory {

  public static final Memory INFINITE_MEMORY = bytes(MAX_VALUE);
  public static final Memory ZERO_MEMORY = megabytes(0);
  public static final Memory ONE_KB_MEMORY = kilobytes(1);
  public static final Memory ONE_MB_MEMORY = megabytes(1);
  public static final Memory ONE_GB_MEMORY = gigabytes(1);

  private static final int THREE_ORDERS = 1024;
  private static final int ONE_MB = THREE_ORDERS * THREE_ORDERS;

  private long bytes;

  public static Memory bytes(long b) {
    return new Memory(b);
  }

  public static Memory kilobytes(long kb) {
    return bytes(THREE_ORDERS).times(kb);
  }

  public static Memory megabytes(long mb) {
    return kilobytes(THREE_ORDERS).times(mb);
  }

  public static Memory gigabytes(long gb) {
    return megabytes(THREE_ORDERS).times(gb);
  }

  public boolean isNotGraterThan(Memory other) {
    return bytes <= other.bytes;
  }

  public Memory times(long times) {
    return bytes(bytes * times);
  }

  public Memory times(int times) {
    return times((long) times);
  }

  public static Memory sum(Memory first, Memory second, Memory... other) {
    return bytes(first.bytes + second.bytes + stream(other).mapToLong(Memory::getBytes).sum());
  }

  public Memory plus(Memory other) {
    return bytes(bytes + other.bytes);
  }

  public Memory minus(Memory other) {
    return bytes(bytes - other.bytes);
  }

  public static Memory greaterOf(Memory first, Memory second) {
    return first.bytes > second.bytes ? first : second;
  }

  public Memory divide(int pieces) {
    return bytes(bytes / pieces);
  }

  public String inGigabytes() {
    val digits = 100.0;
    return valueOf(round(((float) bytes / ONE_MB / THREE_ORDERS) * digits) / digits);
  }

  public String inMegabytes() {
    val digits = 100.0;
    return valueOf(round(((float) bytes / ONE_MB) * digits) / digits);
  }

}
