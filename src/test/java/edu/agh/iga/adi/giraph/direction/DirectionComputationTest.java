package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.InitialComputation;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;
import static edu.agh.iga.adi.giraph.test.TestIgaOperationGraph.igaTestGraph;
import static edu.agh.iga.adi.giraph.test.matrix.MatrixBuilder.emptyMatrixOfSize;
import static edu.agh.iga.adi.giraph.test.matrix.MatrixBuilder.matrixOfSize;

class DirectionComputationTest {

  private final DirectionTree directionTree = new DirectionTree(12);

  @Test
  void canSolve() {
    whenComputation(DirectionComputation.class, InitialComputation.class)
        .ofProblemSize(24)
        .isRunForGraph(graph ->
            igaTestGraph(graph)
                .withVertexElement(igaElement(
                    8L,
                    matrixOfSize(6, 6).withValues(
                        11, 12, 13, 14, 15, 16,
                        21, 22, 23, 24, 25, 26,
                        31, 32, 33, 34, 35, 36,
                        41, 42, 43, 44, 45, 46,
                        51, 52, 53, 54, 55, 56,
                        61, 62, 63, 64, 65, 66
                    ),
                    matrixOfSize(6, 14).withValues(
                        1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
                        2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
                        3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14,
                        4.01, 4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.10, 4.11, 4.12, 4.13, 4.14,
                        5.01, 5.02, 5.03, 5.04, 5.05, 5.06, 5.07, 5.08, 5.09, 5.10, 5.11, 5.12, 5.13, 5.14,
                        6.01, 6.02, 6.03, 6.04, 6.05, 6.06, 6.07, 6.08, 6.09, 6.10, 6.11, 6.12, 6.13, 6.14
                    ),
                    emptyMatrixOfSize(6, 14)
                ))
        )
        .thenAssertThatGraph(assertions ->
            assertions.hasElementWithUnknowns(4L, matrixOfSize(6, 14).withValues(
                1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
                2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
                3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14,
                4.01, 4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.10, 4.11, 4.12, 4.13, 4.14,
                5.01, 5.02, 5.03, 5.04, 5.05, 5.06, 5.07, 5.08, 5.09, 5.10, 5.11, 5.12, 5.13, 5.14,
                6.01, 6.02, 6.03, 6.04, 6.05, 6.06, 6.07, 6.08, 6.09, 6.10, 6.11, 6.12, 6.13, 6.14
            ))
        );
  }

}