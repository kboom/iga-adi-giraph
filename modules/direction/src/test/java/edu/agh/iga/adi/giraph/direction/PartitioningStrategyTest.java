package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import lombok.val;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.PartitioningStrategy.partitioningStrategy;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

final class PartitioningStrategyTest {

    private static final DirectionTree TREE_12 = new DirectionTree(12);
    private static final DirectionTree TREE_48 = new DirectionTree(48);
    private static final DirectionTree TREE_192 = new DirectionTree(192);
    private static final DirectionTree TREE_6144 = new DirectionTree(6144);

    @Test
    void partitionTree12ForRootShouldBe0() {
        assertThat(partitioningStrategy(TREE_12, 1, 1).partitionFor(1))
                .isEqualTo(0);
    }

    @Test
    void partitionTree12ForLeaf8ShouldBe0() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(8))
                .isEqualTo(0);
    }

    @Test
    void partitionTree12ForLeaf13ShouldBe0() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(13))
                .isEqualTo(0);
    }

    @Test
    void partitionTree12ForLeaf14ShouldBe1() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(14))
                .isEqualTo(1);
    }

    @Test
    void partitionTree12ForBranch4ShouldBe0() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(4))
                .isEqualTo(0);
    }

    @Test
    void partitionTree12ForBranch5ShouldBe0() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(5))
                .isEqualTo(0);
    }

    @Test
    void partitionTree12ForBranch6ShouldBe1() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(6))
                .isEqualTo(1);
    }

    @Test
    void partitionTree12ForBranch7ShouldBe1() {
        assertThat(partitioningStrategy(TREE_12, 2, 1).partitionFor(7))
                .isEqualTo(1);
    }

    @Test
    void partitionTree48ForLeaves() {
        val strategy = partitioningStrategy(TREE_48, 4, 2);
        SoftAssertions.assertSoftly(softly -> {
            range(32, 44).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(44, 56).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(56, 68).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(68, 80).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
        });
    }

    @Test
    void partitionTree48ForBranches() {
        val strategy = partitioningStrategy(TREE_48, 4, 2);
        SoftAssertions.assertSoftly(softly -> {
            range(16, 20).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(20, 24).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(24, 28).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(28, 32).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
        });
    }

    @Test
    void partitionTree48ForInterim8() {
        val strategy = partitioningStrategy(TREE_48, 4, 2);
        SoftAssertions.assertSoftly(softly -> {
            range(8, 10).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(10, 12).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(12, 14).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(14, 16).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
        });
    }

    @Test
    void partitionTree48ForInterim4() {
        val strategy = partitioningStrategy(TREE_48, 4, 2);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(strategy.partitionFor(4)).isEqualTo(0);
            softly.assertThat(strategy.partitionFor(5)).isEqualTo(1);
            softly.assertThat(strategy.partitionFor(6)).isEqualTo(2);
            softly.assertThat(strategy.partitionFor(7)).isEqualTo(3);
        });
    }

    @Test
    void partitionTree48ForInterim2AreAssignedToDifferentWorkers() {
        val strategy = partitioningStrategy(TREE_48, 4, 2);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(strategy.partitionFor(2)).isEqualTo(0);
            softly.assertThat(strategy.partitionFor(3)).isEqualTo(3);
        });
    }

    @Test
    void partitionTree48ForRoot() {
        assertThat(partitioningStrategy(TREE_48, 4, 2).partitionFor(1)).isEqualTo(1);
    }

    @Test
    void partitionTree6144ForRootAnd64PartitionsShouldBe0() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(1))
                .isEqualTo(1);
    }

    @Test
    void partitionTree6144ForLeftmostLeafAnd64PartitionsShouldBe0() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(4096))
                .isEqualTo(0);
    }

    @Test
    void partitionTree6144ForRightmostLeafAnd64PartitionsShouldBe63() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(10239))
                .isEqualTo(63);
    }

    @Test
    void partitionTree6144For64And64PartitionsShouldBe0() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(64))
                .isEqualTo(0);
    }

    @Test
    void partitionTree6144For65And64PartitionsShouldBe1() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(65))
                .isEqualTo(1);
    }

    @Test
    void partitionTree6144For66And64PartitionsShouldBe2() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(66))
                .isEqualTo(2);
    }

    @Test
    void partitionTree6144For127And64PartitionsShouldBe2() {
        assertThat(partitioningStrategy(TREE_6144, 64, 1).partitionFor(127))
                .isEqualTo(63);
    }

    @Test
    void partitionTree192ForLeavesAndTwoWorkersAndTwoThreads() {
        val strategy = partitioningStrategy(TREE_192, 4, 2);
        SoftAssertions.assertSoftly(softly -> {
            range(128, 176).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(176, 224).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(224, 272).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(272, 320).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
        });
    }

    @Test
    void partitionTree192ForLeavesAndTwoWorkersAndFourThreads() {
        val strategy = partitioningStrategy(TREE_192, 8, 2);
        SoftAssertions.assertSoftly(softly -> {
            range(128, 152).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(152, 176).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(176, 200).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(200, 224).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
            range(224, 248).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(4));
            range(248, 272).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(5));
            range(272, 296).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(6));
            range(296, 320).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(7));
        });
    }

    @Test
    void partitionTree192ForLeavesAndFourWorkersAndTwoThreads() {
        val strategy = partitioningStrategy(TREE_192, 8, 4);
        SoftAssertions.assertSoftly(softly -> {
            range(128, 152).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(152, 176).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(176, 200).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(200, 224).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
            range(224, 248).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(4));
            range(248, 272).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(5));
            range(272, 296).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(6));
            range(296, 320).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(7));
        });
    }

    @Test
    void partitionTree192ForBranchAndTwoWorkersAndTwoThreads() {
        val strategy = partitioningStrategy(TREE_192, 4, 4);
        SoftAssertions.assertSoftly(softly -> {
            range(64, 80).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(0));
            range(80, 96).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(1));
            range(96, 112).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(2));
            range(112, 128).forEach(i -> softly.assertThat(strategy.partitionFor(i)).isEqualTo(3));
        });
    }

    @Test
    void partitionTree192ForLevel2AndEightWorkersAndFourThreads() {
        val strategy = partitioningStrategy(TREE_192, 32, 8);
        assertThat(strategy.partitionFor(4)).isEqualTo(0);
        assertThat(strategy.partitionFor(5)).isEqualTo(1);
        assertThat(strategy.partitionFor(6)).isEqualTo(2);
        assertThat(strategy.partitionFor(7)).isEqualTo(3);
    }

    @Test
    void partitionTree192ForLevel3AndEightWorkersAndTwoThreads() {
        val strategy = partitioningStrategy(TREE_192, 16, 8);
        assertThat(strategy.partitionFor(8)).isEqualTo(0);
        assertThat(strategy.partitionFor(9)).isEqualTo(3);
        assertThat(strategy.partitionFor(10)).isEqualTo(4);
        assertThat(strategy.partitionFor(11)).isEqualTo(7);
        assertThat(strategy.partitionFor(12)).isEqualTo(8);
        assertThat(strategy.partitionFor(13)).isEqualTo(11);
        assertThat(strategy.partitionFor(14)).isEqualTo(12);
        assertThat(strategy.partitionFor(15)).isEqualTo(15);
    }

    @Test
    void partitionTree192ForLevel4AndEightWorkersAndTwoThreads() {
        val strategy = partitioningStrategy(TREE_192, 16, 8);
        assertThat(strategy.partitionFor(16)).isEqualTo(0);
        assertThat(strategy.partitionFor(17)).isEqualTo(1);
        assertThat(strategy.partitionFor(18)).isEqualTo(2);
        assertThat(strategy.partitionFor(19)).isEqualTo(3);
        assertThat(strategy.partitionFor(20)).isEqualTo(4);
        assertThat(strategy.partitionFor(21)).isEqualTo(5);
        assertThat(strategy.partitionFor(22)).isEqualTo(6);
        assertThat(strategy.partitionFor(23)).isEqualTo(7);
    }

    @Test
    void canPartition192With8PartitionsAnd2Workers() {
        val strategy = partitioningStrategy(TREE_192, 8, 2);
        range(1, TREE_192.lastIndexOfLeafRow()).forEach(i -> assertThat(strategy.partitionFor(i)).isBetween(0, 8));
    }

    @Test
    void canPartition192With16PartitionsAnd2Workers() {
        val strategy = partitioningStrategy(TREE_192, 16, 2);
        range(1, TREE_192.lastIndexOfLeafRow()).forEach(i -> assertThat(strategy.partitionFor(i)).isBetween(0, 16));
    }

    @Test
    void canPartition192With32PartitionsAnd2Workers() {
        val strategy = partitioningStrategy(TREE_192, 32, 2);
        range(1, TREE_192.lastIndexOfLeafRow()).forEach(i -> assertThat(strategy.partitionFor(i)).isBetween(0, 32));
    }

    @Test
    void canPartition6144With64PartitionsAnd4Workers() {
        val strategy = partitioningStrategy(TREE_6144, 64, 4);
        range(1, TREE_6144.lastIndexOfLeafRow()).forEach(i -> assertThat(strategy.partitionFor(i)).isBetween(0, 64));
    }

    @Test
    void canPartition6144With128PartitionsAnd8Workers() {
        val strategy = partitioningStrategy(TREE_6144, 128, 8);
        range(1, TREE_6144.lastIndexOfLeafRow()).forEach(i -> assertThat(strategy.partitionFor(i)).isBetween(0, 128));
    }

    @Test
    void canPartition6144With4PartitionsAnd2Workers() {
        val strategy = partitioningStrategy(TREE_6144, 8, 2);
        range(1, TREE_6144.lastIndexOfLeafRow()).forEach(i -> assertThat(strategy.partitionFor(i)).isBetween(0, 8));
    }

}