package edu.agh.iga.adi.giraph.core;

import edu.agh.iga.adi.giraph.test.TestOperationFactory;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaOperationFactory.operationsFor;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static org.assertj.core.api.Assertions.assertThat;


class IgaOperationFactoryTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);
  private static final DirectionTree TREE_24 = new DirectionTree(24);

  private static final TestOperationFactory T12OF = new TestOperationFactory(TREE_12);
  private static final TestOperationFactory T24OF = new TestOperationFactory(TREE_24);

  @Test
  void canGenerateTopTriangle() {
    assertThat(operationsFor(TREE_12, vertexOf(TREE_12, 1L), 1))
        .containsExactlyInAnyOrder(
            T12OF.mergeAndEliminateRoot(2, 1),
            T12OF.mergeAndEliminateRoot(3, 1),
            T12OF.backwardsSubstituteRoot(1, 2),
            T12OF.backwardsSubstituteRoot(1, 3)
        );
  }

  @Test
  void canGenerateBottomTriangle() {
    assertThat(operationsFor(TREE_12, vertexOf(TREE_12, 4L), 1))
        .containsExactlyInAnyOrder(
            T12OF.mergeAndEliminateLeaves(8, 4),
            T12OF.mergeAndEliminateLeaves(9, 4),
            T12OF.mergeAndEliminateLeaves(10, 4)
        );
  }

  @Test
  void canGenerateGraphFor12Elements() {
    assertThat(operationsFor(TREE_12))
        .containsExactlyInAnyOrder(
            T12OF.mergeAndEliminateLeaves(8, 4),
            T12OF.mergeAndEliminateLeaves(9, 4),
            T12OF.mergeAndEliminateLeaves(10, 4),
            T12OF.mergeAndEliminateLeaves(11, 5),
            T12OF.mergeAndEliminateLeaves(12, 5),
            T12OF.mergeAndEliminateLeaves(13, 5),
            T12OF.mergeAndEliminateLeaves(14, 6),
            T12OF.mergeAndEliminateLeaves(15, 6),
            T12OF.mergeAndEliminateLeaves(16, 6),
            T12OF.mergeAndEliminateLeaves(17, 7),
            T12OF.mergeAndEliminateLeaves(18, 7),
            T12OF.mergeAndEliminateLeaves(19, 7),
            T12OF.mergeAndEliminateBranch(4, 2),
            T12OF.mergeAndEliminateBranch(5, 2),
            T12OF.mergeAndEliminateBranch(6, 3),
            T12OF.mergeAndEliminateBranch(7, 3),
            T12OF.mergeAndEliminateRoot(2, 1),
            T12OF.mergeAndEliminateRoot(3, 1),
            T12OF.backwardsSubstituteRoot(1, 2),
            T12OF.backwardsSubstituteRoot(1, 3),
            T12OF.backwardsSubstituteBranch(2, 4),
            T12OF.backwardsSubstituteBranch(2, 5),
            T12OF.backwardsSubstituteBranch(3, 6),
            T12OF.backwardsSubstituteBranch(3, 7)
        );
  }

  @Test
  void canGenerateGraphFor24Elements() {
    assertThat(operationsFor(TREE_24))
        .containsExactlyInAnyOrder(
            T24OF.mergeAndEliminateLeaves(16, 8),
            T24OF.mergeAndEliminateLeaves(17, 8),
            T24OF.mergeAndEliminateLeaves(18, 8),

            T24OF.mergeAndEliminateLeaves(19, 9),
            T24OF.mergeAndEliminateLeaves(20, 9),
            T24OF.mergeAndEliminateLeaves(21, 9),

            T24OF.mergeAndEliminateLeaves(22, 10),
            T24OF.mergeAndEliminateLeaves(23, 10),
            T24OF.mergeAndEliminateLeaves(24, 10),

            T24OF.mergeAndEliminateLeaves(25, 11),
            T24OF.mergeAndEliminateLeaves(26, 11),
            T24OF.mergeAndEliminateLeaves(27, 11),

            T24OF.mergeAndEliminateLeaves(28, 12),
            T24OF.mergeAndEliminateLeaves(29, 12),
            T24OF.mergeAndEliminateLeaves(30, 12),

            T24OF.mergeAndEliminateLeaves(31, 13),
            T24OF.mergeAndEliminateLeaves(32, 13),
            T24OF.mergeAndEliminateLeaves(33, 13),

            T24OF.mergeAndEliminateLeaves(34, 14),
            T24OF.mergeAndEliminateLeaves(35, 14),
            T24OF.mergeAndEliminateLeaves(36, 14),

            T24OF.mergeAndEliminateLeaves(37, 15),
            T24OF.mergeAndEliminateLeaves(38, 15),
            T24OF.mergeAndEliminateLeaves(39, 15),

            T24OF.mergeAndEliminateBranch(8, 4),
            T24OF.mergeAndEliminateBranch(9, 4),

            T24OF.mergeAndEliminateBranch(10, 5),
            T24OF.mergeAndEliminateBranch(11, 5),

            T24OF.mergeAndEliminateBranch(12, 6),
            T24OF.mergeAndEliminateBranch(13, 6),

            T24OF.mergeAndEliminateBranch(14, 7),
            T24OF.mergeAndEliminateBranch(15, 7),

            T24OF.mergeAndEliminateInterim(4, 2),
            T24OF.mergeAndEliminateInterim(5, 2),

            T24OF.mergeAndEliminateInterim(6, 3),
            T24OF.mergeAndEliminateInterim(7, 3),

            T24OF.mergeAndEliminateRoot(2, 1),
            T24OF.mergeAndEliminateRoot(3, 1),

            T24OF.backwardsSubstituteRoot(1, 2),
            T24OF.backwardsSubstituteRoot(1, 3),

            T24OF.backwardsSubstituteInterim(2, 4),
            T24OF.backwardsSubstituteInterim(2, 5),

            T24OF.backwardsSubstituteInterim(3, 6),
            T24OF.backwardsSubstituteInterim(3, 7),

            T24OF.backwardsSubstituteBranch(4, 8),
            T24OF.backwardsSubstituteBranch(4, 9),

            T24OF.backwardsSubstituteBranch(5, 10),
            T24OF.backwardsSubstituteBranch(5, 11),

            T24OF.backwardsSubstituteBranch(6, 12),
            T24OF.backwardsSubstituteBranch(6, 13),

            T24OF.backwardsSubstituteBranch(7, 14),
            T24OF.backwardsSubstituteBranch(7, 15)
        );
  }


}