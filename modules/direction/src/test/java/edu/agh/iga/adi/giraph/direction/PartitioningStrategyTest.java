package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.PartitioningStrategy.partitioningStrategy;
import static org.assertj.core.api.Assertions.assertThat;

final class PartitioningStrategyTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);

  @Test
  void partitionForRootShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 1).partitionFor(1))
        .isEqualTo(0);
  }

  @Test
  void partitionForLeaf8ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(8))
        .isEqualTo(0);
  }

  @Test
  void partitionForLeaf13ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(13))
        .isEqualTo(0);
  }

  @Test
  void partitionForLeaf14ShouldBe1() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(14))
        .isEqualTo(1);
  }

  @Test
  void partitionForBranch4ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(4))
        .isEqualTo(0);
  }

  @Test
  void partitionForBranch5ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(5))
        .isEqualTo(0);
  }

  @Test
  void partitionForBranch6ShouldBe1() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(6))
        .isEqualTo(1);
  }

  @Test
  void partitionForBranch7ShouldBe1() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(7))
        .isEqualTo(1);
  }

}