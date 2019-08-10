package edu.agh.iga.adi.giraph.core;

import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.InterimVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.RootVertex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static org.assertj.core.api.Assertions.assertThat;

class IgaVertexTest {

  private static final DirectionTree TREE = new DirectionTree(12);

  @Test
  void v1isRoot() {
    assertThat(vertexOf(TREE, 1L))
        .isInstanceOf(RootVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(1L);
  }

  @ParameterizedTest
  @ValueSource(longs = {2L, 3L})
  void interimVertices(long id) {
    assertThat(vertexOf(TREE, id))
        .isInstanceOf(InterimVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(id);
  }

  @ParameterizedTest
  @ValueSource(longs = {4L, 5L, 6L, 7L})
  void branchVertices(long id) {
    assertThat(vertexOf(TREE, id))
        .isInstanceOf(BranchVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(id);
  }

  @ParameterizedTest
  @ValueSource(longs = {8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L})
  void leafVertices(long id) {
    assertThat(vertexOf(TREE, id))
        .isInstanceOf(LeafVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(id);
  }

  @Test
  void childrenRoot() {
    assertThat(vertexOf(TREE, 1L).children())
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(2L, 3L);
  }

}