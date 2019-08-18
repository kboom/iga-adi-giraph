package edu.agh.iga.adi.giraph.core;

import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import static java.math.RoundingMode.UNNECESSARY;

public final class DirectionTree {

  private final int problemSize;

  public DirectionTree(int problemSize) {
    this.problemSize = problemSize;
  }

  public int getProblemSize() {
    return problemSize;
  }

  public int height() {
    return IntMath.log2(problemSize / 3, UNNECESSARY) + 1;
  }

  public int leafHeight() {
    return branchingHeight() + 1;
  }

  public int branchingHeight() {
    return IntMath.log2(problemSize / 3, UNNECESSARY) + 1;
  }

  public long firstIndexOfRow(int level) {
    return IntMath.pow(2, level - 1);
  }

  public long lastIndexOfRow(int level) {
    return firstIndexOfRow(level) + strengthOfRow(level) - 1;
  }

  public long firstIndexOfLeafRow() {
    return firstIndexOfRow(leafHeight());
  }

  public long lastIndexOfLeafRow() {
    return firstIndexOfLeafRow() + strengthOfRow(leafHeight()) - 1;
  }

  public long firstIndexOfBranchingRow() {
    return firstIndexOfRow(branchingHeight());
  }

  public long lastIndexOfBranchingRow() {
    return firstIndexOfBranchingRow() + strengthOfRow(branchingHeight()) - 1;
  }

  public long strengthOfRow(int level) {
    if (level < leafHeight()) {
      return IntMath.pow(2, level - 1);
    } else {
      return 3 * IntMath.pow(2, level - 2);
    }
  }

  public long strengthOfLeaves() {
    return strengthOfRow(leafHeight());
  }

  public int size() {
    return (int) (LongMath.pow(problemSize - 1, 2) * 3);
  }
}
