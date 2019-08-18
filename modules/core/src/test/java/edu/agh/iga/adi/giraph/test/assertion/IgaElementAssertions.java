package edu.agh.iga.adi.giraph.test.assertion;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.test.util.assertion.MatrixAssertions;
import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

public class IgaElementAssertions extends AbstractAssert<IgaElementAssertions, IgaElement> {

  private IgaElementAssertions(IgaElement element) {
    super(element, IgaElementAssertions.class);
  }

  public static IgaElementAssertions assertThatElement(IgaElement element) {
    return new IgaElementAssertions(element);
  }

  public IgaElementAssertions hasMa(PrimitiveDenseStore ds) {
    MatrixAssertions.assertValues(actual.ma, ds, "Matrix A");
    return this;
  }

  public IgaElementAssertions hasMaAbout(PrimitiveDenseStore ds) {
    MatrixAssertions.assertValuesAbout(actual.ma, ds, "Matrix A");
    return this;
  }

  public IgaElementAssertions hasMb(PrimitiveDenseStore ds) {
    MatrixAssertions.assertValues(actual.mb, ds, "Matrix B");
    return this;
  }

  public IgaElementAssertions hasMbAbout(PrimitiveDenseStore ds) {
    MatrixAssertions.assertValuesAbout(actual.mb, ds, "Matrix B");
    return this;
  }

  public IgaElementAssertions hasMx(PrimitiveDenseStore ds) {
    MatrixAssertions.assertValues(actual.mx, ds, "Matrix X");
    return this;
  }

}
