package edu.agh.iga.adi.giraph.core.operations.transposition;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TranspositionIgaMessage;
import lombok.val;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TRANSPOSITION_IGA_OPERATION;
import static edu.agh.iga.adi.giraph.core.test.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.core.test.SmallProblem.MESH;
import static edu.agh.iga.adi.giraph.core.test.Tree12.*;
import static edu.agh.iga.adi.giraph.core.test.assertion.IgaElementAssertions.assertThatElement;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;

final class TranspositionIgaOperationTest {

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

  private IgaElement elementAfterConsuming(IgaElement element, TranspositionIgaMessage msg) {
    TRANSPOSITION_IGA_OPERATION.consumeMessage(element, msg, DIRECTION_TREE);
    return element;
  }

}