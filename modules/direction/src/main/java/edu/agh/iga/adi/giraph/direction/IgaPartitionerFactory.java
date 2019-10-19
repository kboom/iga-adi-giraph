package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.partition.GraphPartitionerFactory;
import org.apache.giraph.partition.SimpleLongRangePartitionerFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;

/**
 * As our operations go up and down the problem tree we can greatly benefit from separating a sub-trees
 * out of the original problem tree and assign them to the workers. This way the communication between
 * the workers happens only at the boundaries of the trees which is relatively infrequent.
 *
 * @see SimpleLongRangePartitionerFactory
 */
public class IgaPartitionerFactory extends GraphPartitionerFactory<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Logger LOG = Logger.getLogger(IgaPartitionerFactory.class);

  private DirectionTree directionTree;

  @Override
  public int getPartition(LongWritable id, int partitionCount, int workerCount) {
    // todo significant pressure on memory
    IgaVertex igaVertex = vertexOf(directionTree, id.get());
    val partition = getPartitionInRange(igaVertex.offsetLeft(), igaVertex.strengthOf(), partitionCount); // todo this
    if (LOG.isTraceEnabled()) {
      LOG.trace(String.format("Partition for vertex: %s,%d", igaVertex, partition));
    }
    return partition;
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
