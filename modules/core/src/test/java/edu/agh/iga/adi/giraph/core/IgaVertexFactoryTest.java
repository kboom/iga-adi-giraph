package edu.agh.iga.adi.giraph.core;

import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.IgaVertexFactory.childrenOf;
import static edu.agh.iga.adi.giraph.core.test.Problems.TREE_12;
import static org.assertj.core.api.Assertions.assertThat;

class IgaVertexFactoryTest {

  @Test
  void childrenOfRootIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 1), 3)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 2),
            vertexOf(TREE_12, 3),
            vertexOf(TREE_12, 4),
            vertexOf(TREE_12, 5),
            vertexOf(TREE_12, 6),
            vertexOf(TREE_12, 7),
            vertexOf(TREE_12, 8),
            vertexOf(TREE_12, 9),
            vertexOf(TREE_12, 10),
            vertexOf(TREE_12, 11),
            vertexOf(TREE_12, 12),
            vertexOf(TREE_12, 13),
            vertexOf(TREE_12, 14),
            vertexOf(TREE_12, 15),
            vertexOf(TREE_12, 16),
            vertexOf(TREE_12, 17),
            vertexOf(TREE_12, 18),
            vertexOf(TREE_12, 19)
        );
  }

  @Test
  void childrenOf2LIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 2), 2)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 4),
            vertexOf(TREE_12, 5),
            vertexOf(TREE_12, 8),
            vertexOf(TREE_12, 9),
            vertexOf(TREE_12, 10),
            vertexOf(TREE_12, 11),
            vertexOf(TREE_12, 12),
            vertexOf(TREE_12, 13)
        );
  }

  @Test
  void childrenOf3LIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 3), 2)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 6),
            vertexOf(TREE_12, 7),
            vertexOf(TREE_12, 14),
            vertexOf(TREE_12, 15),
            vertexOf(TREE_12, 16),
            vertexOf(TREE_12, 17),
            vertexOf(TREE_12, 18),
            vertexOf(TREE_12, 19)
        );
  }

  @Test
  void childrenOf5LIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 5), 1)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 11),
            vertexOf(TREE_12, 12),
            vertexOf(TREE_12, 13)
        );
  }

  @Test
  void emptyForZeroHeight() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 1), 0)))
        .isEmpty();
  }

}