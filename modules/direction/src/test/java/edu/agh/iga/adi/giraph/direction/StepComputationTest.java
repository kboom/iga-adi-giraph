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
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> 1, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_12,
                matrixOfSize(5, 14).withValues(
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                ),
                3
            )
        );
  }

  @Test
  void canSolveOneFor24() {
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_24_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> 1, TREE_24, MESH_24)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_24,
                matrixOfSize(5, 26).withValues(
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                ),
                3
            )
        );
  }

  @Test
  void canSolveXFor12() {
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> x, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_12,
                matrixOfSize(5, 14).withValues(
                    -0.5, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5,
                    -0.5, 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5
                ),
                3
            )
        );
  }

  @Test
  void canSolveYFor12() {
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> y, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions
                .hasElementWithUnknowns(
                    4L,
                    matrixOfSize(5, 14).withValues(
                        -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5,
                        0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5,
                        1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5,
                        2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5,
                        3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    5L,
                    matrixOfSize(5, 14).withValues(
                        2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5, 2.5,
                        3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5,
                        4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5, 4.5,
                        5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5,
                        6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    6L,
                    matrixOfSize(5, 14).withValues(
                        5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5, 5.5,
                        6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5, 6.5,
                        7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5, 7.5,
                        8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5,
                        9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    7L,
                    matrixOfSize(5, 14).withValues(
                        8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5,
                        9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5, 9.5,
                        10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5, 10.5,
                        11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5, 11.5,
                        12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5, 12.5
                    ),
                    3
                )
        );
  }

  @Test
  void canSolveSumFor12() {
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_12_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions
                .hasElementWithUnknowns(
                    4L,
                    matrixOfSize(5, 14).withValues(
                        // @formatter:off
                       -1, -0,  1,  2,  3,  4, 5,  6,  7,  8,  9, 10, 11, 12,
                       -0,  1,  2,  3,  4,  5, 6,  7,  8,  9, 10, 11, 12, 13,
                        1,  2,  3,  4,  5,  6, 7,  8,  9, 10, 11, 12, 13, 14,
                        2,  3,  4,  5,  6,  7, 8,  9, 10, 11, 12, 13, 14, 15,
                        3,  4,  5,  6,  7,  8, 9, 10, 11, 12, 13, 14, 15, 16
                        // @formatter:on
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    5L,
                    matrixOfSize(5, 14).withValues(
                        // @formatter:off
                        2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
                        3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16,
                        4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17,
                        5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                        6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
                        // @formatter:on
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    6L,
                    matrixOfSize(5, 14).withValues(
                        // @formatter:off
                        5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                        6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                        8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
                        9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
                        // @formatter:on
                    ),
                    3
                )
                .hasElementWithUnknowns(
                    7L,
                    matrixOfSize(5, 14).withValues(
                        // @formatter:off
                         8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
                         9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
                        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
                        11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                        12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
                        // @formatter:on
                    ),
                    3
                )
        );
  }

  @Test
  void canSolveLinearFor24() {
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_24_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, TREE_24, MESH_24)))
        .thenAssertThatGraph(assertions ->
            assertions.leavesHaveChecksums(
                TREE_24,
                new double[] {1755.0, 2145.0, 2535.0, 2925.0, 3315.0, 3705.0, 4095.0, 4485.0},
                3
            )
        );
  }

  @Test
  void canSolveLinearFor48() {
    whenComputation(IterativeComputation.class, InitialComputation.class)
        .ofProblemSize(PROBLEM_48_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, TREE_48, MESH_48)))
        .thenAssertThatGraph(assertions ->
            assertions.leavesHaveChecksums(
                TREE_48,
                new double[] {6375.0, 7125.0, 7875.0, 8625.0, 9375.0, 10125.0, 10875.0, 11625.0, 12375.0, 13125.0, 13875.0, 14625.0, 15375.0, 16125.0, 16875.0, 17625.0},
                3
            )
        );
  }

}