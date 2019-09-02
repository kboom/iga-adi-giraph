package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation;
import lombok.val;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;
import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenComputation;
import static edu.agh.iga.adi.giraph.direction.test.TestElementFactory.elementsFor;
import static edu.agh.iga.adi.giraph.direction.test.TestIgaOperationGraph.igaTestGraph;
import static org.assertj.core.api.Assertions.assertThat;

class ComputationPerformanceTest {

  private static final int BIG_PROBLEM_SIZE = 768;
  private static final Mesh BIG_PROBLEM_MESH = aMesh().withElements(BIG_PROBLEM_SIZE).build();
  private static final DirectionTree BIG_PROBLEM_TREE = new DirectionTree(BIG_PROBLEM_SIZE);


  /**
   * A naive test without the insight into the process - counts in the set-up and tear-down times.
   * Change once proper time-measuring techniques are installed.
   */
  @Test
  void canSolveOneFor12() {
    val startedAt = System.currentTimeMillis();
    whenComputation(StepComputation.class, InitialComputation.class)
        .ofProblemSize(BIG_PROBLEM_SIZE)
        .isRunForGraph(graph -> igaTestGraph(graph).withVertexElements(elementsFor(Double::sum, BIG_PROBLEM_TREE, BIG_PROBLEM_MESH)));

    val endedAt = System.currentTimeMillis();

    assertThat(endedAt - startedAt).isLessThan(40000);
  }

}