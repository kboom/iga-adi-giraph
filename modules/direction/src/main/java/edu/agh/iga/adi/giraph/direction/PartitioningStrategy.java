package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import lombok.Builder;
import lombok.Value;
import lombok.val;

import static com.google.common.math.IntMath.log2;
import static edu.agh.iga.adi.giraph.core.IgaVertexType.vertexType;
import static java.math.RoundingMode.UNNECESSARY;

@Builder
@Value
public class PartitioningStrategy {

  DirectionTree tree;
  int tipHeight;
  int bottomHeight;
  int partitions;

  public int partitionFor(int vid) {
    val vt = vertexType(tree, vid);
    val ri = vt.rowIndexOf(tree, vid);
    if(ri - 1 <= tipHeight) {
      return 0;
    } else {
      val verticesPerPartition = vt.strengthOf(tree, vid) / partitions;
      return vt.offsetLeft(tree, vid) / verticesPerPartition;
    }
  }

  public static PartitioningStrategy partitioningStrategy(
      DirectionTree tree,
      int partitionCountHint
  ) {
    val treeHeight = tree.height();
    val leavesStrength = tree.strengthOfLeaves();
    val partitions = optimalPartitionCount(tree, partitionCountHint);
    val leavesPerPartition = leavesStrength / partitions;
    val bottomTreeHeight = leavesPerPartition > 1 ? log2((leavesPerPartition + 1) / 3, UNNECESSARY) + 1 : 1;
    val tipTreeHeight = treeHeight - bottomTreeHeight;

    return PartitioningStrategy.builder()
        .tree(tree)
        .tipHeight(tipTreeHeight)
        .bottomHeight(bottomTreeHeight)
        .partitions(partitions)
        .build();
  }

  private static int optimalPartitionCount(
      DirectionTree tree,
      int partitionCountHint
  ) {
    val branchStrength =  tree.strengthOfLeaves() / 3;
    if (branchStrength / partitionCountHint < 1) {
      return branchStrength;
    } else if (partitionCountHint == 1 || partitionCountHint % 2 == 0) {
      return partitionCountHint;
    } else {
      return 2 * partitionCountHint;
    }
  }

}
