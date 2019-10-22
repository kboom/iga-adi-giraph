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
            inputSplit(TREE_24, 2, 3),
            inputSplit(TREE_24, 3, 3),
            inputSplit(TREE_24, 1, 1)
        );
  }

  @Test
  void splits768Into2() {
    assertThat(SPLITTER_768.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_768, 16, 5),
            inputSplit(TREE_768, 17, 5),
            inputSplit(TREE_768, 18, 5),
            inputSplit(TREE_768, 19, 5),
            inputSplit(TREE_768, 20, 5),
            inputSplit(TREE_768, 21, 5),
            inputSplit(TREE_768, 22, 5),
            inputSplit(TREE_768, 23, 5),
            inputSplit(TREE_768, 24, 5),
            inputSplit(TREE_768, 25, 5),
            inputSplit(TREE_768, 26, 5),
            inputSplit(TREE_768, 27, 5),
            inputSplit(TREE_768, 28, 5),
            inputSplit(TREE_768, 29, 5),
            inputSplit(TREE_768, 30, 5),
            inputSplit(TREE_768, 31, 5),
            inputSplit(TREE_768, 1, 4)
        );
  }

  @Test
  void splits768Into3() {
    assertThat(SPLITTER_768.allSplitsFor(3))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_768, 32, 4),
            inputSplit(TREE_768, 33, 4),
            inputSplit(TREE_768, 34, 4),
            inputSplit(TREE_768, 35, 4),
            inputSplit(TREE_768, 36, 4),
            inputSplit(TREE_768, 37, 4),
            inputSplit(TREE_768, 38, 4),
            inputSplit(TREE_768, 39, 4),
            inputSplit(TREE_768, 40, 4),
            inputSplit(TREE_768, 41, 4),
            inputSplit(TREE_768, 42, 4),
            inputSplit(TREE_768, 43, 4),
            inputSplit(TREE_768, 44, 4),
            inputSplit(TREE_768, 45, 4),
            inputSplit(TREE_768, 46, 4),
            inputSplit(TREE_768, 47, 4),
            inputSplit(TREE_768, 48, 4),
            inputSplit(TREE_768, 49, 4),
            inputSplit(TREE_768, 50, 4),
            inputSplit(TREE_768, 51, 4),
            inputSplit(TREE_768, 52, 4),
            inputSplit(TREE_768, 53, 4),
            inputSplit(TREE_768, 54, 4),
            inputSplit(TREE_768, 55, 4),
            inputSplit(TREE_768, 56, 4),
            inputSplit(TREE_768, 57, 4),
            inputSplit(TREE_768, 58, 4),
            inputSplit(TREE_768, 59, 4),
            inputSplit(TREE_768, 60, 4),
            inputSplit(TREE_768, 61, 4),
            inputSplit(TREE_768, 62, 4),
            inputSplit(TREE_768, 63, 4),
            inputSplit(TREE_768, 2, 4),
            inputSplit(TREE_768, 3, 4),
            inputSplit(TREE_768, 1, 1)
        );
  }

  private IgaInputSplit inputSplit(DirectionTree tree, int root, int height) {
    return new IgaInputSplit(vertexOf(tree, root), height);
  }

}