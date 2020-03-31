package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.PartitioningStrategy.partitioningStrategy;
import static java.util.stream.Collectors.collectingAndThen;

final class IgaTreeSplitter {

  private final DirectionTree tree;

  IgaTreeSplitter(DirectionTree tree) {
    this.tree = tree;
  }

  List<IgaInputSplit> allSplitsFor(int partitionCountHint) {
    val partitionStrategy = partitioningStrategy(tree, partitionCountHint, 1);

    // splits have to be assigned to workers, doesn't matter to which threads as no computation takes place there
    return Stream.concat(
        rootSplitIfApplicable(partitionStrategy.getPartitioningThreadLevel()),
        leafSplits(
            partitionStrategy.getPartitions(),
            partitionStrategy.getTree().height() - partitionStrategy.getPartitioningThreadLevel(),
            partitionStrategy.getPartitioningThreadLevel()
        )
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



}
