package edu.agh.iga.adi.giraph.core.operations.transposition;

import edu.agh.iga.adi.giraph.core.IgaVertex;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.ojalgo.structure.Access2D;

import static java.lang.Long.MAX_VALUE;

@RequiredArgsConstructor
final class PartitionProvider implements Access2D<Double> {

  private static final double[] FIRST_PARTITION = new double[] {1d, 1 / 2d, 1 / 3d};
  private static final double[] SECOND_PARTITION = new double[] {1 / 2d, 1 / 3d, 1 / 3d};
  private static final double[] THIRD_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 3d};
  private static final double[] MIDDLE_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 3d};
  private static final double[] THIRD_TO_LAST_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 3d};
  private static final double[] SECOND_TO_LAST_PARTITION = new double[] {1 / 3d, 1 / 3d, 1 / 2d};
  private static final double[] LAST_PARTITION = new double[] {1 / 3d, 1 / 2d, 1d};

  private final IgaVertex v;
  private final int cols;

  @Override
  public double doubleValue(long row, long col) {
    return partitionFor(v, (int) row);
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

  /**
   * todo predetermine this rather than computing each time
   * @param v
   * @param localRow
   * @return
   */
  private static double partitionFor(IgaVertex v, int localRow) {
    val tree = v.getTree();
    val firstIdx = tree.firstIndexOfLeafRow();
    val lastIdx = tree.lastIndexOfLeafRow();
    val vid = v.id();

    if (vid == firstIdx) {
      return FIRST_PARTITION[localRow];
    }
    if (vid == firstIdx + 1) {
      return SECOND_PARTITION[localRow];
    }
    if (vid == firstIdx + 2) {
      return THIRD_PARTITION[localRow];
    }
    if (vid == lastIdx - 2) {
      return THIRD_TO_LAST_PARTITION[localRow];
    }
    if (vid == lastIdx - 1) {
      return SECOND_TO_LAST_PARTITION[localRow];
    }
    if (vid == lastIdx) {
      return LAST_PARTITION[localRow];
    }
    return MIDDLE_PARTITION[localRow];
  }

}
