package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.direction.computation.InitialComputation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;
import static edu.agh.iga.adi.giraph.test.Problems.MESH_12;
import static edu.agh.iga.adi.giraph.test.Problems.TREE_12;
import static edu.agh.iga.adi.giraph.test.TestIgaOperationGraph.igaTestGraph;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.LongStream.range;

class DirectionComputationTest {

  private static double[] SOLUTION_1D_12_ONE = new double[] {
      0.167, 0.833, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.833, 0.167,
      0.167, 0.833, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.833, 0.167,
      0.167, 0.833, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.833, 0.167,
      0.167, 0.833, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.833, 0.167,
      0.167, 0.833, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.833, 0.167,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
  };

  @Test
  void canSolveOneFor12() {
    whenComputation(DirectionComputation.class, InitialComputation.class)
        .ofProblemSize(12)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor((x, y) -> 1, TREE_12, MESH_12)))
        .thenAssertThatGraph(assertions ->
            assertions.allBranchElementsHaveUnknowns(TREE_12, matrixOfSize(6, 14).withValues(SOLUTION_1D_12_ONE), 3)
        );
  }

  private Set<IgaElement> elementsFor(Problem problem, DirectionTree tree, Mesh mesh) {
    HorizontalElementFactory ef = new HorizontalElementFactory(mesh);
    return range(1, 20)
        .boxed()
        .map(id -> ef.createElement(problem, vertexOf(tree, id)))
        .collect(toSet());
  }

}