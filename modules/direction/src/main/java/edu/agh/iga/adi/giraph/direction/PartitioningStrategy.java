package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import lombok.Builder;
import lombok.Value;
import lombok.val;

import static com.google.common.math.DoubleMath.log2;
import static edu.agh.iga.adi.giraph.core.IgaVertexType.vertexType;
import static java.util.stream.IntStream.rangeClosed;

@Builder
@Value
public class PartitioningStrategy {

  DirectionTree tree;

  int partitions;
  int partitioningThreadLevel;
  int partitioningWorkerLevel;
  int partitionsPerWorker;

  // for better efficiency
  int [] rowFirstIndices;
  int [] verticesPerPartitionByRow;

  // x  w1 w2 w3
  // 0  32 64 34
  int [] partitionBoundaryOwnership;

  public int partitionFor(int vid) {
    val vt = vertexType(tree, vid);
    val ri = vt.rowIndexOf(tree, vid) - 1;
    if (ri >= partitioningThreadLevel) {
      return (vid - rowFirstIndices[ri]) / verticesPerPartitionByRow[ri]; // handled by a single thread up till the root
    }
    if (ri >= partitioningWorkerLevel) {
      // track till the root for partitioningWorkerLevel
      val parentId = vt.nthParent(tree, vid, ri - partitioningWorkerLevel);
      val workerIndex = parentId - rowFirstIndices[partitioningWorkerLevel];
      val partitionBoundaryLeft = partitionBoundaryOwnership[workerIndex];
      return partitionBoundaryLeft + vid % partitionsPerWorker;
    }
    return vid % partitionsPerWorker; // this is intentional round-robin as this is executed by a single worker
  }

  public static PartitioningStrategy partitioningStrategy(
          DirectionTree tree,
          int partitions,
          int workers
  ) {
    val treeHeight = tree.height();
    val threadHeight = log2(partitions);
    val workerHeight = log2(workers);
    val partitionsPerWorker = partitions / workers;

    val partitioningThreadLevel = (int) Math.min(threadHeight, tree.leafHeight());
    val partitioningWorkerLevel = (int) Math.min(workerHeight, partitioningThreadLevel);

    return PartitioningStrategy.builder()
        .tree(tree)
        .partitions(partitions)
        .rowFirstIndices(rangeClosed(0, treeHeight).map(h -> (int) Math.pow(2, h)).toArray())
        .partitionBoundaryOwnership(rangeClosed(0, workers).map(w -> partitionsPerWorker * w).toArray())
        .partitioningThreadLevel(partitioningThreadLevel)
        .partitioningWorkerLevel(partitioningWorkerLevel)
        .partitionsPerWorker(partitionsPerWorker)
        .verticesPerPartitionByRow(rangeClosed(1, treeHeight + 1).map(h -> tree.strengthOfRow(h) / partitions).toArray())
        .build();
  }

}
