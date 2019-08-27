package edu.agh.iga.adi.giraph.core.logging;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.logging.ElementFormatter.formatElement;
import static edu.agh.iga.adi.giraph.core.test.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.core.test.SmallProblem.INTERIM;
import static edu.agh.iga.adi.giraph.core.test.SmallProblem.MESH;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;
import static org.assertj.core.api.Assertions.assertThat;

class ElementFormatterTest {

  @Test
  void canFormatElement() {
    final IgaElement element = elementFor(INTERIM, MESH)
        .withMatrixA(matrixOfSize(6, 6).withIndexedValues())
        .withMatrixB(matrixOfSize(6, 14).withIndexedValues())
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThat(formatElement(element)).isEqualTo(
        " 1.1 1.2 1.3 1.4 1.5 1.6 |   1.1  1.2  1.3  1.4  1.5  1.6  1.7  1.8  1.9  1.1 1.11 1.12 1.13 1.14 |   1.1  1.2  1.3  1.4  1.5  1.6  1.7  1.8  1.9  1.1 1.11 1.12 1.13 1.14\n" +
            " 2.1 2.2 2.3 2.4 2.5 2.6 |   2.1  2.2  2.3  2.4  2.5  2.6  2.7  2.8  2.9  2.1 2.11 2.12 2.13 2.14 |   2.1  2.2  2.3  2.4  2.5  2.6  2.7  2.8  2.9  2.1 2.11 2.12 2.13 2.14\n" +
            " 3.1 3.2 3.3 3.4 3.5 3.6 |   3.1  3.2  3.3  3.4  3.5  3.6  3.7  3.8  3.9  3.1 3.11 3.12 3.13 3.14 |   3.1  3.2  3.3  3.4  3.5  3.6  3.7  3.8  3.9  3.1 3.11 3.12 3.13 3.14\n" +
            " 4.1 4.2 4.3 4.4 4.5 4.6 |   4.1  4.2  4.3  4.4  4.5  4.6  4.7  4.8  4.9  4.1 4.11 4.12 4.13 4.14 |   4.1  4.2  4.3  4.4  4.5  4.6  4.7  4.8  4.9  4.1 4.11 4.12 4.13 4.14\n" +
            " 5.1 5.2 5.3 5.4 5.5 5.6 |   5.1  5.2  5.3  5.4  5.5  5.6  5.7  5.8  5.9  5.1 5.11 5.12 5.13 5.14 |   5.1  5.2  5.3  5.4  5.5  5.6  5.7  5.8  5.9  5.1 5.11 5.12 5.13 5.14\n" +
            " 6.1 6.2 6.3 6.4 6.5 6.6 |   6.1  6.2  6.3  6.4  6.5  6.6  6.7  6.8  6.9  6.1 6.11 6.12 6.13 6.14 |   6.1  6.2  6.3  6.4  6.5  6.6  6.7  6.8  6.9  6.1 6.11 6.12 6.13 6.14\n"
    );
  }

  @Test
  void canFormatMissingMatrices() {
    final IgaElement element = elementFor(INTERIM, MESH)
        .withMatrixA(null)
        .withMatrixB(null)
        .withMatrixX(null)
        .build();

    assertThat(formatElement(element)).isEqualTo("");
  }

  @Test
  void canFormatOneMatrixWithOthersMissing() {
    final IgaElement element = elementFor(INTERIM, MESH)
        .withMatrixA(matrixOfSize(3, 3).withIndexedValues())
        .withMatrixB(null)
        .withMatrixX(null)
        .build();

    assertThat(formatElement(element)).isEqualTo(
        " 1.1 1.2 1.3 | x | x\n" +
            " 2.1 2.2 2.3 | x | x\n" +
            " 3.1 3.2 3.3 | x | x\n"
    );
  }

}