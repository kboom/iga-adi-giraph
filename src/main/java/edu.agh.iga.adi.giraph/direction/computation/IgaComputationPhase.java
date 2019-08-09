package edu.agh.iga.adi.giraph.direction.computation;

import com.google.common.collect.ImmutableSet;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.operations.*;

import java.util.Set;

public enum IgaComputationPhase {
  MERGE_AND_ELIMINATE(
      MergeAndEliminateLeavesOperation.class,
      MergeAndEliminateBranchOperation.class,
      MergeAndEliminateInterimOperation.class,
      MergeAndEliminateRootOperation.class
  ),
  BACKWARDS_SUBSTITUTE(
      BackwardsSubstituteBranchOperation.class,
      BackwardsSubstituteInterimOperation.class,
      BackwardsSubstituteRootOperation.class
  );

  private final Set<Class<?>> classSet;

  IgaComputationPhase(Class<? extends IgaOperation>... classes) {
    classSet = ImmutableSet.copyOf(classes);
  }

  public static IgaComputationPhase getPhase(int phaseInt) {
    return IgaComputationPhase.values()[phaseInt];
  }

  public boolean matches(IgaOperation igaOperation) {
    return classSet.contains(igaOperation.getClass());
  }
}
