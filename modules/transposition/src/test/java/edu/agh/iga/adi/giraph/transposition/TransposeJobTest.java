package edu.agh.iga.adi.giraph.transposition;

import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static edu.agh.iga.adi.giraph.commons.PathUtil.pathOfResource;
import static edu.agh.iga.adi.giraph.transposition.TransposeJob.transpose;
import static java.nio.file.Files.readAllLines;
import static org.assertj.core.api.Assertions.assertThat;

class TransposeJobTest {

  private static final Path SIMPLE_MAT_IN = new Path(pathOfResource("simple/coefficients.in").toUri());
  private static final Path SIMPLE_MAT_OUT = new Path(pathOfResource("simple/coefficients.out").toUri());

  @Test
  void canTransposeSimpleMatrix(@TempDir java.nio.file.Path tmpDir) throws IOException {
    java.nio.file.Path output = tmpDir.resolve("test");
    transpose(SIMPLE_MAT_IN, new Path(output.toUri()));

    java.nio.file.Path file = Files.list(output)
        .filter(f -> f.getFileName().toString().startsWith("part"))
        .findFirst()
        .get();

    List<String> expectedLines = readAllLines(Paths.get(SIMPLE_MAT_OUT.toUri()));
    assertThat(readAllLines(file)).containsExactlyElementsOf(expectedLines);
  }

}