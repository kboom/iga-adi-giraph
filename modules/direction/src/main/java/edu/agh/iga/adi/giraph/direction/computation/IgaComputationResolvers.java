package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialisationComputation;
import edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation;
import lombok.Getter;
import org.apache.giraph.graph.Computation;

import java.util.function.BiFunction;

import static java.util.Arrays.stream;

public enum IgaComputationResolvers implements ComputationResolver {
  COEFFICIENTS_PROBLEM("COEFFICIENTS", (tree, step) -> {
    if (step == 0) {
      return InitialisationComputation.class;
    }
    if (step == 1) {
      return InitialComputation.class;
    }
    if (step < 2 * tree.height() + 1) {
      return FactorisationComputation.class;
    }
    if (step <= 2 * tree.height() + 2) {
      return TranspositionComputation.class;
    }
    if (step == 2 * tree.height() + 3) {
      return InitialComputation.class;
    }
    if (step < 4 * tree.height() + 4) {
      return FactorisationComputation.class;
    }
    return null;
  }),
  SURFACE_PROBLEM("SURFACE", (tree, step) -> {
    if (step == 0) {
      return InitialComputation.class;
    }
    if (step < 2 * tree.height()) {
      return FactorisationComputation.class;
    }
    if (step <= 2 * tree.height() + 1) {
      return TranspositionComputation.class;
    }
    if (step == 2 * tree.height() + 2) {
      return InitialComputation.class;
    }
    if (step < 4 * tree.height() + 3) {
      return FactorisationComputation.class;
    }
    return null;
  });

  @Getter
  private final String type;
  private final BiFunction<DirectionTree, Long, Class<? extends IgaComputation>> mapper;

  IgaComputationResolvers(String type, BiFunction<DirectionTree, Long, Class<? extends IgaComputation>> mapper) {
    this.type = type;
    this.mapper = mapper;
  }

  public static ComputationResolver computationResolverFor(String type) {
    return stream(values())
        .filter(c -> c.getType().equals(type))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("Does not have computation " + type));
  }

  @Override
  public Class<? extends Computation> computationFor(DirectionTree tree, long step) {
    return mapper.apply(tree, step);
  }

}
