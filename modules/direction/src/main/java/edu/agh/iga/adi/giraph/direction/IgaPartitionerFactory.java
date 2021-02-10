package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.partition.GraphPartitionerFactory;
import org.apache.giraph.partition.SimpleLongRangePartitionerFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.IgaVertexType.vertexType;
import static edu.agh.iga.adi.giraph.direction.PartitioningStrategy.partitioningStrategy;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;

/**
 * As our operations go up and down the problem tree we can greatly benefit from separating a sub-trees
 * out of the original problem tree and assign them to the workers. This way the communication between
 * the workers happens only at the boundaries of the trees which is relatively infrequent.
 *
 * @see SimpleLongRangePartitionerFactory
 */
public class IgaPartitionerFactory extends GraphPartitionerFactory<IntWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Logger LOG = Logger.getLogger(IgaPartitionerFactory.class);

  private DirectionTree directionTree;

  /**
   * The partitioning strategy is valid only for a given partition hint.
   * As we don't allow dynamic changes to the cluster we can assume it is never changed once assigned.
   * Otherwise it would need to be in a concurrent map which decreases performance.
   */
  private PartitioningStrategy partitioningStrategy;

  @Override
  public int getPartition(IntWritable id, int partitionCount, int workerCount) {
    val vid = id.get();

    assumePartitioningStrategy(partitionCount, workerCount);

    val partition = partitioningStrategy.partitionFor(vid);

    if (LOG.isTraceEnabled()) {
      val vertexType = vertexType(directionTree, vid);
      LOG.trace(String.format("P-> %s,%d", vertexType.describe(directionTree, vid), partition));
    }
    return partition;
  }

  private void assumePartitioningStrategy(int partitionCount, int workerCount) {
    if(partitioningStrategy == null) {
      synchronized (this) {
        if(partitioningStrategy == null) {
          partitioningStrategy = partitioningStrategy(
              directionTree,
              partitionCount,
              workerCount
          );
        }
      }
    }
  }

  @Override
  public int getWorker(int partition, int partitionCount, int workerCount) {
    return getPartitionInRange(partition, partitionCount, workerCount);
  }

  @Override
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    super.setConf(conf);
    directionTree = new CachedDirectionTree(PROBLEM_SIZE.get(conf));
  }

}
