package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static org.assertj.core.api.Assertions.assertThat;

final class IgaTreeSplitterTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);
  private static final IgaTreeSplitter SPLITTER_12 = new IgaTreeSplitter(TREE_12);

  private static final DirectionTree TREE_24 = new DirectionTree(24);
  private static final IgaTreeSplitter SPLITTER_24 = new IgaTreeSplitter(TREE_24);

  private static final DirectionTree TREE_768 = new DirectionTree(768);
  private static final IgaTreeSplitter SPLITTER_768 = new IgaTreeSplitter(TREE_768);

  @Test
  void splits12Into1() {
    assertThat(SPLITTER_12.allSplitsFor(1))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_12, 1, 3)
        );
  }

  @Test
  void splits12Into2() {
    assertThat(SPLITTER_12.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_12, 2, 2),
            inputSplit(TREE_12, 3, 2),
            inputSplit(TREE_12, 1, 1)
        );
  }

  @Test
  void splits12Into4() {
    assertThat(SPLITTER_12.allSplitsFor(4))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_12, 1, 2),
            inputSplit(TREE_12, 4, 1),
            inputSplit(TREE_12, 5, 1),
            inputSplit(TREE_12, 6, 1),
            inputSplit(TREE_12, 7, 1)
        );
  }

  @Test
  void splits12Into8() {
    assertThat(SPLITTER_12.allSplitsFor(8))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_12, 1, 2),
            inputSplit(TREE_12, 4, 1),
            inputSplit(TREE_12, 5, 1),
            inputSplit(TREE_12, 6, 1),
            inputSplit(TREE_12, 7, 1)
        );
  }

  @Test
  void splits24Into1() {
    assertThat(SPLITTER_24.allSplitsFor(1))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_24, 1, 4)
        );
  }

  @Test
  void splits24Into2() {
    assertThat(SPLITTER_24.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_24, 1, 1),
            inputSplit(TREE_24, 2, 3),
            inputSplit(TREE_24, 3, 3)
        );
  }

  @Test
  void splits24Into3() {
    assertThat(SPLITTER_24.allSplitsFor(3))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_24, 1, 3),
            inputSplit(TREE_24, 8, 1),
            inputSplit(TREE_24, 9, 1),
            inputSplit(TREE_24, 10, 1),
            inputSplit(TREE_24, 11, 1),
            inputSplit(TREE_24, 12, 1),
            inputSplit(TREE_24, 13, 1)
        );
  }

  @Test
  void splits24Into4() {
    assertThat(SPLITTER_24.allSplitsFor(4))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_24, 1, 2),
            inputSplit(TREE_24, 4, 2),
            inputSplit(TREE_24, 5, 2),
            inputSplit(TREE_24, 6, 2),
            inputSplit(TREE_24, 7, 2)
        );
  }

  @Test
  void splits768Into2() {
    assertThat(SPLITTER_768.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_768, 1, 1),
            inputSplit(TREE_768, 2, 8),
            inputSplit(TREE_768, 3, 8)
        );
  }

  @Test
  void splits768Into4() {
    assertThat(SPLITTER_768.allSplitsFor(4))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_768, 1, 2),
            inputSplit(TREE_768, 4, 7),
            inputSplit(TREE_768, 5, 7),
            inputSplit(TREE_768, 6, 7),
            inputSplit(TREE_768, 7, 7)
        );
  }

  private IgaInputSplit inputSplit(DirectionTree tree, int root, int height) {
    return new IgaInputSplit(vertexOf(tree, root), height);
  }

}