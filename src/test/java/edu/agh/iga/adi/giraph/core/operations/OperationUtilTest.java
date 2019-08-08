package edu.agh.iga.adi.giraph.core.operations;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.operations.OperationUtil.partialForwardElimination;
import static edu.agh.iga.adi.giraph.test.SmallProblem.INTERIM;
import static edu.agh.iga.adi.giraph.test.SmallProblem.MESH;
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

  @Test
  void canEliminateNonTrivialCoefficientMatrix() {
    IgaElement element = elementFor(INTERIM, MESH)
        .withSpecificMatrixA(
            matrixOfSize(6, 6).withValues(
                4, 0, 1, 0, 1, 0,
                8, 1, 0, 1, 0, 1,
                4, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 3, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0
            )
        );

    partialForwardElimination(element, 1, 6);

    assertThatElement(element)
        .hasMa(
            matrixOfSize(6, 6).withValues(
                1.0, 0.0, 0.25, 0.0, 0.25, 0.0,
                0.0, 1.0, -2.0, 1.0, -2.0, 1.0,
                0.0, 0.0, -1.0, 0.0, -1.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 3.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, -0.25, 0.0, -0.25, 0.0
            )
        );
  }

  @Test
  void doesNotModifyForcingMatrixIfCoefficientsIsIdentityMatrix() {
    IgaElement element = elementFor(INTERIM, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValues(
            1, 0, 0, 0, 0, 0,
            0, 1, 0, 0, 0, 0,
            0, 0, 1, 0, 0, 0,
            0, 0, 0, 1, 0, 0,
            0, 0, 0, 0, 1, 0,
            0, 0, 0, 0, 0, 1
        ))
        .withMatrixB(
            matrixOfSize(6, 14).withValues(
                1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
                2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
                3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14,
                4.01, 4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.10, 4.11, 4.12, 4.13, 4.14,
                5.01, 5.02, 5.03, 5.04, 5.05, 5.06, 5.07, 5.08, 5.09, 5.10, 5.11, 5.12, 5.13, 5.14,
                6.01, 6.02, 6.03, 6.04, 6.05, 6.06, 6.07, 6.08, 6.09, 6.10, 6.11, 6.12, 6.13, 6.14
            )
        ).build();

    partialForwardElimination(element, 1, 6);

    assertThatElement(element)
        .hasMb(
            matrixOfSize(6, 14).withValues(
                1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
                2.01, 2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
                3.01, 3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14,
                4.01, 4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.10, 4.11, 4.12, 4.13, 4.14,
                5.01, 5.02, 5.03, 5.04, 5.05, 5.06, 5.07, 5.08, 5.09, 5.10, 5.11, 5.12, 5.13, 5.14,
                6.01, 6.02, 6.03, 6.04, 6.05, 6.06, 6.07, 6.08, 6.09, 6.10, 6.11, 6.12, 6.13, 6.14
            )
        );
  }

  @Test
  void partiallyEliminatesForcingMatrixIfNonUnitaryCoefficientMatrixUsed() {
    IgaElement element = elementFor(INTERIM, MESH)
        .withMatrixA(matrixOfSize(6, 6).withValues(
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1
        ))
        .withMatrixB(
            matrixOfSize(6, 14).withValues(
                4, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16,
                0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0,
                0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )
        ).build();

    partialForwardElimination(element, 1, 6);

    assertThatElement(element)
        .hasMb(
            matrixOfSize(6, 14).withValues(
                +04.00, +08.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +16.00,
                -04.00, -07.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, -16.00,
                -04.00, -08.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, -16.00,
                -04.00, -08.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +24.00, +00.00, +00.00, +00.00, -16.00,
                -04.00, +04.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, -16.00,
                +00.00, -08.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, +00.00, -16.00
            )
        );
  }

}