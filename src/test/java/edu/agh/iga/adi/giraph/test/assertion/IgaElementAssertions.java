package edu.agh.iga.adi.giraph.test.assertion;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static edu.agh.iga.adi.giraph.test.assertion.MatrixAssertions.assertValues;

public class IgaElementAssertions extends AbstractAssert<IgaElementAssertions, IgaElement> {

  private IgaElementAssertions(IgaElement element) {
    super(element, IgaElementAssertions.class);
  }

  public static IgaElementAssertions assertThatElement(IgaElement element) {
    return new IgaElementAssertions(element);
  }

  public IgaElementAssertions hasMa(PrimitiveDenseStore ds) {
    assertValues(actual.ma, ds, "Matrix A");
    return this;
  }

  public IgaElementAssertions hasMb(PrimitiveDenseStore ds) {
    assertValues(actual.mb, ds, "Matrix B");
    return this;
  }

  public IgaElementAssertions hasMx(PrimitiveDenseStore ds) {
    assertValues(actual.mx, ds, "Matrix X");
    return this;
  }

}
