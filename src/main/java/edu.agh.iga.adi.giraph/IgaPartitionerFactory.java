package edu.agh.iga.adi.giraph;

import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.partition.GraphPartitionerFactory;
import org.apache.giraph.partition.SimpleLongRangePartitionerFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;

import static java.lang.String.format;
import static org.apache.giraph.conf.GiraphConstants.PARTITION_VERTEX_KEY_SPACE_SIZE;

/**
 * As our operations go up and down the problem tree we can greatly benefit from separating a sub-trees
 * out of the original problem tree and assign them to the workers. This way the communication between
 * the workers happens only at the boundaries of the trees which is relatively infrequent.
 *
 * @see SimpleLongRangePartitionerFactory
 */
public class IgaPartitionerFactory<V extends Writable, E extends Writable>
    extends GraphPartitionerFactory<LongWritable, V, E> {

  private int partitionSize;

  @Override
  public int getPartition(LongWritable id, int partitionCount, int workerCount) {
    return 0;
  }

  @Override
  public int getWorker(int partition, int partitionCount, int workerCount) {
    return 0;
  }

  @Override
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    super.setConf(conf);
    partitionSize = conf.getInt(PARTITION_VERTEX_KEY_SPACE_SIZE, -1);
    if (partitionSize == -1) {
      throw new IllegalStateException(
          format("Need to specify %s when using IgaPartitionerFactory", PARTITION_VERTEX_KEY_SPACE_SIZE)
      );
    }
  }

}
