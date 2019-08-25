package edu.agh.iga.adi.giraph.core.operations.transposition;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TranspositionIgaMessage;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TRANSPOSITION_IGA_OPERATION;
import static edu.agh.iga.adi.giraph.core.test.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.core.test.SmallProblem.*;
import static edu.agh.iga.adi.giraph.core.test.assertion.IgaElementAssertions.assertThatElement;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;

final class TranspositionIgaOperationTest {

  @Test
  void canConsumeMessageFromFirstBranch() {
    IgaElement element = elementFor(LEFT_LEAF, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValue(0))
        .withMatrixB(matrixOfSize(6, 14).withValue(0))
        .build();

    TranspositionIgaMessage msg = new TranspositionIgaMessage(
        LEFT_BRANCH_ID,
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

  private IgaElement elementAfterConsuming(IgaElement element, TranspositionIgaMessage msg) {
    TRANSPOSITION_IGA_OPERATION.consumeMessage(element, msg, DIRECTION_TREE);
    return element;
  }

}