package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.direction.computation.InitialComputation;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;

class DirectionComputationTest {

  @Test
  void canSolve() {
    whenComputation(DirectionComputation.class, InitialComputation.class)
        .isRunForGraph((graph) ->
            graph.withVertex(1L, new IgaOperation(), 2L)
        ).thenAssertThatGraph(assertions -> assertions.hasElement(1L, new IgaElement()));
  }

}