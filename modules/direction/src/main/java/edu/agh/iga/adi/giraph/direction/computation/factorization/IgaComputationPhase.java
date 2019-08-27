package edu.agh.iga.adi.giraph.direction.computation.factorization;

import edu.agh.iga.adi.giraph.core.DirectionTree;

import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationDirection.DOWN;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationDirection.UP;

public enum IgaComputationPhase {
  MERGE_AND_ELIMINATE_LEAVES(UP),
  MERGE_AND_ELIMINATE_BRANCH(UP),
  MERGE_AND_ELIMINATE_INTERIM(UP),
  MERGE_AND_ELIMINATE_ROOT(DOWN),
  BACKWARDS_SUBSTITUTE_ROOT(DOWN),
  BACKWARDS_SUBSTITUTE_INTERIM(DOWN),
  BACKWARDS_SUBSTITUTE_BRANCH(DOWN);

  private final IgaComputationDirection direction;

  IgaComputationPhase(IgaComputationDirection direction) {
    this.direction = direction;
  }

  public static IgaComputationPhase phaseFor(DirectionTree tree, int step) {
    final int verticalSteps = tree.height() - 1;
    if (step == 0) {
      return MERGE_AND_ELIMINATE_LEAVES;
    }
    if (step == 1) {
      return MERGE_AND_ELIMINATE_BRANCH;
    }
    if (step == verticalSteps) {
      return MERGE_AND_ELIMINATE_ROOT;
    }
    if (step < verticalSteps) {
      return MERGE_AND_ELIMINATE_INTERIM;
    }
    if (step == verticalSteps + 1) {
      return BACKWARDS_SUBSTITUTE_ROOT;
    }
    if (step == 2 * verticalSteps) {
      return BACKWARDS_SUBSTITUTE_BRANCH;
    }
    if (step < 2 * verticalSteps) {
      return BACKWARDS_SUBSTITUTE_INTERIM;
    }
    return null;
  }

  public boolean matchesDirection(long src, long dst) {
    return direction == UP ? src > dst : src < dst;
  }

}
