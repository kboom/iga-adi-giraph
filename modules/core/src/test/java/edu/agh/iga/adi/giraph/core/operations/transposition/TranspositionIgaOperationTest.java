package edu.agh.iga.adi.giraph.core.operations.transposition;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TranspositionIgaMessage;
import lombok.val;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TRANSPOSITION_IGA_OPERATION;
import static edu.agh.iga.adi.giraph.core.test.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.core.test.SmallProblem.MESH;
import static edu.agh.iga.adi.giraph.core.test.Tree12.*;
import static edu.agh.iga.adi.giraph.core.test.assertion.IgaElementAssertions.assertThatElement;
import static edu.agh.iga.adi.giraph.core.test.assertion.IgaMessageAssertions.assertThatMessage;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;

final class TranspositionIgaOperationTest {

  @Test
  void branch4SendsProperMessageToLeaf8() {
    val element = elementFor(BRANCH_4, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_8, element))
        .hasSrc(BRANCH_4_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(5, 3).withValues(
                1.1, 1.2, 1.3,
                2.1, 2.2, 2.3,
                3.1, 3.2, 3.3,
                4.1, 4.2, 4.3,
                5.1, 5.2, 5.3
            )
        );
  }

  @Test
  void branch4SendsProperMessageToLeaf9() {
    val element = elementFor(BRANCH_4, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_9, element))
        .hasSrc(BRANCH_4_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(5, 3).withValues(
                1.2, 1.3, 1.4,
                2.2, 2.3, 2.4,
                3.2, 3.3, 3.4,
                4.2, 4.3, 4.4,
                5.2, 5.3, 5.4
            )
        );
  }

  @Test
  void branch4SendsProperMessageToLeaf10() {
    val element = elementFor(BRANCH_4, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_10, element))
        .hasSrc(BRANCH_4_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(5, 3).withValues(
                1.3, 1.4, 1.5,
                2.3, 2.4, 2.5,
                3.3, 3.4, 3.5,
                4.3, 4.4, 4.5,
                5.3, 5.4, 5.5
            )
        );
  }

  @Test
  void branch4SendsProperMessageToLeaf19() {
    val element = elementFor(BRANCH_4, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_19, element))
        .hasSrc(BRANCH_4_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(5, 3).withValues(
                1.12, 1.13, 1.14,
                2.12, 2.13, 2.14,
                3.12, 3.13, 3.14,
                4.12, 4.13, 4.14,
                5.12, 5.13, 5.14
            )
        );
  }

  @Test
  void branch5SendsProperMessageToLeaf8() {
    val element = elementFor(BRANCH_5, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_8, element))
        .hasSrc(BRANCH_5_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(3).withValues(
                2.1, 2.2, 2.3,
                3.1, 3.2, 3.3,
                4.1, 4.2, 4.3
            )
        );
  }

  @Test
  void branch5SendsProperMessageToLeaf9() {
    val element = elementFor(BRANCH_5, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_9, element))
        .hasSrc(BRANCH_5_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(3).withValues(
                2.2, 2.3, 2.4,
                3.2, 3.3, 3.4,
                4.2, 4.3, 4.4
            )
        );
  }

  @Test
  void branch6SendsProperMessageToLeaf8() {
    val element = elementFor(BRANCH_6, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_8, element))
        .hasSrc(BRANCH_6_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(3).withValues(
                2.1, 2.2, 2.3,
                3.1, 3.2, 3.3,
                4.1, 4.2, 4.3
            )
        );
  }

  @Test
  void branch6SendsProperMessageToLeaf19() {
    val element = elementFor(BRANCH_6, MESH)
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThatMessage(messageTo(LEAF_19, element))
        .hasSrc(BRANCH_6_ID)
        .hasMatrix(
            TranspositionIgaMessage::getMxp,
            matrixOfSize(3).withValues(
                2.12, 2.13, 2.14,
                3.12, 3.13, 3.14,
                4.12, 4.13, 4.14
            )
        );
  }

  @Test
  void leaf8CanConsumeFromBranch4() {
    val element = elementFor(LEAF_8, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(0))
        .withMatrixB(matrixOfSize(6, 14).withValue(0))
        .build();

    val msg = new TranspositionIgaMessage(
        BRANCH_4_ID,
        matrixOfSize(5, 3).withValues(
            0.167, 0.833, 1.000,
            0.167, 0.833, 1.000,
            0.167, 0.833, 1.000,
            0.167, 0.833, 1.000,
            0.167, 0.833, 1.000
        )
    );

    assertThatElement(elementAfterConsuming(element, msg))
        .hasMbAbout(
            matrixOfSize(6, 14).withValues(
                0.167, 0.167, 0.167, 0.167, 0.167, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.4165, 0.4165, 0.4165, 0.4165, 0.4165, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        );
  }

  @Test
  void leaf8CanConsumeFromBranch5() {
    val element = elementFor(LEAF_8, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(0))
        .withMatrixB(matrixOfSize(6, 14).withValue(0))
        .build();

    val msg = new TranspositionIgaMessage(
        BRANCH_5_ID,
        matrixOfSize(3, 3).withValues(
            0.167, 0.833, 1.000,
            0.167, 0.833, 1.000,
            0.167, 0.833, 1.000
        )
    );

    assertThatElement(elementAfterConsuming(element, msg))
        .hasMbAbout(
            matrixOfSize(6, 14).withValues(
                0, 0, 0, 0, 0, 0.167, 0.167, 0.167, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0.4165, 0.4165, 0.4165, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        );
  }

  @Test
  void leaf9CanConsumeFromBranch4() {
    val element = elementFor(LEAF_9, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(0))
        .withMatrixB(matrixOfSize(6, 14).withValue(0))
        .build();

    val msg = new TranspositionIgaMessage(
        BRANCH_4_ID,
        matrixOfSize(5, 3).withValues(
            0.833, 1.000, 1.000,
            0.833, 1.000, 1.000,
            0.833, 1.000, 1.000,
            0.833, 1.000, 1.000,
            0.833, 1.000, 1.000
        )
    );

    assertThatElement(elementAfterConsuming(element, msg))
        .hasMbAbout(
            matrixOfSize(6, 14).withValues(
                0.4165, 0.4165, 0.4165, 0.4165, 0.4165, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        );
  }

  @Test
  void leaf10CanConsumeFromBranch4() {
    val element = elementFor(LEAF_10, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(0))
        .withMatrixB(matrixOfSize(6, 14).withValue(0))
        .build();

    val msg = new TranspositionIgaMessage(
        BRANCH_4_ID,
        matrixOfSize(5, 3).withValues(
            1, 1, 1,
            1, 1, 1,
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
        )
    );

    assertThatElement(elementAfterConsuming(element, msg))
        .hasMbAbout(
            matrixOfSize(6, 14).withValues(
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        );
  }

  @Test
  void leaf19CanConsumeFromBranch4() {
    val element = elementFor(LEAF_19, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(0))
        .withMatrixB(matrixOfSize(6, 14).withValue(0))
        .build();

    val msg = new TranspositionIgaMessage(
        BRANCH_4_ID,
        matrixOfSize(5, 3).withValues(
            1.000, 0.833, 0.167,
            1.000, 0.833, 0.167,
            1.000, 0.833, 0.167,
            1.000, 0.833, 0.167,
            1.000, 0.833, 0.167
        )
    );

    assertThatElement(elementAfterConsuming(element, msg))
        .hasMbAbout(
            matrixOfSize(6, 14).withValues(
                0.333333, 0.333333, 0.333333, 0.333333, 0.333333, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.4165, 0.4165, 0.4165, 0.4165, 0.4165, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0.167, 0.167, 0.167, 0.167, 0.167, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        );
  }

  private TranspositionIgaMessage messageTo(IgaVertex dst, IgaElement element) {
    return TRANSPOSITION_IGA_OPERATION.sendMessage(dst, element);
  }

  private IgaElement elementAfterConsuming(IgaElement element, TranspositionIgaMessage msg) {
    TRANSPOSITION_IGA_OPERATION.consumeMessage(element, msg, DIRECTION_TREE);
    return element;
  }

}