package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.operations.*;

import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationDirection.DOWN;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationDirection.UP;

public enum IgaComputationPhase {
  MERGE_AND_ELIMINATE_LEAVES(MergeAndEliminateLeavesOperation.class, UP),
  MERGE_AND_ELIMINATE_BRANCH(MergeAndEliminateBranchOperation.class, UP),
  MERGE_AND_ELIMINATE_INTERIM(MergeAndEliminateInterimOperation.class, UP),
  MERGE_AND_ELIMINATE_ROOT(MergeAndEliminateRootOperation.class, UP),
  BACKWARDS_SUBSTITUTE_ROOT(BackwardsSubstituteRootOperation.class, DOWN),
  BACKWARDS_SUBSTITUTE_INTERIM(BackwardsSubstituteInterimOperation.class, DOWN),
  BACKWARDS_SUBSTITUTE_BRANCH(BackwardsSubstituteBranchOperation.class, DOWN);

  private final Class<? extends IgaOperation> operationClazz;
  private final IgaComputationDirection direction;

  IgaComputationPhase(Class<? extends IgaOperation> operationClazz, IgaComputationDirection direction) {
    this.operationClazz = operationClazz;
    this.direction = direction;
  }

  public static IgaComputationPhase phaseFor(DirectionTree tree, int step) {
    final int height = tree.height();
    if(step == 0) {
      return MERGE_AND_ELIMINATE_LEAVES;
    }
    if(step == 1) {
      return MERGE_AND_ELIMINATE_BRANCH;
    }
    if(step < height) {
      return MERGE_AND_ELIMINATE_INTERIM;
    }
    if(step == height) {
      return MERGE_AND_ELIMINATE_ROOT;
    }
    if(step == height + 1) {
      return BACKWARDS_SUBSTITUTE_ROOT;
    }
    if(step < 2 * height) {
      return BACKWARDS_SUBSTITUTE_INTERIM;
    }
    if(step == 2 * height) {
      return BACKWARDS_SUBSTITUTE_BRANCH;
    }
    throw new IllegalArgumentException("Should not have this step");
  }

  public static IgaComputationPhase getPhase(int phaseInt) {
    return values()[phaseInt];
  }

  public boolean matchesDirection(long src, long dst) {
    return direction == UP ? src > dst : src < dst;
  }
}
