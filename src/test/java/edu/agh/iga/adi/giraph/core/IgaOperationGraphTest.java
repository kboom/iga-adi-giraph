package edu.agh.iga.adi.giraph.core;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaOperationGraph.directionGraph;
import static org.assertj.core.api.Assertions.assertThat;


class IgaOperationGraphTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);

  @Test
  void canGenerateGraphFor12Elements() {
    assertThat(directionGraph(TREE_12))
        .containsExactlyInAnyOrder(

        );
  }

}