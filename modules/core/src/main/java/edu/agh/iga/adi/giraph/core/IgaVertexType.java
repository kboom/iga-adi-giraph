package edu.agh.iga.adi.giraph.core;

import com.google.common.math.LongMath;

import static java.lang.String.format;
import static java.math.RoundingMode.FLOOR;

public enum IgaVertexType {
  ROOT('R'),
  INTERIM('I'),
  BRANCH('B'),
  LEAF('L');

  char code;

  IgaVertexType(char code) {
    this.code = code;
  }

  public static IgaVertexType vertexType(final DirectionTree tree, final long vid) {
    if (vid == 1) {
      return ROOT;
    }
    if (vid < tree.firstIndexOfBranchingRow()) {
      return INTERIM;
    }
    if (vid < tree.firstIndexOfLeafRow()) {
      return BRANCH;
    }
    if (vid <= tree.lastIndexOfLeafRow()) {
      return LEAF;
    }
    throw new IllegalStateException(format("The problem tree does not have vertex %d", vid));
  }

  public long offsetLeft(final DirectionTree directionTree, final long vid) {
    return vid - directionTree.firstIndexOfRow(rowIndexOf(directionTree, vid));
  }

  public long strengthOf(DirectionTree directionTree, long vid) {
    return directionTree.strengthOfRow(rowIndexOf(directionTree, vid));
  }

  public int rowIndexOf(DirectionTree directionTree, long vid) {
    if (this == ROOT) {
      return 1;
    }
    if (this == LEAF) {
      return directionTree.leafHeight();
    }
    if (this == BRANCH) {
      return directionTree.branchingHeight();
    }
    return LongMath.log2(vid, FLOOR) + 1;
  }

  public String describe(DirectionTree directionTree, long vid) {
    return String.format(
        "%0" + maxDigits(directionTree) + "d[%s][R=%0" + maxDigitsHeight(directionTree) +"d][O=%0" + maxDigits(directionTree) + "d]",
        vid,
        code,
        rowIndexOf(directionTree, vid),
        offsetLeft(directionTree, vid));
  }

  private static int maxDigitsHeight(DirectionTree directionTree) {
    return String.valueOf(directionTree.height()).length();
  }

  private static int maxDigits(DirectionTree directionTree) {
    return String.valueOf(directionTree.size()).length();
  }

}
