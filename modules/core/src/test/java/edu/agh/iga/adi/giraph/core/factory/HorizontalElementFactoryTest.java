package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.test.Problems;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.test.Problems.LINEAR_PROBLEM;
import static edu.agh.iga.adi.giraph.core.test.assertion.IgaElementAssertions.assertThatElement;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;

class HorizontalElementFactoryTest {

  private static final HorizontalElementFactory factory = new HorizontalElementFactory(Problems.MESH_12);

  @Test
  void canCreateElement() {
    // when
    IgaElement element = factory.createElement(LINEAR_PROBLEM, vertexOf(Problems.TREE_12, 8L));

    // then
    assertThatElement(element)
        .hasMaAbout(
            matrixOfSize(3).withValues(
                0.05, 0.108333, 0.008333,
                0.108333, 0.45, 0.108333,
                0.008333, 0.108333, 0.05
            )
        );
//        .hasMbAbout(
//            matrixOfSize(6, 14).withValues(
//                1001.01, 1001.02, 1001.03, 1001.04, 1001.05, 1001.06, 1001.07, 1001.08, 1001.09, 1001.10, 1001.11, 1001.12, 1001.13, 1001.14,
//                1002.01, 1002.02, 1002.03, 1002.04, 1002.05, 1002.06, 1002.07, 1002.08, 1002.09, 1002.10, 1002.11, 1002.12, 1002.13, 1002.14,
//                1003.01, 1003.02, 1003.03, 1003.04, 1003.05, 1003.06, 1003.07, 1003.08, 1003.09, 1003.10, 1003.11, 1003.12, 1003.13, 1003.14,
//                1004.01, 1004.02, 1004.03, 1004.04, 1004.05, 1004.06, 1004.07, 1004.08, 1004.09, 1004.10, 1004.11, 1004.12, 1004.13, 1004.14,
//                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00,
//                1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00
//            )
//        );
  }

}