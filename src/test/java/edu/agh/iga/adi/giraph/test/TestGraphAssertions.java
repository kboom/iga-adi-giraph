package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;
import org.assertj.core.api.AbstractAssert;

public final class TestGraphAssertions extends AbstractAssert<TestGraphAssertions, TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable>> {

  private TestGraphAssertions(TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> vertices) {
    super(vertices, TestGraphAssertions.class);
  }

  public static TestGraphAssertions assertThat(TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph) {
    return new TestGraphAssertions(graph);
  }


  public TestGraphAssertions hasElement(long l, IgaElement igaElement) {
    return this;
  }

}
