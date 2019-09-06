package edu.agh.iga.adi.giraph.test.util.assertion;

import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

import static edu.agh.iga.adi.giraph.test.util.CoefficientsFromFileReader.coefficientsOfDir;
import static edu.agh.iga.adi.giraph.test.util.CoefficientsFromFileReader.coefficientsOfFile;
import static org.assertj.core.api.Assertions.assertThat;

public final class CoefficientsAssertions extends AbstractAssert<CoefficientsAssertions, Path> {

  private static final int STANDARD_PRECISION = 3;

  private CoefficientsAssertions(Path path) {
    super(path, CoefficientsAssertions.class);
  }

  public static CoefficientsAssertions assertThatCoefficients(Path path) {
    return new CoefficientsAssertions(path);
  }

  public CoefficientsAssertions areEqualToResource(String resource, int rows) {
    try {
      return assertCoefficients(resource, rows);
    } catch (IOException e) {
      throw new IllegalStateException("Could not read coefficients to compare from " + resource);
    }
  }

  private CoefficientsAssertions assertCoefficients(String resource, int rows) throws IOException {
    SortedMap<Long, PrimitiveDenseStore> actualCoefficients = coefficientsOfDir(actual, rows, STANDARD_PRECISION);
    SortedMap<Long, PrimitiveDenseStore> expectedCoefficients = coefficientsOfFile(pathOfResource(resource), rows);

    assertThat(actualCoefficients).containsExactlyEntriesOf(expectedCoefficients);
    return this;
  }

  private Path pathOfResource(String resourceName) {
    final URL resource = ClassLoader.getSystemClassLoader().getResource(resourceName);
    return Paths.get(resource.getPath());
  }

}
