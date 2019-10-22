package edu.agh.iga.adi.giraph.direction.computation.factorization;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.operations.*;

import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationDirection.DOWN;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationDirection.UP;

public enum IgaComputationPhase {
  MERGE_AND_ELIMINATE_LEAVES(MergeAndEliminateLeavesOperation.class, UP),
  MERGE_AND_ELIMINATE_BRANCH(MergeAndEliminateBranchOperation.class, UP),
  MERGE_AND_ELIMINATE_INTERIM(MergeAndEliminateInterimOperation.class, UP),
  MERGE_AND_ELIMINATE_ROOT(MergeAndEliminateRootOperation.class, DOWN),
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

  public boolean matchesOperation(IgaOperation igaOperation) {
    return operationClazz.isAssignableFrom(igaOperation.getClass());
  }

  public boolean matchesDirection(long src, long dst) {
    return direction == UP ? src > dst : src < dst;
  }
}
