package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.*;
import static org.assertj.core.api.Assertions.assertThat;


class IgaComputationPhaseTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);
  private static final DirectionTree TREE_24 = new DirectionTree(24);

  @Test
  void isMergeAndEliminateLeafForZero12() {
    assertThat(phaseFor(TREE_12, 0)).isEqualTo(MERGE_AND_ELIMINATE_LEAVES);
  }

  @Test
  void isMergeAndEliminateLeafForZero24() {
    assertThat(phaseFor(TREE_24, 0)).isEqualTo(MERGE_AND_ELIMINATE_LEAVES);
  }

  @Test
  void isMergeAndEliminateBranchForOne12() {
    assertThat(phaseFor(TREE_12, 1)).isEqualTo(MERGE_AND_ELIMINATE_BRANCH);
  }

  @Test
  void isMergeAndEliminateBranchForOne24() {
    assertThat(phaseFor(TREE_24, 1)).isEqualTo(MERGE_AND_ELIMINATE_BRANCH);
  }

  @Test
  void isMergeAndEliminateRootForTwo12() {
    assertThat(phaseFor(TREE_12, 2)).isEqualTo(MERGE_AND_ELIMINATE_ROOT);
  }

  @Test
  void isMergeAndEliminateInterimForTwo24() {
    assertThat(phaseFor(TREE_24, 2)).isEqualTo(MERGE_AND_ELIMINATE_INTERIM);
  }

  @Test
  void isBackwardsSubstituteRootForThree12() {
    assertThat(phaseFor(TREE_12, 3)).isEqualTo(BACKWARDS_SUBSTITUTE_ROOT);
  }

  @Test
  void isBackwardsSubstituteBranchForFour12() {
    assertThat(phaseFor(TREE_12, 4)).isEqualTo(BACKWARDS_SUBSTITUTE_BRANCH);
  }

  @Test
  void isMergeAndEliminateRootForThree24() {
    assertThat(phaseFor(TREE_24, 3)).isEqualTo(MERGE_AND_ELIMINATE_ROOT);
  }

  @Test
  void isBackwardsSubstituteRootForFour24() {
    assertThat(phaseFor(TREE_24, 4)).isEqualTo(BACKWARDS_SUBSTITUTE_ROOT);
  }

  @Test
  void isBackwardsSubstituteInterimForFive24() {
    assertThat(phaseFor(TREE_24, 5)).isEqualTo(BACKWARDS_SUBSTITUTE_INTERIM);
  }

  @Test
  void isBackwardsSubstituteBranchForSixAnd24() {
    assertThat(phaseFor(TREE_24, 6)).isEqualTo(BACKWARDS_SUBSTITUTE_BRANCH);
  }

  @Test
  void isBackwardsSubstituteRootForFourAnd24() {
    assertThat(phaseFor(TREE_24, 4)).isEqualTo(BACKWARDS_SUBSTITUTE_ROOT);
  }

}