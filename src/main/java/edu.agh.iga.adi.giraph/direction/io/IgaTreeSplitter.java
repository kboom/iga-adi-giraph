package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;

import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.*;
import static java.util.stream.Collectors.toList;

final class IgaTreeSplitter {

  private final DirectionTree tree;

  IgaTreeSplitter(DirectionTree tree) {
    this.tree = tree;
  }

  List<IgaInputSplit> allSplitsFor(int workers) {
    final int problemSize = tree.size();
    final int segments = problemSize / 3;
    assert segments % workers == 0; // enforce even number of nodes
    final int leafsPerThread = segments / workers;

    final Stack<List<IgaInputSplit>> splits = new Stack<>();
    splits.add(splitForSegment(tree.height(), segments, leafsPerThread));

    int clevel = tree.height();
    while (clevel > 0) {
      List<IgaInputSplit> lastSplits = splits.lastElement();
      clevel = lastSplits.get(0).getRoot().heightOf();
      int currentSegments = (int) tree.strengthOfRow(clevel);
      int verticesPerThread = Math.min(1, currentSegments / workers);
      splits.add(splitForSegment(tree.height(), currentSegments, verticesPerThread));
    }

    return splits.stream()
        .flatMap(Collection::stream)
        .collect(toList());
  }

  List<IgaInputSplit> splitForSegment(int level, int batches, int elementsInBatch) {
    final long firstIndex = tree.firstIndexOfRow(level);
    return IntStream.range(0, batches)
        .boxed()
        .map(thread -> {
          final IgaVertex left = vertexOf(tree, firstIndex + elementsInBatch * thread);
          final IgaVertex right = vertexOf(tree, firstIndex + elementsInBatch * (thread + 1) - 1);
          final IgaVertex lca = lcaOf(left, right);
          final int height = lca.heightDifference(lowerOf(left, right).rowIndexOf());
          return new IgaInputSplit(lca, height);
        })
        .collect(toList());
  }

}
