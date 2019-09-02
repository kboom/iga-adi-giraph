package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;
import static edu.agh.iga.adi.giraph.direction.test.Problems.*;
import static edu.agh.iga.adi.giraph.direction.test.TestElementFactory.elementsFor;
import static edu.agh.iga.adi.giraph.direction.test.TestIgaOperationGraph.igaTestGraph;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;

class StepComputationTest {

  @Test
  void canSolveOneFor12() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> 1, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_12,
                matrixOfSize(6, 14).withValues(
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                ),
                3
            )
        );
  }

  @Test
  void canSolveOneFor24() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_24_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> 1, TREE_24, MESH_24)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_24,
                matrixOfSize(6, 26).withValues(
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                ),
                3
            )
        );
  }

  @Test
  void canSolveXFor12() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> x, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_12,
                matrixOfSize(6, 14).withValues(
                    -0.5, 0.5, 1.5, 2.5, 3.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                ),
                3
            )
        );
  }

  @Test
  void canSolveYFor12() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> y, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions
                .hasElementWithUnknowns(
                    4L,
                    matrixOfSize(6, 14).withValues(
                        -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5,
                        0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5,
                        1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5,
                        2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5,
                        3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    5L,
                    matrixOfSize(6, 14).withValues(
                        2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5,
                        3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5,
                        4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5,
                        5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5,
                        6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    6L,
                    matrixOfSize(6, 14).withValues(
                        5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5,
                        6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5,
                        7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5,
                        8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5,
                        9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    7L,
                    matrixOfSize(6, 14).withValues(
                        8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5,
                        9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5,
                        10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5,
                        11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5,
                        12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
        );
  }

  @Test
  void canSolveSumFor12() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions
                .hasElementWithUnknowns(
                    4L,
                    matrixOfSize(6, 14).withValues(
                        -1, 0, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                        -0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                        1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                        2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                        3, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    5L,
                    matrixOfSize(6, 14).withValues(
                        2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                        3, 4, 5, 6, 7, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        4, 5, 6, 7, 8, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                        5, 6, 7, 8, 9, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                        6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    6L,
                    matrixOfSize(6, 14).withValues(
                        5, 6, 7, 8, 9, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                        6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                        7, 8, 9, 10, 11, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        8, 9, 10, 11, 12, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                        9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19, 20, 21,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    7L,
                    matrixOfSize(6, 14).withValues(
                        8, 9, 10, 11, 12, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                        9, 10, 11, 12, 13, 13, 14, 15, 16, 17, 18, 19, 20, 21,
                        10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22,
                        11, 12, 13, 14, 15, 15, 16, 17, 18, 19, 20, 21, 22, 23,
                        12, 13, 14, 15, 16, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    ),
                    3
                )
        );
  }

  @Test
  void canSolveLinearFor24() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_24_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, TREE_24, MESH_24)))
        .thenAssertThatGraph(assertions ->
            assertions.leavesHaveChecksums(
                TREE_24,
                new double[] {1650.0, 2040.0, 2430.0, 2820.0, 3210.0, 3600.0, 3990.0, 4380.0},
                3
            )
        );
  }

  @Test
  void canSolveLinearFor48() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_48_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, TREE_48, MESH_48)))
        .thenAssertThatGraph(assertions ->
            assertions.leavesHaveChecksums(
                TREE_48,
                new double[] {6150.0, 6900.0, 7650.0, 8400.0, 9150.0, 9900.0, 10650.0, 11400.0, 12150.0, 12900.0, 13650.0, 14400.0, 15150.0, 15900.0, 16650.0, 17400.0},
                3
            )
        );
  }

}