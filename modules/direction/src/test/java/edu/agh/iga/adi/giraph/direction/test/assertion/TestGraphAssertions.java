package edu.agh.iga.adi.giraph.direction.test.assertion;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.IntWritable;
import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static edu.agh.iga.adi.giraph.test.util.assertion.MatrixUtil.weakMatrix;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ojalgo.function.aggregator.Aggregator.SUM;

public final class TestGraphAssertions extends AbstractAssert<TestGraphAssertions, TestGraph<IntWritable, IgaElementWritable, IgaOperationWritable>> {

  private TestGraphAssertions(TestGraph<IntWritable, IgaElementWritable, IgaOperationWritable> vertices) {
    super(vertices, TestGraphAssertions.class);
  }

  public static TestGraphAssertions assertThatGraph(TestGraph<IntWritable, IgaElementWritable, IgaOperationWritable> graph) {
    return new TestGraphAssertions(graph);
  }


  public TestGraphAssertions hasElementWithUnknowns(int l, PrimitiveDenseStore ds, int precision) {
    assertThat(weakMatrix(actual.getVertex(new IntWritable(l)).getValue().getElement().mx, precision)).isEqualTo(ds);
    return this;
  }

  public TestGraphAssertions allBranchElementsHaveUnknowns(DirectionTree tree, PrimitiveDenseStore ds, int precision) {
    rangeClosed(tree.firstIndexOfBranchingRow(), tree.lastIndexOfBranchingRow())
        .forEachOrdered(l -> hasElementWithUnknowns(l, ds, precision));
    return this;
  }

  public TestGraphAssertions leavesHaveChecksums(DirectionTree tree, double[] checksums, int precision) {
    val sums = rangeClosed(tree.firstIndexOfBranchingRow(), tree.lastIndexOfBranchingRow())
        .boxed()
        .map(l -> weakMatrix(actual.getVertex(new IntWritable(l)).getValue().getElement().mx, precision))
        .map(m -> m.aggregateAll(SUM))
        .mapToDouble(d -> d)
        .toArray();

    assertThat(sums).containsExactly(checksums);
    return this;
  }

}
