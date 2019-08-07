package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import edu.agh.iga.adi.giraph.test.IgaTestElementFactory;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;
import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.test.IgaTestElementFactory.MatrixCharacteristics.INDEXED;
import static org.assertj.core.api.Assertions.assertThat;

class MergeAndEliminateLeavesOperationTest {

  private static final DirectionTree directionTree = new DirectionTree(12);
  private static final Mesh mesh = aMesh().withElements(12).build();
  private static final IgaTestElementFactory ef = new IgaTestElementFactory(mesh);

  private static IgaVertex PARENT_LEAF = vertexOf(directionTree, 4L);
  private static final long LEFT_LEAF_ID = 8L;
  private static IgaVertex LEFT_LEAF = vertexOf(directionTree, LEFT_LEAF_ID);
  private static final long MIDDLE_LEAF_ID = 9L;
  private static IgaVertex MIDDLE_LEAF = vertexOf(directionTree, MIDDLE_LEAF_ID);
  private static final long RIGHT_LEAF_ID = 10L;
  private static IgaVertex RIGHT_LEAF = vertexOf(directionTree, RIGHT_LEAF_ID);

  @Test
  void messageFromLeftChildHasLeftChildSource() {
    assertThat(messageSentFrom(LEFT_LEAF))
        .extracting(MergeAndEliminateLeavesMessage::getSrcId)
        .isEqualTo(LEFT_LEAF_ID);
  }

  @Test
  void messageFromMiddleChildHasMiddleChildSource() {
    assertThat(messageSentFrom(MIDDLE_LEAF))
        .extracting(MergeAndEliminateLeavesMessage::getSrcId)
        .isEqualTo(MIDDLE_LEAF_ID);
  }

  @Test
  void messageFromRightChildHasRightChildSource() {
    assertThat(messageSentFrom(RIGHT_LEAF))
        .extracting(MergeAndEliminateLeavesMessage::getSrcId)
        .isEqualTo(RIGHT_LEAF_ID);
  }

  private MergeAndEliminateLeavesMessage messageSentFrom(IgaVertex leaf) {
    return MERGE_AND_ELIMINATE_LEAVES_OPERATION.sendMessage(PARENT_LEAF, ef.testElement(leaf, INDEXED));
  }

}