package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.partition.GraphPartitionerFactory;
import org.apache.giraph.partition.SimpleLongRangePartitionerFactory;
import org.apache.hadoop.io.LongWritable;

import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;

/**
 * As our operations go up and down the problem tree we can greatly benefit from separating a sub-trees
 * out of the original problem tree and assign them to the workers. This way the communication between
 * the workers happens only at the boundaries of the trees which is relatively infrequent.
 *
 * @see SimpleLongRangePartitionerFactory
 */
public class IgaPartitionerFactory extends GraphPartitionerFactory<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private DirectionTree directionTree;

  @Override
  public int getPartition(LongWritable id, int partitionCount, int workerCount) {
    IgaVertex igaVertex = vertexOf(directionTree, id.get());
    return getPartitionInRange(igaVertex.offsetLeft(), igaVertex.strengthOf(), partitionCount); // todo this might be inaccurate for certain partition sizes compared to problem sizes
  }

  @Override
  public int getWorker(int partition, int partitionCount, int workerCount) {
    return getPartitionInRange(partition, partitionCount, workerCount);
  }

  @Override
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    super.setConf(conf);
    directionTree = new DirectionTree(PROBLEM_SIZE.get(conf));
  }

}
