package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.computation.MergeAndEliminateLeavesComputation;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;

class DirectionComputationTest {

  @Test
  void canSolve() {
    whenComputation(DirectionComputation.class, MergeAndEliminateLeavesComputation.class)
        .isRunForGraph((graph) ->
            graph.withVertex(1L, new IgaOperation(), 2L)
        ).thenAssertThatGraph(assertions -> assertions.hasElement(1L, new IgaElement()));
  }

}