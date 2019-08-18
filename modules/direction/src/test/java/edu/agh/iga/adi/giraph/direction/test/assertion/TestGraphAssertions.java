package edu.agh.iga.adi.giraph.direction.test.assertion;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;
import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.util.stream.LongStream;

import static edu.agh.iga.adi.giraph.test.util.assertion.MatrixUtil.weakMatrix;
import static org.assertj.core.api.Assertions.assertThat;

public final class TestGraphAssertions extends AbstractAssert<TestGraphAssertions, TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable>> {

  private TestGraphAssertions(TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> vertices) {
    super(vertices, TestGraphAssertions.class);
  }

  public static TestGraphAssertions assertThatGraph(TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph) {
    return new TestGraphAssertions(graph);
  }


  public TestGraphAssertions hasElementWithUnknowns(long l, PrimitiveDenseStore ds) {
    assertThat(actual.getVertex(new LongWritable(l)).getValue().getElement().mx).isEqualTo(ds);
    return this;
  }

  public TestGraphAssertions allBranchElementsHaveUnknowns(DirectionTree tree, PrimitiveDenseStore ds, int precision) {
    LongStream.rangeClosed(tree.firstIndexOfBranchingRow(), tree.lastIndexOfBranchingRow())
        .forEachOrdered(l -> assertThat(weakMatrix(actual.getVertex(new LongWritable(l)).getValue().getElement().mx, precision)).isEqualTo(ds));
    return this;
  }

}
