package edu.agh.iga.adi.giraph.core;

import com.google.common.math.LongMath;
import lombok.val;

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

  public int offsetLeft(final DirectionTree directionTree, final int vid) {
    return vid - directionTree.firstIndexOfRow(rowIndexOf(directionTree, vid));
  }

  public int strengthOf(DirectionTree directionTree, int vid) {
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

  public String describe(DirectionTree directionTree, int vid) {
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

  public boolean isLeading(DirectionTree tree, int id) {
    return offsetLeft(tree, id) == 0;
  }

  public int nthParent(DirectionTree tree, int vid, int distanceUp) {
    if(this == ROOT) {
      return vid;
    }
    if(this == LEAF) {
      throw new IllegalStateException("Should not be called for a leaf");
    }
    val childRowIndex = rowIndexOf(tree, vid);
    val parentRowIndex = childRowIndex - distanceUp;
    val strengthOfChildRow = tree.strengthOfRow(childRowIndex);
    val offsetLeft = vid - tree.firstIndexOfRow(childRowIndex);
    val proportion = offsetLeft / (float) strengthOfChildRow;
    val firstIndexOfParentRow = (int) Math.pow(2, parentRowIndex - 1);
    val strengthOfParentRow = tree.strengthOfRow(parentRowIndex);
    return firstIndexOfParentRow + (int) (proportion * strengthOfParentRow);
  }

}
