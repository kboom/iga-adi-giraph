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
            inputSplit(TREE_12, 1L, 3)
        );
  }

  @Test
  void splits12Into2() {
    assertThat(SPLITTER_12.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_12, 2L, 2),
            inputSplit(TREE_12, 3L, 2),
            inputSplit(TREE_12, 1L, 1)
        );
  }

  @Test
  void splits24Into1() {
    assertThat(SPLITTER_24.allSplitsFor(1))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_24, 1L, 4)
        );
  }

  @Test
  void splits24Into2() {
    assertThat(SPLITTER_24.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_24, 2L, 3),
            inputSplit(TREE_24, 3L, 3),
            inputSplit(TREE_24, 1L, 1)
        );
  }

  @Test
  void splits768Into2() {
    assertThat(SPLITTER_768.allSplitsFor(2))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_768, 16L, 5),
            inputSplit(TREE_768, 17L, 5),
            inputSplit(TREE_768, 18L, 5),
            inputSplit(TREE_768, 19L, 5),
            inputSplit(TREE_768, 20L, 5),
            inputSplit(TREE_768, 21L, 5),
            inputSplit(TREE_768, 22L, 5),
            inputSplit(TREE_768, 23L, 5),
            inputSplit(TREE_768, 24L, 5),
            inputSplit(TREE_768, 25L, 5),
            inputSplit(TREE_768, 26L, 5),
            inputSplit(TREE_768, 27L, 5),
            inputSplit(TREE_768, 28L, 5),
            inputSplit(TREE_768, 29L, 5),
            inputSplit(TREE_768, 30L, 5),
            inputSplit(TREE_768, 31L, 5),
            inputSplit(TREE_768, 1L, 4)
        );
  }

  @Test
  void splits768Into3() {
    assertThat(SPLITTER_768.allSplitsFor(3))
        .containsExactlyInAnyOrder(
            inputSplit(TREE_768, 32L, 4),
            inputSplit(TREE_768, 33L, 4),
            inputSplit(TREE_768, 34L, 4),
            inputSplit(TREE_768, 35L, 4),
            inputSplit(TREE_768, 36L, 4),
            inputSplit(TREE_768, 37L, 4),
            inputSplit(TREE_768, 38L, 4),
            inputSplit(TREE_768, 39L, 4),
            inputSplit(TREE_768, 40L, 4),
            inputSplit(TREE_768, 41L, 4),
            inputSplit(TREE_768, 42L, 4),
            inputSplit(TREE_768, 43L, 4),
            inputSplit(TREE_768, 44L, 4),
            inputSplit(TREE_768, 45L, 4),
            inputSplit(TREE_768, 46L, 4),
            inputSplit(TREE_768, 47L, 4),
            inputSplit(TREE_768, 48L, 4),
            inputSplit(TREE_768, 49L, 4),
            inputSplit(TREE_768, 50L, 4),
            inputSplit(TREE_768, 51L, 4),
            inputSplit(TREE_768, 52L, 4),
            inputSplit(TREE_768, 53L, 4),
            inputSplit(TREE_768, 54L, 4),
            inputSplit(TREE_768, 55L, 4),
            inputSplit(TREE_768, 56L, 4),
            inputSplit(TREE_768, 57L, 4),
            inputSplit(TREE_768, 58L, 4),
            inputSplit(TREE_768, 59L, 4),
            inputSplit(TREE_768, 60L, 4),
            inputSplit(TREE_768, 61L, 4),
            inputSplit(TREE_768, 62L, 4),
            inputSplit(TREE_768, 63L, 4),
            inputSplit(TREE_768, 2L, 4),
            inputSplit(TREE_768, 3L, 4),
            inputSplit(TREE_768, 1L, 1)
        );
  }

  private IgaInputSplit inputSplit(DirectionTree tree, long root, int height) {
    return new IgaInputSplit(vertexOf(tree, root), height);
  }

}