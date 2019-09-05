package edu.agh.iga.adi.giraph.core.setup;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.setup.VertexDependencies.coefficientsFor;
import static edu.agh.iga.adi.giraph.core.setup.VertexDependencies.verticesDependingOn;
import static edu.agh.iga.adi.giraph.core.test.Tree12.*;
import static org.assertj.core.api.Assertions.assertThat;

class VertexDependenciesTest {

  @Test
  void leadingBranch() {
    assertThat(verticesDependingOn(BRANCH_4)).containsExactlyInAnyOrder(8L, 9L, 10L, 11L, 12L);
  }

  @Test
  void middleBranch() {
    assertThat(verticesDependingOn(BRANCH_5)).containsExactlyInAnyOrder(10L, 11L, 12L, 13L, 14L);
  }

  @Test
  void trailingBranch() {
    assertThat(verticesDependingOn(BRANCH_7)).containsExactlyInAnyOrder(18L, 19L);
  }

  @Test
  void b4CoefficientsForL8() {
    assertThat(coefficientsFor(BRANCH_4, LEAF_8_ID)).containsExactlyInAnyOrder(0, 1, 2);
  }

  @Test
  void b4coefficientsForL9() {
    assertThat(coefficientsFor(BRANCH_4, LEAF_9_ID)).containsExactlyInAnyOrder(1, 2, 3);
  }

  @Test
  void b4CoefficientsForL10() {
    assertThat(coefficientsFor(BRANCH_4, LEAF_10_ID)).containsExactlyInAnyOrder(2, 3, 4);
  }

  @Test
  void b4CoefficientsForL11() {
    assertThat(coefficientsFor(BRANCH_4, LEAF_11_ID)).containsExactlyInAnyOrder(3, 4);
  }

  @Test
  void b5CoefficientsForL11() {
    assertThat(coefficientsFor(BRANCH_5, LEAF_11_ID)).containsExactly(0);
  }

  @Test
  void b5CoefficientsForL12() {
    assertThat(coefficientsFor(BRANCH_5, LEAF_12_ID)).containsExactly(0);
  }

  @Test
  void b5CoefficientsForL13() {
    assertThat(coefficientsFor(BRANCH_5, LEAF_13_ID)).containsExactly(0, 1);
  }

  @Test
  void b5CoefficientsForL14() {
    assertThat(coefficientsFor(BRANCH_5, LEAF_14_ID)).containsExactly(0, 1, 2);
  }

  @Test
  void b5CoefficientsForL15() {
    assertThat(coefficientsFor(BRANCH_5, LEAF_15_ID)).containsExactly(1, 2);
  }

  @Test
  void b5CoefficientsForL16() {
    assertThat(coefficientsFor(BRANCH_5, LEAF_16_ID)).containsExactly(2);
  }

}