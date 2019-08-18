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
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 1L), 3)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 2L),
            vertexOf(TREE_12, 3L),
            vertexOf(TREE_12, 4L),
            vertexOf(TREE_12, 5L),
            vertexOf(TREE_12, 6L),
            vertexOf(TREE_12, 7L),
            vertexOf(TREE_12, 8L),
            vertexOf(TREE_12, 9L),
            vertexOf(TREE_12, 10L),
            vertexOf(TREE_12, 11L),
            vertexOf(TREE_12, 12L),
            vertexOf(TREE_12, 13L),
            vertexOf(TREE_12, 14L),
            vertexOf(TREE_12, 15L),
            vertexOf(TREE_12, 16L),
            vertexOf(TREE_12, 17L),
            vertexOf(TREE_12, 18L),
            vertexOf(TREE_12, 19L)
        );
  }

  @Test
  void childrenOf2LIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 2L), 2)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 4L),
            vertexOf(TREE_12, 5L),
            vertexOf(TREE_12, 8L),
            vertexOf(TREE_12, 9L),
            vertexOf(TREE_12, 10L),
            vertexOf(TREE_12, 11L),
            vertexOf(TREE_12, 12L),
            vertexOf(TREE_12, 13L)
        );
  }

  @Test
  void childrenOf3LIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 3L), 2)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 6L),
            vertexOf(TREE_12, 7L),
            vertexOf(TREE_12, 14L),
            vertexOf(TREE_12, 15L),
            vertexOf(TREE_12, 16L),
            vertexOf(TREE_12, 17L),
            vertexOf(TREE_12, 18L),
            vertexOf(TREE_12, 19L)
        );
  }

  @Test
  void childrenOf5LIn12() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 5L), 1)))
        .containsExactlyInAnyOrder(
            vertexOf(TREE_12, 11L),
            vertexOf(TREE_12, 12L),
            vertexOf(TREE_12, 13L)
        );
  }

  @Test
  void emptyForZeroHeight() {
    assertThat(newArrayList(childrenOf(vertexOf(TREE_12, 1L), 0)))
        .isEmpty();
  }

}