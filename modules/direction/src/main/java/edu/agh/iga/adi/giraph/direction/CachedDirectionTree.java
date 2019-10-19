package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;

public final class CachedDirectionTree extends DirectionTree {

  int height = -1;
  int branchingHeight = -1;
  long firstIndexOfLeafRow = -1;
  long lastIndexOfLeafRow = -1;
  long firstIndexOfBranchingRow = -1;
  long lastIndexOfBranchingRow = -1;
  long strengthOfLeaves = -1;
  int size = -1;

  public CachedDirectionTree(int problemSize) {
    super(problemSize);
  }

  @Override
  public int height() {
    if (height == -1) {
      synchronized (this) {
        if (height == -1) {
          height = super.height();
        }
      }
    }
    return height;
  }

  @Override
  public int branchingHeight() {
    if (branchingHeight == -1) {
      synchronized (this) {
        if (branchingHeight == -1) {
          branchingHeight = super.branchingHeight();
        }
      }
    }
    return branchingHeight;
  }

  @Override
  public long firstIndexOfLeafRow() {
    if (firstIndexOfLeafRow == -1) {
      synchronized (this) {
        if (firstIndexOfLeafRow == -1) {
          firstIndexOfLeafRow = super.firstIndexOfLeafRow();
        }
      }
    }
    return firstIndexOfLeafRow;
  }

  @Override
  public long lastIndexOfLeafRow() {
    if (lastIndexOfLeafRow == -1) {
      synchronized (this) {
        if (lastIndexOfLeafRow == -1) {
          lastIndexOfLeafRow = super.lastIndexOfLeafRow();
        }
      }
    }
    return lastIndexOfLeafRow;
  }

  @Override
  public long firstIndexOfBranchingRow() {
    if (firstIndexOfBranchingRow == -1) {
      synchronized (this) {
        if (firstIndexOfBranchingRow == -1) {
          firstIndexOfBranchingRow = super.firstIndexOfBranchingRow();
        }
      }
    }
    return firstIndexOfBranchingRow;
  }

  @Override
  public long lastIndexOfBranchingRow() {
    if (lastIndexOfBranchingRow == -1) {
      synchronized (this) {
        if (lastIndexOfBranchingRow == -1) {
          lastIndexOfBranchingRow = super.lastIndexOfBranchingRow();
        }
      }
    }
    return lastIndexOfBranchingRow;
  }

  @Override
  public long strengthOfLeaves() {
    if (strengthOfLeaves == -1) {
      synchronized (this) {
        if (strengthOfLeaves == -1) {
          strengthOfLeaves = super.strengthOfLeaves();
        }
      }
    }
    return strengthOfLeaves;
  }

  @Override
  public int size() {
    if (size == -1) {
      synchronized (this) {
        if (size == -1) {
          size = super.size();
        }
      }
    }
    return size;
  }

}
