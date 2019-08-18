package edu.agh.iga.adi.giraph.direction.io;

import com.google.common.collect.ImmutableList;
import edu.agh.iga.adi.giraph.core.DirectionTree;

import java.util.Iterator;
import java.util.List;
import java.util.stream.LongStream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static java.lang.Math.max;
import static java.lang.Math.min;

final class IgaTreeSplitter {

  private final DirectionTree tree;

  IgaTreeSplitter(DirectionTree tree) {
    this.tree = tree;
  }

  List<IgaInputSplit> allSplitsFor(int heightPartitionsHint) {
    final int treeHeight = tree.height();
    final int heightPerSegment = min(treeHeight, max(1, treeHeight / heightPartitionsHint + 1));

    ImmutableList.Builder<IgaInputSplit> builder = ImmutableList.builder();
    int cheight = treeHeight;
    while (cheight >= heightPerSegment) {
      cheight -= heightPerSegment;
      builder.addAll(inputSplitsFor(cheight, heightPerSegment));
    }

    if (cheight > 0) {
      builder.add(new IgaInputSplit(vertexOf(tree, 1L), cheight));
    }

    return builder.build();
  }

  private Iterator<IgaInputSplit> inputSplitsFor(int height, int heightPerSegment) {
    final long firstIndex = tree.firstIndexOfRow(height + 1);
    final long lastIndex = tree.lastIndexOfRow(height + 1);

    return LongStream.range(firstIndex, lastIndex + 1)
        .boxed()
        .map(i -> new IgaInputSplit(vertexOf(tree, i), heightPerSegment))
        .iterator();
  }

}
