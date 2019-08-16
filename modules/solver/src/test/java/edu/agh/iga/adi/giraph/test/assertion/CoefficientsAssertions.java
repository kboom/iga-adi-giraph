package edu.agh.iga.adi.giraph.test.assertion;

import org.assertj.core.api.AbstractAssert;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

import static edu.agh.iga.adi.giraph.test.CoefficientsFromFileReader.coefficientsOfDir;
import static edu.agh.iga.adi.giraph.test.CoefficientsFromFileReader.coefficientsOfFile;
import static org.assertj.core.api.Assertions.assertThat;

public final class CoefficientsAssertions extends AbstractAssert<CoefficientsAssertions, Path> {

  private CoefficientsAssertions(Path path) {
    super(path, CoefficientsAssertions.class);
  }

  public static CoefficientsAssertions assertThatCoefficients(Path path) {
    return new CoefficientsAssertions(path);
  }

  public CoefficientsAssertions areEqualToResource(String resource) {
    try {
      return assertCoefficients(resource);
    } catch (IOException e) {
      throw new IllegalStateException("Could not read coefficients to compare from " + resource);
    }
  }

  private CoefficientsAssertions assertCoefficients(String resource) throws IOException {
    SortedMap<Long, PrimitiveDenseStore> actualCoefficients = coefficientsOfDir(actual);
    SortedMap<Long, PrimitiveDenseStore> expectedCoefficients = coefficientsOfFile(pathOfResource(resource));

    assertThat(actualCoefficients).containsExactlyEntriesOf(expectedCoefficients);
    return this;
  }

  private Path pathOfResource(String resourceName) {
    final URL resource = ClassLoader.getSystemClassLoader().getResource(resourceName);
    return Paths.get(resource.getPath());
  }

}
