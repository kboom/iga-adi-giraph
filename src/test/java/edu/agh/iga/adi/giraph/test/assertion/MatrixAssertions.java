package edu.agh.iga.adi.giraph.test.assertion;

import org.assertj.core.api.SoftAssertions;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.netio.BasicLogger;
import org.ojalgo.structure.Access2D;

import static org.assertj.core.api.Assertions.offset;

class MatrixAssertions {

  private MatrixAssertions() {
  }


  static void assertValues(Access2D<Double> mat, PrimitiveDenseStore ds, String prefix) {
    SoftAssertions softly = new SoftAssertions();
    ds.loopAll((row, col) -> {
      double expected = ds.get(row, col);
      double actualValue = mat.get(row, col);

      softly.assertThat(actualValue)
          .as(prefix + " should contain value %s at (%s, %s)", expected, row, col)
          .isEqualTo(expected);
    });
    if (!softly.errorsCollected().isEmpty()) {
      BasicLogger.debug("expected=", ds);
      BasicLogger.debug("actual=", mat);
    }
    softly.assertAll();
  }

  static void assertValuesAbout(Access2D<Double> mat, PrimitiveDenseStore ds, String prefix) {
    SoftAssertions softly = new SoftAssertions();
    ds.loopAll((row, col) -> {
      double expected = ds.get(row, col);
      double actualValue = mat.get(row, col);

      softly.assertThat(actualValue)
          .as(prefix + " should contain value %s at (%s, %s)", expected, row, col)
          .isCloseTo(expected, offset(0.00001));
    });
    if (!softly.errorsCollected().isEmpty()) {
      BasicLogger.debug("expected=", ds);
      BasicLogger.debug("actual=", mat);
    }
    softly.assertAll();
  }

}
