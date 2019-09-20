package edu.agh.iga.adi.giraph.test.util.assertion;

import lombok.val;
import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

import static edu.agh.iga.adi.giraph.test.util.CoefficientsFromFileReader.coefficientsOfDir;
import static edu.agh.iga.adi.giraph.test.util.CoefficientsFromFileReader.coefficientsOfFileDefaultPrecision;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ojalgo.function.aggregator.Aggregator.AVERAGE;

public final class CoefficientsAssertions extends AbstractAssert<CoefficientsAssertions, Path> {

  private static final int STANDARD_PRECISION = 3;

  private CoefficientsAssertions(Path path) {
    super(path, CoefficientsAssertions.class);
  }

  public static CoefficientsAssertions assertThatCoefficients(Path path) {
    return new CoefficientsAssertions(path);
  }

  public CoefficientsAssertions areEqualToResource(String resource, int rows) {
    return assertCoefficients(resource, rows);
  }

  private CoefficientsAssertions assertCoefficients(String resource, int rows) {
    SortedMap<Long, PrimitiveDenseStore> actualCoefficients = coefficientsOfDir(actual, rows, STANDARD_PRECISION);
    SortedMap<Long, PrimitiveDenseStore> expectedCoefficients = coefficientsOfFileDefaultPrecision(pathOfResource(resource), rows);

    assertThat(actualCoefficients).containsExactlyEntriesOf(expectedCoefficients);
    return this;
  }

  public CoefficientsAssertions checksumEquals(double expectedChecksum, int rows) {
    SortedMap<Long, PrimitiveDenseStore> actualCoefficients = coefficientsOfDir(actual, rows);
    val actualChecksum = actualCoefficients
        .values()
        .stream()
        .mapToDouble(ds -> ds.aggregateAll(AVERAGE))
        .sum();
    assertThat(actualChecksum)
        .as("Checksums should match")
        .isEqualTo(expectedChecksum);
    return this;
  }

  private Path pathOfResource(String resourceName) {
    final URL resource = ClassLoader.getSystemClassLoader().getResource(resourceName);
    return Paths.get(resource.getPath());
  }
}
