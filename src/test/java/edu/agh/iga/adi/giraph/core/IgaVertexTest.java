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
        .hasOnlyElementsOfType(InterimVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(2L, 3L);
  }

  @Test
  void v2children() {
    assertThat(vertexOf(TREE, 2L).children())
        .hasOnlyElementsOfType(BranchVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(4L, 5L);
  }

  @Test
  void v3children() {
    assertThat(vertexOf(TREE, 3L).children())
        .hasOnlyElementsOfType(BranchVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(6L, 7L);
  }

  @Test
  void v4children() {
    assertThat(vertexOf(TREE, 4L).children())
        .hasOnlyElementsOfType(LeafVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(8L, 9L, 10L);
  }

  @Test
  void v7children() {
    assertThat(vertexOf(TREE, 7L).children())
        .hasOnlyElementsOfType(LeafVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(17L, 18L, 19L);
  }

  @Test
  void firstLeftDescendantOfRootIs2() {
    assertThat(vertexOf(TREE, 1L).leftDescendantOffsetAt(1)).isEqualTo(2);
  }

  @Test
  void secondLeftDescendantOfRootIs4() {
    assertThat(vertexOf(TREE, 1L).leftDescendantOffsetAt(2)).isEqualTo(4);
  }

  @Test
  void lastLeftDescendantOfRootIs8() {
    assertThat(vertexOf(TREE, 1L).leftDescendantOffsetAt(3)).isEqualTo(8);
  }

  @Test
  void firstLeftDescendantOf3Is6() {
    assertThat(vertexOf(TREE, 3L).leftDescendantOffsetAt(1)).isEqualTo(6);
  }

  @Test
  void lastLeftDescendantOf3Is14() {
    assertThat(vertexOf(TREE, 3L).leftDescendantOffsetAt(2)).isEqualTo(14);
  }

  @Test
  void lastLeftDescendantOf5Is11() {
    assertThat(vertexOf(TREE, 5L).leftDescendantOffsetAt(1)).isEqualTo(11);
  }

  @Test
  void firstRightDescendantOfRootIs3() {
    assertThat(vertexOf(TREE, 1L).rightDescendantOffsetAt(1)).isEqualTo(3);
  }

  @Test
  void secondRightDescendantOfRootIs7() {
    assertThat(vertexOf(TREE, 1L).rightDescendantOffsetAt(2)).isEqualTo(7);
  }

  @Test
  void lastRightDescendantOfRootIs19() {
    assertThat(vertexOf(TREE, 1L).rightDescendantOffsetAt(3)).isEqualTo(19);
  }

  @Test
  void firstRightDescendantOf3Is7() {
    assertThat(vertexOf(TREE, 3L).rightDescendantOffsetAt(1)).isEqualTo(7);
  }

  @Test
  void lastRightDescendantOf3Is19() {
    assertThat(vertexOf(TREE, 3L).rightDescendantOffsetAt(2)).isEqualTo(19);
  }

  @Test
  void lastRightDescendantOf5Is13() {
    assertThat(vertexOf(TREE, 5L).rightDescendantOffsetAt(1)).isEqualTo(13);
  }

}