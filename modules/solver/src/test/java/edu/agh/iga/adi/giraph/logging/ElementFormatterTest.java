package edu.agh.iga.adi.giraph.logging;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.logging.ElementFormatter.formatElement;
import static edu.agh.iga.adi.giraph.test.SmallProblem.INTERIM;
import static edu.agh.iga.adi.giraph.test.SmallProblem.MESH;
import static edu.agh.iga.adi.giraph.test.element.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.test.matrix.MatrixBuilder.matrixOfSize;
import static org.assertj.core.api.Assertions.assertThat;

class ElementFormatterTest {

  @Test
  void canFormatElement() {
    final IgaElement element = elementFor(INTERIM, MESH)
        .withMatrixA(matrixOfSize(6, 6).withIndexedValues())
        .withMatrixB(matrixOfSize(6, 14).withIndexedValues())
        .withMatrixX(matrixOfSize(6, 14).withIndexedValues())
        .build();

    assertThat(formatElement(element)).isEqualTo("");
  }

}