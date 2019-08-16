package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.DirectionComputation;
import edu.agh.iga.adi.giraph.direction.IgaAdiWorkerContext;
import edu.agh.iga.adi.giraph.direction.io.CoefficientMatricesOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.test.DirManager.aDirManager;
import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.test.assertion.CoefficientsAssertions.assertThatCoefficients;

class DirectionComputationIT {

  @Test
  void canRun(@TempDir Path coefficientsDir) {
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
    assertThatCoefficients(coefficientsDir)
        .areEqualToResource("src/test/resources/DirectionIT/one.mat");
  }

}