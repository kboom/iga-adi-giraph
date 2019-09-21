package edu.agh.iga.adi.giraph.direction;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.hadoop.io.LongWritable;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.IgaGiraphJobFactory.injectSolverConfiguration;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;
import static org.assertj.core.api.Assertions.assertThat;

class IgaPartitionerFactoryTest {

  private static final IgaPartitionerFactory PARTITIONER_FACTORY = new IgaPartitionerFactory();

  static {
    GiraphConfiguration config = new GiraphConfiguration();
    HEIGHT_PARTITIONS.set(config, 1);
    PROBLEM_SIZE.set(config, 12);
    injectSolverConfiguration(config);
    PARTITIONER_FACTORY.setConf(new ImmutableClassesGiraphConfiguration<>(config));
  }

  @Test
  void assignsLeaf8ToPartition0() {
    assertThat(PARTITIONER_FACTORY.getPartition(new LongWritable(8L), 2, 2)).isEqualTo(0);
  }

  @Test
  void assignsLeaf19ToPartition1() {
    assertThat(PARTITIONER_FACTORY.getPartition(new LongWritable(19L), 2, 2)).isEqualTo(1);
  }

  @Test
  void assignsPartition0ToWorker0() {
    assertThat(PARTITIONER_FACTORY.getWorker(0, 2, 2)).isEqualTo(0);
  }

  @Test
  void assignsPartition1ToWorker1() {
    assertThat(PARTITIONER_FACTORY.getWorker(1, 2, 2)).isEqualTo(1);
  }

}