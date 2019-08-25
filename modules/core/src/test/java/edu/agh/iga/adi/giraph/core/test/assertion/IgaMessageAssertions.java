package edu.agh.iga.adi.giraph.core.test.assertion;

import edu.agh.iga.adi.giraph.core.IgaMessage;
import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.structure.Access2D;

import java.util.function.Function;

import static edu.agh.iga.adi.giraph.test.util.assertion.MatrixAssertions.assertValues;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class IgaMessageAssertions<T extends IgaMessage> extends AbstractAssert<IgaMessageAssertions<T>, T> {

  private IgaMessageAssertions(T msg) {
    super(msg, IgaMessageAssertions.class);
  }

  public static <T extends IgaMessage> IgaMessageAssertions<T> assertThatMessage(T msg) {
    return new IgaMessageAssertions<>(msg);
  }

  public IgaMessageAssertions<T> hasSrc(long srcId) {
    assertThat(actual.getSrcId())
        .as(format("Should have source <%s> rather than <%s>", srcId, actual.getSrcId()))
        .isEqualTo(srcId);
    return this;
  }

  public IgaMessageAssertions<T> hasMatrix(Function<T, Access2D<Double>> extractor, PrimitiveDenseStore expectedValues) {
    assertValues(extractor.apply(actual), expectedValues, "Matrix");
    return this;
  }

}
