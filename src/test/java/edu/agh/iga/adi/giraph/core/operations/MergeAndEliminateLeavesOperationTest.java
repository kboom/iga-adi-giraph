package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.test.DummyProblem.*;
import static edu.agh.iga.adi.giraph.test.assertion.TransformableRegionAssertions.assertThatRegion;
import static edu.agh.iga.adi.giraph.test.element.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.test.matrix.MatrixBuilder.matrixOfSize;
import static org.assertj.core.api.Assertions.assertThat;

class MergeAndEliminateLeavesOperationTest {

  @Test
  void messageContainsSourceId() {
    assertThat(messageSentFrom(elementFor(CHILD_LEAF, MESH).build()))
        .extracting(MergeAndEliminateLeavesMessage::getSrcId)
        .isEqualTo(CHILD_LEAF_ID);
  }

  @Test
  void messageContainsFragmentOfCoefficientMatrix() {
    assertThatRegion(messageSentFrom(
        elementFor(CHILD_LEAF, MESH)
            .withMatrixA(matrixOfSize(6, 6).withValues(
                11, 12, 13, 14, 15, 16,
                21, 22, 23, 24, 25, 26,
                31, 32, 33, 34, 35, 36,
                41, 42, 43, 44, 45, 46,
                51, 52, 53, 54, 55, 56,
                61, 62, 63, 64, 65, 66
            )).build()
    ).getMa())
        .isOfSize(3, 3)
        .hasElementsMatching(
            matrixOfSize(3, 3).withValues(
                11, 12, 13,
                21, 22, 23,
                31, 32, 33
            )
        );
  }

  private MergeAndEliminateLeavesMessage messageSentFrom(IgaElement element) {
    return MERGE_AND_ELIMINATE_LEAVES_OPERATION.sendMessage(BRANCHING_LEAF, element);
  }

}