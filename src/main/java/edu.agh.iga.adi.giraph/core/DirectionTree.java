package edu.agh.iga.adi.giraph.core;

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

  int leafHeight() {
    return branchingHeight() + 1;
  }

  int branchingHeight() {
    return log2(height / 3, UNNECESSARY) + 1;
  }

  long firstIndexOfRow(int level) {
    return pow(2, level - 1);
  }

  long firstIndexOfLeafRow() {
    return firstIndexOfRow(leafHeight());
  }

  long lastIndexOfLeafRow() {
    return firstIndexOfLeafRow() + strengthOfRow(leafHeight()) - 1;
  }

  long firstIndexOfBranchingRow() {
    return firstIndexOfRow(branchingHeight());
  }

  long lastIndexOfBranchingRow() {
    return firstIndexOfBranchingRow() + strengthOfRow(branchingHeight()) - 1;
  }

  long strengthOfRow(int level) {
    if (level < leafHeight()) {
      return pow(2, level - 1);
    } else {
      return 3 * pow(2, level - 2);
    }
  }

  long strengthOfLeaves() {
    return strengthOfRow(leafHeight());
  }

}
