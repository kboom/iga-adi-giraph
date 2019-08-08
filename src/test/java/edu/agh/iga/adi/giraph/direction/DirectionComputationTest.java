package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.InitialComputation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;
import static edu.agh.iga.adi.giraph.test.TestGraphFactory.directionGraph;

class DirectionComputationTest {

  private final DirectionTree directionTree = new DirectionTree(12);

  @Test
  @Disabled
  void canSolve() {
    whenComputation(DirectionComputation.class, InitialComputation.class)
        .ofProblemSize(12)
        .isRunForGraph((graph) -> directionGraph(directionTree, graph))
        .thenAssertThatGraph(assertions -> assertions.hasElement(1L, igaElement(0L, 12)));
  }

}