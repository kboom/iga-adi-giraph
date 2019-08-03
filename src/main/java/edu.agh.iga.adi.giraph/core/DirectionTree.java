package edu.agh.iga.adi.giraph.core;

import com.google.common.math.LongMath;

import static com.google.common.math.IntMath.log2;
import static com.google.common.math.IntMath.pow;
import static java.math.RoundingMode.UNNECESSARY;

public final class DirectionTree {

  private final int height;

  public DirectionTree(int problemSize) {
    this.height = log2(problemSize / 3, UNNECESSARY) + 1;
  }

  public int height() {
    return height;
  }

  public int leafHeight() {
    return branchingHeight() + 1;
  }

  public int branchingHeight() {
    return log2(height / 3, UNNECESSARY) + 1;
  }

  public long firstIndexOfRow(int level) {
    return pow(2, level - 1);
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
      return pow(2, level - 1);
    } else {
      return 3 * pow(2, level - 2);
    }
  }

  public long strengthOfLeaves() {
    return strengthOfRow(leafHeight());
  }

  public long size() {
    return LongMath.pow(height - 1, 2) * 3;
  }
}
