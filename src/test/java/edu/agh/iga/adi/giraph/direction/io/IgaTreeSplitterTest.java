package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static org.assertj.core.api.Assertions.assertThat;

final class IgaTreeSplitterTest {

  private static final DirectionTree TREE_12 = new DirectionTree(12);
  private static final IgaTreeSplitter SPLITTER_12 = new IgaTreeSplitter(TREE_12);

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
            inputSplit(TREE_12, 4L, 2),
            inputSplit(TREE_12, 5L, 2),
            inputSplit(TREE_12, 1L, 2)
        );
  }

  private IgaInputSplit inputSplit(DirectionTree tree, long root, int height) {
    return new IgaInputSplit(vertexOf(tree, root), height);
  }

}