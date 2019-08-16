package edu.agh.iga.adi.giraph.test.assertion;

import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.matrix.store.TransformableRegion;

import static edu.agh.iga.adi.giraph.test.assertion.MatrixAssertions.assertValues;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformableRegionAssertions extends AbstractAssert<TransformableRegionAssertions, TransformableRegion<Double>> {

  private TransformableRegionAssertions(TransformableRegion<Double> store) {
    super(store, TransformableRegionAssertions.class);
  }

  public static TransformableRegionAssertions assertThatRegion(TransformableRegion<Double> store) {
    return new TransformableRegionAssertions(store);
  }

  public TransformableRegionAssertions isOfSize(long rows, long cols) {
    assertThat(actual.countColumns()).isEqualTo(cols);
    assertThat(actual.countRows()).isEqualTo(rows);
    return this;
  }

  public TransformableRegionAssertions hasElementsMatching(PrimitiveDenseStore ds) {
    assertValues(actual, ds, "Matrix ");
    return this;
  }

}
