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
    assertThat(vertexOf(TREE, 1))
        .isInstanceOf(RootVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(1);
  }

  @ParameterizedTest
  @ValueSource(ints = {2, 3})
  void interimVertices(int id) {
    assertThat(vertexOf(TREE, id))
        .isInstanceOf(InterimVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(id);
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 5, 6, 7})
  void branchVertices(int id) {
    assertThat(vertexOf(TREE, id))
        .isInstanceOf(BranchVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(id);
  }

  @ParameterizedTest
  @ValueSource(ints = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19})
  void leafVertices(int id) {
    assertThat(vertexOf(TREE, id))
        .isInstanceOf(LeafVertex.class)
        .extracting(IgaVertex::id)
        .isEqualTo(id);
  }

  @Test
  void childrenRoot() {
    assertThat(vertexOf(TREE, 1).children())
        .hasOnlyElementsOfType(InterimVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(2, 3);
  }

  @Test
  void v2children() {
    assertThat(vertexOf(TREE, 2).children())
        .hasOnlyElementsOfType(BranchVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(4, 5);
  }

  @Test
  void v3children() {
    assertThat(vertexOf(TREE, 3).children())
        .hasOnlyElementsOfType(BranchVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(6, 7);
  }

  @Test
  void v4children() {
    assertThat(vertexOf(TREE, 4).children())
        .hasOnlyElementsOfType(LeafVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(8, 9, 10);
  }

  @Test
  void v7children() {
    assertThat(vertexOf(TREE, 7).children())
        .hasOnlyElementsOfType(LeafVertex.class)
        .extracting(IgaVertex::id)
        .containsExactlyInAnyOrder(17, 18, 19);
  }

  @Test
  void firstLeftDescendantOfRootIs2() {
    assertThat(vertexOf(TREE, 1).leftDescendantAt(1)).isEqualTo(2);
  }

  @Test
  void secondLeftDescendantOfRootIs4() {
    assertThat(vertexOf(TREE, 1).leftDescendantAt(2)).isEqualTo(4);
  }

  @Test
  void lastLeftDescendantOfRootIs8() {
    assertThat(vertexOf(TREE, 1).leftDescendantAt(3)).isEqualTo(8);
  }

  @Test
  void firstLeftDescendantOf3Is6() {
    assertThat(vertexOf(TREE, 3).leftDescendantAt(1)).isEqualTo(6);
  }

  @Test
  void lastLeftDescendantOf3Is14() {
    assertThat(vertexOf(TREE, 3).leftDescendantAt(2)).isEqualTo(14);
  }

  @Test
  void lastLeftDescendantOf5Is11() {
    assertThat(vertexOf(TREE, 5).leftDescendantAt(1)).isEqualTo(11);
  }

  @Test
  void firstRightDescendantOfRootIs3() {
    assertThat(vertexOf(TREE, 1).rightDescendantAt(1)).isEqualTo(3);
  }

  @Test
  void secondRightDescendantOfRootIs7() {
    assertThat(vertexOf(TREE, 1).rightDescendantAt(2)).isEqualTo(7);
  }

  @Test
  void lastRightDescendantOfRootIs19() {
    assertThat(vertexOf(TREE, 1).rightDescendantAt(3)).isEqualTo(19);
  }

  @Test
  void firstRightDescendantOf3Is7() {
    assertThat(vertexOf(TREE, 3).rightDescendantAt(1)).isEqualTo(7);
  }

  @Test
  void lastRightDescendantOf3Is19() {
    assertThat(vertexOf(TREE, 3).rightDescendantAt(2)).isEqualTo(19);
  }

  @Test
  void lastRightDescendantOf5Is13() {
    assertThat(vertexOf(TREE, 5).rightDescendantAt(1)).isEqualTo(13);
  }

}