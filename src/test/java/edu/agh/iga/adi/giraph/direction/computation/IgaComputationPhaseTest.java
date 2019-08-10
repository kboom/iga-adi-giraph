package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.*;
import static org.assertj.core.api.Assertions.assertThat;


class IgaComputationPhaseTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);
  private static final DirectionTree TREE_24 = new DirectionTree(24);

  @Test
  void isMergeAndEliminateLeafForZero() {
    assertThat(phaseFor(TREE_12, 0)).isEqualTo(MERGE_AND_ELIMINATE_LEAVES);
  }

  @Test
  void isMergeAndEliminateBranchForOne() {
    assertThat(phaseFor(TREE_12, 1)).isEqualTo(MERGE_AND_ELIMINATE_BRANCH);
  }

  @Test
  void isMergeAndEliminateInterimForTwo() {
    assertThat(phaseFor(TREE_12, 2)).isEqualTo(MERGE_AND_ELIMINATE_INTERIM);
  }

  @Test
  void isMergeAndEliminateRootForThree() {
    assertThat(phaseFor(TREE_12, 3)).isEqualTo(MERGE_AND_ELIMINATE_ROOT);
  }

  @Test
  void isBackwardsSubstituteRootForFour() {
    assertThat(phaseFor(TREE_12, 4)).isEqualTo(BACKWARDS_SUBSTITUTE_ROOT);
  }

  @Test
  void isBackwardsSubstituteInterimForFive() {
    assertThat(phaseFor(TREE_12, 5)).isEqualTo(BACKWARDS_SUBSTITUTE_INTERIM);
  }

  @Test
  void isBackwardsSubstituteBranchForSix() {
    assertThat(phaseFor(TREE_12, 6)).isEqualTo(BACKWARDS_SUBSTITUTE_BRANCH);
  }

  @Test
  void isBackwardsSubstituteInterimForSixAnd24() {
    assertThat(phaseFor(TREE_24, 6)).isEqualTo(BACKWARDS_SUBSTITUTE_INTERIM);
  }

  @Test
  void isBackwardsSubstituteInterimForSevenAnd24() {
    assertThat(phaseFor(TREE_24, 7)).isEqualTo(BACKWARDS_SUBSTITUTE_INTERIM);
  }

  @Test
  void isBackwardsSubstituteBranchForEightAnd24() {
    assertThat(phaseFor(TREE_24, 8)).isEqualTo(BACKWARDS_SUBSTITUTE_BRANCH);
  }

  @Test
  void isMergeAndEliminateRootForFourAnd24() {
    assertThat(phaseFor(TREE_24, 4)).isEqualTo(MERGE_AND_ELIMINATE_ROOT);
  }

}