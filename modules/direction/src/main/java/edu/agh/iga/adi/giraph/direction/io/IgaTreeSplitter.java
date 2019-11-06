package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.math.IntMath.log2;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static java.math.RoundingMode.UNNECESSARY;
import static java.util.stream.Collectors.collectingAndThen;

final class IgaTreeSplitter {

  private final DirectionTree tree;

  IgaTreeSplitter(DirectionTree tree) {
    this.tree = tree;
  }

  List<IgaInputSplit> allSplitsFor(int partitionCountHint) {
    val treeHeight = tree.height();
    val leavesStrength = tree.strengthOfLeaves();
    val partitions = optimalPartitionCount(partitionCountHint);
    val leavesPerPartition = leavesStrength / partitions;
    val bottomTreeHeight = leavesPerPartition > 1 ? log2((leavesPerPartition + 1) / 3, UNNECESSARY) + 1 : 1;
    val tipTreeHeight = treeHeight - bottomTreeHeight;

    return Stream.concat(
        rootSplitIfApplicable(tipTreeHeight),
        leafSplits(partitions, bottomTreeHeight, tipTreeHeight)
    ).collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
  }

  private Stream<IgaInputSplit> rootSplitIfApplicable(int tipTreeHeight) {
    return tipTreeHeight > 0 ? Stream.of(rootInputSplit(tipTreeHeight)) : Stream.empty();
  }

  private Stream<IgaInputSplit> leafSplits(int partitions, int bottomTreeHeight, int tipTreeHeight) {
    val firstIndexOfRow = tree.firstIndexOfRow(tipTreeHeight + 1);
    return IntStream.range(0, partitions)
        .mapToObj(p -> leafInputSplit(bottomTreeHeight, firstIndexOfRow + p));
  }

  private IgaInputSplit leafInputSplit(int bottomTreeHeight, int firstIndexOfTipRow) {
    return new IgaInputSplit(vertexOf(tree, firstIndexOfTipRow), bottomTreeHeight);
  }

  private IgaInputSplit rootInputSplit(int tipTreeHeight) {
    return new IgaInputSplit(vertexOf(tree, 1), tipTreeHeight);
  }

  private int optimalPartitionCount(int partitionCountHint) {
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
