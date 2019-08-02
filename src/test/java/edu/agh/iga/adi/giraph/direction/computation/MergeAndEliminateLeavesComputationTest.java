package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaOperation;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.direction.ComputationTestRunner.whenMasterComputation;

class MergeAndEliminateLeavesComputationTest {


  @Test
  void canSolve() {
    whenMasterComputation(MergeAndEliminateLeavesComputation.class)
        .isRunForGraph((graph, vf) ->
            graph.addVertex(vf.makeVertex(1L, new IgaOperation(), 2L))
        ).thenAssertThatGraph(assertions -> assertions.hasElement(1L, new IgaElement()));
  }

}