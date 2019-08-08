package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static edu.agh.iga.adi.giraph.test.DummyProblem.INTERIM;
import static edu.agh.iga.adi.giraph.test.DummyProblem.MESH;
import static edu.agh.iga.adi.giraph.test.assertion.IgaElementAssertions.assertThatElement;
import static edu.agh.iga.adi.giraph.test.element.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.test.matrix.MatrixBuilder.matrixOfSize;

class OperationUtilTest {

  @Test
  void doesNotModifyIdentityMatrix() {
    IgaElement element = elementFor(INTERIM, MESH)
        .withSpecificMatrixA(
            matrixOfSize(6, 6).withValues(
                1, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 1
            )
        );

    partialForwardElimination(element, 1, 6);

    assertThatElement(element)
        .hasMa(
            matrixOfSize(6, 6).withValues(
                1, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 1
            )
        );
  }

  @Test
  void canEliminateUnitaryCoefficientMatrix() {
    IgaElement element = elementFor(INTERIM, MESH)
        .withSpecificMatrixA(
            matrixOfSize(6, 6).withValues(
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1
            )
        );

    partialForwardElimination(element, 1, 6);

    assertThatElement(element)
        .hasMa(
            matrixOfSize(6, 6).withValues(
                1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0
            )
        );
  }

  @Test
  void canEliminateFirstColumnOfCoefficientMatrix() {
    IgaElement element = elementFor(INTERIM, MESH)
        .withSpecificMatrixA(
            matrixOfSize(6, 6).withValues(
                1, 1, 1, 1, 1, 1,
                1, 2, 1, 1, 1, 1,
                1, 3, 1, 1, 1, 1,
                1, 4, 1, 1, 1, 1,
                1, 5, 1, 1, 1, 1,
                1, 6, 1, 1, 1, 1
            )
        );

    partialForwardElimination(element, 1, 6);

    assertThatElement(element)
        .hasMa(
            matrixOfSize(6, 6).withValues(
                1, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 2, 0, 0, 0, 0,
                0, 3, 0, 0, 0, 0,
                0, 4, 0, 0, 0, 0,
                0, 5, 0, 0, 0, 0
            )
        );
  }

}