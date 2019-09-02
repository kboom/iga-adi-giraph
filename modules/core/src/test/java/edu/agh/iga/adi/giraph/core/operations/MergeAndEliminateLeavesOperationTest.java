package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import edu.agh.iga.adi.giraph.core.test.SmallProblem;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MERGE_AND_ELIMINATE_LEAVES_OPERATION;
import static edu.agh.iga.adi.giraph.core.test.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.core.test.assertion.IgaElementAssertions.assertThatElement;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;
import static edu.agh.iga.adi.giraph.test.util.assertion.TransformableRegionAssertions.assertThatRegion;
import static org.assertj.core.api.Assertions.assertThat;

class MergeAndEliminateLeavesOperationTest {

  @Test
  void messageContainsSourceId() {
    assertThat(messageSentFrom(elementFor(SmallProblem.LEAF, SmallProblem.MESH).build()))
        .extracting(MergeAndEliminateLeavesMessage::getSrcId)
        .isEqualTo(SmallProblem.LEAF_ID);
  }

  @Test
  void messageContainsFragmentOfCoefficientMatrix() {
    assertThatRegion(messageSentFrom(
        elementFor(SmallProblem.LEAF, SmallProblem.MESH)
            .withSpecificMatrixA(
                matrixOfSize(3, 3).withValues(
                    11, 12, 13,
                    21, 22, 23,
                    31, 32, 33
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
  void canConsumeMessageFromLeftLeaf() {
    IgaElement element = elementFor(SmallProblem.BRANCH, SmallProblem.MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(1000))
        .withMatrixB(matrixOfSize(6, 14).withValue(1000))
        .build();

    MergeAndEliminateLeavesMessage msg = new MergeAndEliminateLeavesMessage(
        SmallProblem.LEFT_LEAF_ID,
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

  @Test
  void canConsumeMessageFromMiddleLeaf() {
    IgaElement element = elementFor(SmallProblem.BRANCH, SmallProblem.MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(1000))
        .withMatrixB(matrixOfSize(6, 14).withValue(1000))
        .build();

    MergeAndEliminateLeavesMessage msg = new MergeAndEliminateLeavesMessage(
        SmallProblem.MIDDLE_LEAF_ID,
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
                1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1011, 1012, 1013, 1000, 1000,
                1000, 1021, 1022, 1023, 1000, 1000,
                1000, 1031, 1032, 1033, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000
            )
        )
        .hasMb(
            matrixOfSize(6, 14).withValues(
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
                1001.01, 1001.02, 1001.03, 1001.04, 1001.05, 1001.06, 1001.07, 1001.08, 1001.09, 1001.10, 1001.11, 1001.12, 1001.13, 1001.14,
                1002.01, 1002.02, 1002.03, 1002.04, 1002.05, 1002.06, 1002.07, 1002.08, 1002.09, 1002.10, 1002.11, 1002.12, 1002.13, 1002.14,
                1003.01, 1003.02, 1003.03, 1003.04, 1003.05, 1003.06, 1003.07, 1003.08, 1003.09, 1003.10, 1003.11, 1003.12, 1003.13, 1003.14,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00
            )
        );
  }

  @Test
  void canConsumeMessageFromRightLeaf() {
    IgaElement element = elementFor(SmallProblem.BRANCH, SmallProblem.MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(1000))
        .withMatrixB(matrixOfSize(6, 14).withValue(1000))
        .build();

    MergeAndEliminateLeavesMessage msg = new MergeAndEliminateLeavesMessage(
        SmallProblem.RIGHT_LEAF_ID,
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
                1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1011, 1012, 1013, 1000,
                1000, 1000, 1021, 1022, 1023, 1000,
                1000, 1000, 1031, 1032, 1033, 1000,
                1000, 1000, 1000, 1000, 1000, 1000
            )
        )
        .hasMb(
            matrixOfSize(6, 14).withValues(
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
                1001.01, 1001.02, 1001.03, 1001.04, 1001.05, 1001.06, 1001.07, 1001.08, 1001.09, 1001.10, 1001.11, 1001.12, 1001.13, 1001.14,
                1002.01, 1002.02, 1002.03, 1002.04, 1002.05, 1002.06, 1002.07, 1002.08, 1002.09, 1002.10, 1002.11, 1002.12, 1002.13, 1002.14,
                1003.01, 1003.02, 1003.03, 1003.04, 1003.05, 1003.06, 1003.07, 1003.08, 1003.09, 1003.10, 1003.11, 1003.12, 1003.13, 1003.14,
                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00
            )
        );
  }

  @Test
  void canProcess() {
    IgaElement element = elementFor(SmallProblem.BRANCH, SmallProblem.MESH)
        .withMatrixA(
            matrixOfSize(6, 6).withValues(
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1
            )
        )
        .withMatrixB(
            matrixOfSize(6, 14).withValues(
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
            )
        )
        .withMatrixX(
            matrixOfSize(6, 14).withValues(
                1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
                2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
                3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14,
                4.01, 4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.10, 4.11, 4.12, 4.13, 4.14,
                5.01, 5.02, 5.03, 5.04, 5.05, 5.06, 5.07, 5.08, 5.09, 5.10, 5.11, 5.12, 5.13, 5.14,
                6.01, 6.02, 6.03, 6.04, 6.05, 6.06, 6.07, 6.08, 6.09, 6.10, 6.11, 6.12, 6.13, 6.14
            )
        )
        .build();

    assertThatElement(elementAfterProcessing(element))
        .hasMa(
            matrixOfSize(6, 6).withValues(
                1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                1, 1, 1, 1, 1, 1
            )
        )
        .hasMb(
            matrixOfSize(6, 14).withValues(
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
            )
        )
        .hasMx(
            matrixOfSize(6, 14).withValues(
                3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.1, 3.11, 3.12, 3.13, 3.14,
                1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.1, 1.11, 1.12, 1.13, 1.14,
                2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.1, 2.11, 2.12, 2.13, 2.14,
                4.01, 4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.1, 4.11, 4.12, 4.13, 4.14,
                5.01, 5.02, 5.03, 5.04, 5.05, 5.06, 5.07, 5.08, 5.09, 5.1, 5.11, 5.12, 5.13, 5.14,
                6.01, 6.02, 6.03, 6.04, 6.05, 6.06, 6.07, 6.08, 6.09, 6.1, 6.11, 6.12, 6.13, 6.14
            )
        );
  }

  private MergeAndEliminateLeavesMessage messageSentFrom(IgaElement element) {
    return MERGE_AND_ELIMINATE_LEAVES_OPERATION.sendMessage(SmallProblem.BRANCH, element);
  }

  private IgaElement elementAfterConsuming(IgaElement element, MergeAndEliminateLeavesMessage msg) {
    MERGE_AND_ELIMINATE_LEAVES_OPERATION.consumeMessage(element, msg, SmallProblem.DIRECTION_TREE);
    return element;
  }

  private IgaElement elementAfterProcessing(IgaElement element) {
    MERGE_AND_ELIMINATE_LEAVES_OPERATION.postConsume(element, SmallProblem.DIRECTION_TREE);
    return element;
  }

}