package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.DirectionComputation;
import edu.agh.iga.adi.giraph.direction.IgaAdiWorkerContext;
import edu.agh.iga.adi.giraph.direction.io.CoefficientMatricesOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

import static edu.agh.iga.adi.giraph.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.test.CoefficientsFromFileReader.coefficientsOfDir;
import static edu.agh.iga.adi.giraph.test.CoefficientsFromFileReader.coefficientsOfFile;
import static edu.agh.iga.adi.giraph.test.DirManager.aDirManager;
import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionComputationIT {

  @Test
  void canRun(@TempDir Path coefficientsDir) throws IOException {
    // given
    GiraphTestJob job = giraphJob()
        .computationClazz(DirectionComputation.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(InMemoryStepInputFormat.class)
        .vertexOutputFormatClazz(CoefficientMatricesOutputFormat.class)
        .dirManager(conf ->
            aDirManager(conf)
                .withCoefficientsOutputDir(coefficientsDir.toString())
                .build()
        )
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
          HEIGHT_PARTITIONS.set(conf, 2);
        })
        .build();

    // when
    job.run();

    // then
    SortedMap<Long, PrimitiveDenseStore> actualCoefficients = coefficientsOfDir(coefficientsDir);
    SortedMap<Long, PrimitiveDenseStore> expectedCoefficients = coefficientsOfFile(pathOfResource("DirectionIT/one.mat"));

    assertThat(actualCoefficients).containsExactlyEntriesOf(expectedCoefficients);
  }

  private Path pathOfResource(String resourceName) {
    final URL resource = ClassLoader.getSystemClassLoader().getResource(resourceName);
    return Paths.get(resource.getPath());
  }

}