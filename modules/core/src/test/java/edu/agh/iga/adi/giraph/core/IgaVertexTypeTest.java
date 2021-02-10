package edu.agh.iga.adi.giraph.core;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.core.IgaVertexType.vertexType;
import static org.assertj.core.api.Assertions.assertThat;

class IgaVertexTypeTest {

    private static final DirectionTree TREE_12 = new DirectionTree(12);
    private static final DirectionTree TREE_48 = new DirectionTree(48);

    @Test
    void zeroParentIsTheSameVertex() {
        assertThat(vertexType(TREE_12, 4).nthParent(TREE_12, 4, 0)).isEqualTo(4);
        assertThat(vertexType(TREE_12, 5).nthParent(TREE_12, 5, 0)).isEqualTo(5);
        assertThat(vertexType(TREE_12, 6).nthParent(TREE_12, 6, 0)).isEqualTo(6);
        assertThat(vertexType(TREE_12, 7).nthParent(TREE_12, 7, 0)).isEqualTo(7);
    }

    @Test
    void firstParentIsCorrect() {
        assertThat(vertexType(TREE_12, 4).nthParent(TREE_12, 4, 1)).isEqualTo(2);
        assertThat(vertexType(TREE_12, 5).nthParent(TREE_12, 5, 1)).isEqualTo(2);
        assertThat(vertexType(TREE_12, 6).nthParent(TREE_12, 6, 1)).isEqualTo(3);
        assertThat(vertexType(TREE_12, 7).nthParent(TREE_12, 7, 1)).isEqualTo(3);
    }

    @Test
    void worksIfTheParentIsRoot() {
        assertThat(vertexType(TREE_12, 4).nthParent(TREE_12, 4, 2)).isEqualTo(1);
        assertThat(vertexType(TREE_12, 5).nthParent(TREE_12, 5, 2)).isEqualTo(1);
        assertThat(vertexType(TREE_12, 6).nthParent(TREE_12, 6, 2)).isEqualTo(1);
        assertThat(vertexType(TREE_12, 7).nthParent(TREE_12, 7, 2)).isEqualTo(1);
    }

    @Test
    void worksForLargerTrees() {
        assertThat(vertexType(TREE_48, 8).nthParent(TREE_48, 8, 2)).isEqualTo(2);
        assertThat(vertexType(TREE_48, 9).nthParent(TREE_48, 9, 2)).isEqualTo(2);
        assertThat(vertexType(TREE_48, 10).nthParent(TREE_48, 10, 2)).isEqualTo(2);
        assertThat(vertexType(TREE_48, 11).nthParent(TREE_48, 11, 2)).isEqualTo(2);
        assertThat(vertexType(TREE_48, 12).nthParent(TREE_48, 12, 2)).isEqualTo(3);
        assertThat(vertexType(TREE_48, 13).nthParent(TREE_48, 13, 2)).isEqualTo(3);
        assertThat(vertexType(TREE_48, 14).nthParent(TREE_48, 14, 2)).isEqualTo(3);
        assertThat(vertexType(TREE_48, 15).nthParent(TREE_48, 15, 2)).isEqualTo(3);
    }

}