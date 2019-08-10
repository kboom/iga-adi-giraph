package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.*;
import static org.assertj.core.api.Assertions.assertThat;


class IgaComputationPhaseTest {

  public static final DirectionTree TREE_12 = new DirectionTree(12);

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

}