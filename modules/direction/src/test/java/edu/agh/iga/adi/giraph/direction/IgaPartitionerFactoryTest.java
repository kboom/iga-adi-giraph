package edu.agh.iga.adi.giraph.direction;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.hadoop.io.IntWritable;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.igaConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

class IgaPartitionerFactoryTest {

  private static final IgaPartitionerFactory PARTITIONER_FACTORY = new IgaPartitionerFactory();

  @Test
  void assignsLeaf8ToPartition0() {
    setProblemSize(12);
    assertThat(PARTITIONER_FACTORY.getPartition(new IntWritable(8), 2, 2)).isEqualTo(0);
  }

  @Test
  void assignsLeaf13ToPartition0() {
    setProblemSize(12);
    assertThat(PARTITIONER_FACTORY.getPartition(new IntWritable(13), 2, 2)).isEqualTo(0);
  }

  @Test
  void assignsLeaf14ToPartition1() {
    setProblemSize(12);
    assertThat(PARTITIONER_FACTORY.getPartition(new IntWritable(14), 2, 2)).isEqualTo(1);
  }

  @Test
  void assignsLeaf19ToPartition1() {
    setProblemSize(12);
    assertThat(PARTITIONER_FACTORY.getPartition(new IntWritable(19), 2, 2)).isEqualTo(1);
  }

  @Test
  void assignsPartition0ToWorker0() {
    setProblemSize(12);
    assertThat(PARTITIONER_FACTORY.getWorker(0, 2, 2)).isEqualTo(0);
  }

  @Test
  void assignsPartition1ToWorker1() {
    setProblemSize(12);
    assertThat(PARTITIONER_FACTORY.getWorker(1, 2, 2)).isEqualTo(1);
  }

  @Test
  void assignsPartition16ToWorker0When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(16, 4, 4)).isEqualTo(0);
  }

  @Test
  void assignsPartition32ToWorker0When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(32, 4, 4)).isEqualTo(0);
  }

  @Test
  void assignsPartition20ToWorker0When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(20, 4, 4)).isEqualTo(0);
  }

  @Test
  void assignsPartition21ToWorker1When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(21, 4, 4)).isEqualTo(1);
  }

  @Test
  void assignsPartition25ToWorker1When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(25, 4, 4)).isEqualTo(1);
  }

  @Test
  void assignsPartition26ToWorker2When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(26, 4, 4)).isEqualTo(2);
  }

  @Test
  void assignsPartition35ToWorker3When4PartitionsAndProblemSize48() {
    setProblemSize(48);
    assertThat(PARTITIONER_FACTORY.getWorker(35, 4, 4)).isEqualTo(3);
  }

  private static void setProblemSize(int problemSize) {
    GiraphConfiguration config = new GiraphConfiguration();
    PROBLEM_SIZE.set(config, problemSize);
    igaConfiguration(config);
    PARTITIONER_FACTORY.setConf(new ImmutableClassesGiraphConfiguration<>(config));
  }

}