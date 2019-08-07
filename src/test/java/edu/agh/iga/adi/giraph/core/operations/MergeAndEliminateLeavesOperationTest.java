package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.test.DummyProblem.*;
import static edu.agh.iga.adi.giraph.test.assertion.IgaElementAssertions.assertThatElement;
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
            .withSpecificMatrixA(
                matrixOfSize(6, 6).withValues(
                    11, 12, 13, 14, 15, 16,
                    21, 22, 23, 24, 25, 26,
                    31, 32, 33, 34, 35, 36,
                    41, 42, 43, 44, 45, 46,
                    51, 52, 53, 54, 55, 56,
                    61, 62, 63, 64, 65, 66
                ))
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

  @Test
  void canConsumeMessageFromLeafLeaf() {
    IgaElement element = elementFor(BRANCHING_LEAF, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(1000))
        .withMatrixB(matrixOfSize(6, 14).withValue(1000))
        .build();

    MergeAndEliminateLeavesMessage msg = new MergeAndEliminateLeavesMessage(
        LEFT_LEAF_ID,
        matrixOfSize(3, 3).withValues(
            11, 12, 13,
            21, 22, 23,
            31, 32, 33
        ),
        matrixOfSize(3, 14).withValues(
            1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
            2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
            3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14
        )
    );

    assertThatElement(elementAfterConsuming(element, msg))
        .hasMa(
            matrixOfSize(6, 6).withValues(
                1011, 1012, 1013, 1000, 1000, 1000,
                1021, 1022, 1023, 1000, 1000, 1000,
                1031, 1032, 1033, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000
            )
        )
        .hasMb(
            matrixOfSize(6, 14).withValues(
                1001.01, 1001.02, 1001.03, 1001.04, 1001.05, 1001.06, 1001.07, 1001.08, 1001.09, 1001.10, 1001.11, 1001.12, 1001.13, 1001.14,
                1002.01, 1002.02, 1002.03, 1002.04, 1002.05, 1002.06, 1002.07, 1002.08, 1002.09, 1002.10, 1002.11, 1002.12, 1002.13, 1002.14,
                1003.01, 1003.02, 1003.03, 1003.04, 1003.05, 1003.06, 1003.07, 1003.08, 1003.09, 1003.10, 1003.11, 1003.12, 1003.13, 1003.14,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00
            )
        );
  }


  private MergeAndEliminateLeavesMessage messageSentFrom(IgaElement element) {
    return MERGE_AND_ELIMINATE_LEAVES_OPERATION.sendMessage(BRANCHING_LEAF, element);
  }

  private IgaElement elementAfterConsuming(IgaElement element, MergeAndEliminateLeavesMessage msg) {
    MERGE_AND_ELIMINATE_LEAVES_OPERATION.consumeMessage(element, msg, DIRECTION_TREE);
    return element;
  }

}