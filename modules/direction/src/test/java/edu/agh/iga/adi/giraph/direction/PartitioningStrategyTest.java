package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.PartitioningStrategy.partitioningStrategy;
import static org.assertj.core.api.Assertions.assertThat;

final class PartitioningStrategyTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);
  private static final DirectionTree TREE_6144 = new DirectionTree(6144);

  @Test
  void partitionTree12ForRootShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 1).partitionFor(1))
        .isEqualTo(0);
  }

  @Test
  void partitionTree12ForLeaf8ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(8))
        .isEqualTo(0);
  }

  @Test
  void partitionTree12ForLeaf13ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(13))
        .isEqualTo(0);
  }

  @Test
  void partitionTree12ForLeaf14ShouldBe1() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(14))
        .isEqualTo(1);
  }

  @Test
  void partitionTree12ForBranch4ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(4))
        .isEqualTo(0);
  }

  @Test
  void partitionTree12ForBranch5ShouldBe0() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(5))
        .isEqualTo(0);
  }

  @Test
  void partitionTree12ForBranch6ShouldBe1() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(6))
        .isEqualTo(1);
  }

  @Test
  void partitionTree12ForBranch7ShouldBe1() {
    assertThat(partitioningStrategy(TREE_12, 2).partitionFor(7))
        .isEqualTo(1);
  }

  @Test
  void partitionTree6144ForRootAnd64PartitionsShouldBe0() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(1))
            .isEqualTo(0);
  }

  @Test
  void partitionTree6144ForLeftmostLeafAnd64PartitionsShouldBe0() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(4096))
            .isEqualTo(0);
  }

  @Test
  void partitionTree6144ForRightmostLeafAnd64PartitionsShouldBe63() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(10239))
            .isEqualTo(63);
  }

  @Test
  void partitionTree6144For64And64PartitionsShouldBe0() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(64))
            .isEqualTo(0);
  }

  @Test
  void partitionTree6144For65And64PartitionsShouldBe1() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(65))
            .isEqualTo(1);
  }

  @Test
  void partitionTree6144For66And64PartitionsShouldBe2() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(66))
            .isEqualTo(2);
  }

  @Test
  void partitionTree6144For127And64PartitionsShouldBe2() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(127))
            .isEqualTo(63);
  }

  @Test
  void partitionTree6144For63And64PartitionsShouldBe0() {
    assertThat(partitioningStrategy(TREE_6144, 64).partitionFor(63))
            .isEqualTo(0);
  }

  @Test
  void tipForTree12And1PartitionIs3() {
    assertThat(partitioningStrategy(TREE_12, 1).getTipHeight()).isEqualTo(0);
  }

  @Test
  void tipForTree6144And1PartitionIs12() {
    assertThat(partitioningStrategy(TREE_6144, 1).getTipHeight()).isEqualTo(0);
  }

  @Test
  void tipForTree12And2PartitionIs1() {
    assertThat(partitioningStrategy(TREE_12, 2).getTipHeight()).isEqualTo(1);
    assertThat(partitioningStrategy(TREE_12, 2).getBottomHeight()).isEqualTo(2);
  }

  @Test
  void tipForTree12And4PartitionIs2() {
    assertThat(partitioningStrategy(TREE_12, 4).getTipHeight()).isEqualTo(2);
    assertThat(partitioningStrategy(TREE_12, 4).getBottomHeight()).isEqualTo(1);
  }

  @Test
  void tipForTree12And8PartitionIs3() {
    assertThat(partitioningStrategy(TREE_12, 8).getTipHeight()).isEqualTo(2);
    assertThat(partitioningStrategy(TREE_12, 8).getBottomHeight()).isEqualTo(1);
  }

}