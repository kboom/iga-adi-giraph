package edu.agh.iga.adi.giraph.core.operations.setup;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.ojalgo.structure.Access2D;

final class PartitionProvider implements Access2D<Double> {

  private static final double[] FIRST_PARTITION = new double[] {1d, 1 / 2d, 1 / 3d};
  private static final double[] SECOND_PARTITION = new double[] {1 / 2d, 1 / 3d, 1 / 3d};
  private static final double[] THIRD_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 3d};
  private static final double[] MIDDLE_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 3d};
  private static final double[] THIRD_TO_LAST_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 3d};
  private static final double[] SECOND_TO_LAST_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 2d};
  private static final double[] LAST_PARTITION = new double[] {1 / 3d, 1 / 2d, 1d};

  private final long vid;
  private final int cols;
  private final DirectionTree tree;

  PartitionProvider(long vid, DirectionTree tree, int cols) {
    this.vid = vid;
    this.cols = cols;
    this.tree = tree;
  }

  @Override
  public double doubleValue(long row, long col) {
    return partitionFor((int) row);
  }

  @Override
  public Double get(long row, long col) {
    return doubleValue(row);
  }

  @Override
  public long countColumns() {
    return cols;
  }

  @Override
  public long countRows() {
    return 3;
  }

  private double partitionFor(int localRow) {
    if (vid == tree.firstIndexOfLeafRow()) {
      return FIRST_PARTITION[localRow];
    }
    if (vid == tree.firstIndexOfLeafRow() + 1) {
      return SECOND_PARTITION[localRow];
    }
    if (vid == tree.firstIndexOfLeafRow() + 2) {
      return THIRD_PARTITION[localRow];
    }
    if (vid == tree.lastIndexOfLeafRow() - 2) {
      return THIRD_TO_LAST_PARTITION[localRow];
    }
    if (vid == tree.lastIndexOfLeafRow() - 1) {
      return SECOND_TO_LAST_PARTITION[localRow];
    }
    if (vid == tree.lastIndexOfLeafRow()) {
      return LAST_PARTITION[localRow];
    }
    return MIDDLE_PARTITION[localRow];
  }

}
