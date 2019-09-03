package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.StepComputation;
import edu.agh.iga.adi.giraph.direction.IgaWorkerContext;
import edu.agh.iga.adi.giraph.direction.io.CoefficientMatricesOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.test.DirManager.aDirManager;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;

class StepComputationIT {

  @Test
  void canRun(@TempDir Path coefficientsDir) {
    // given
    GiraphTestJob job = giraphJob()
        .computationClazz(StepComputation.class)
        .workerContextClazz(IgaWorkerContext.class)
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
        .areEqualToResource("DirectionIT/one.mat", ROWS_BOUND_TO_NODE);
  }

}