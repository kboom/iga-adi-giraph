package edu.agh.iga.adi.giraph.core.setup;

import edu.agh.iga.adi.giraph.core.IgaContext;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.setup.Initialisation.InitialisationIgaMessage;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.test.IgaElementBuilder.elementFor;
import static edu.agh.iga.adi.giraph.core.test.Tree12.*;
import static edu.agh.iga.adi.giraph.test.util.MatrixBuilder.matrixOfSize;
import static edu.agh.iga.adi.giraph.test.util.assertion.TransformableRegionAssertions.assertThatRegion;
import static org.assertj.core.api.Assertions.assertThat;

// todo for leaf 11 we get only 1 row from left and 1 row from right, not 3 in total thus it fails!
class InitialisationTest {

  private static final IgaContext CONTEXT = IgaContext.builder()
      .mesh(MESH)
      .tree(DIRECTION_TREE)
      .build();

  private static final Initialisation INIT = new Initialisation(
      CONTEXT,
      new HorizontalElementFactory(MESH),
      partialSolution -> partialSolution::valueAt
  );

  @Test
  void canSendTo11() {
    assertThat(messagesSentFrom(
        elementFor(BRANCH_4, MESH)
            .withSpecificMatrixX(matrixOfSize(5, 14).withIndexedValues())
    )).hasSize(5)
        .anySatisfy(to8 -> {
          assertThat(to8.getDstId()).isEqualTo(LEAF_8_ID);
          assertThatRegion(to8.getMxp())
              .isOfSize(3, 14)
              .hasElementsMatching(
                  matrixOfSize(3, 14).withValues(
                      1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 1.10, 1.11, 1.12, 1.13, 1.14,
                      2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9, 2.10, 2.11, 2.12, 2.13, 2.14,
                      3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9, 3.10, 3.11, 3.12, 3.13, 3.14
                  )
              );
        })
        .anySatisfy(to9 -> {
          assertThat(to9.getDstId()).isEqualTo(LEAF_9_ID);
          assertThatRegion(to9.getMxp())
              .isOfSize(3, 14)
              .hasElementsMatching(
                  matrixOfSize(3, 14).withValues(
                      2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9, 2.10, 2.11, 2.12, 2.13, 2.14,
                      3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9, 3.10, 3.11, 3.12, 3.13, 3.14,
                      4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9, 4.10, 4.11, 4.12, 4.13, 4.14
                  )
              );
        })
        .anySatisfy(to10 -> {
          assertThat(to10.getDstId()).isEqualTo(LEAF_10_ID);
          assertThatRegion(to10.getMxp())
              .isOfSize(3, 14)
              .hasElementsMatching(
                  matrixOfSize(3, 14).withValues(
                      3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9, 3.10, 3.11, 3.12, 3.13, 3.14,
                      4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9, 4.10, 4.11, 4.12, 4.13, 4.14,
                      5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8, 5.9, 5.10, 5.11, 5.12, 5.13, 5.14
                  )
              );
        })
        .anySatisfy(to11 -> {
          assertThat(to11.getDstId()).isEqualTo(LEAF_11_ID);
          assertThatRegion(to11.getMxp())
              .isOfSize(2, 14)
              .hasElementsMatching(
                  matrixOfSize(2, 14).withValues(
                      4.1, 4.2, 4.3, 4.4, 4.5, 4.6, 4.7, 4.8, 4.9, 4.10, 4.11, 4.12, 4.13, 4.14,
                      5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8, 5.9, 5.10, 5.11, 5.12, 5.13, 5.14
                  )
              );
        })
        .anySatisfy(to12 -> {
          assertThat(to12.getDstId()).isEqualTo(LEAF_12_ID);
          assertThatRegion(to12.getMxp())
              .isOfSize(1, 14)
              .hasElementsMatching(
                  matrixOfSize(1, 14).withValues(
                      5.1, 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8, 5.9, 5.10, 5.11, 5.12, 5.13, 5.14
                  )
              );
        });
  }

  private Stream<InitialisationIgaMessage> messagesSentFrom(IgaElement element) {
    return INIT.sendMessages(vertexOf(DIRECTION_TREE, element.id), element);
  }

}