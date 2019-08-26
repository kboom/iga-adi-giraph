package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;
import static edu.agh.iga.adi.giraph.direction.test.Problems.*;
import static edu.agh.iga.adi.giraph.direction.test.TestIgaOperationGraph.igaTestGraph;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.LongStream.range;

class StepComputationTest {

  @Test
  void canSolveOneFor12() {
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(12)
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
        .ofProblemSize(24)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> 1, TREE_24, MESH_24)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(
                TREE_12,
                matrixOfSize(6, 26).withValues(
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
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

  private Set<IgaElement> elementsFor(Problem problem, DirectionTree tree, Mesh mesh) {
    HorizontalElementFactory ef = new HorizontalElementFactory(mesh);
    return range(1, tree.lastIndexOfLeafRow() + 1)
        .boxed()
        .map(id -> ef.createElement(problem, vertexOf(tree, id)))
        .collect(toSet());
  }

}