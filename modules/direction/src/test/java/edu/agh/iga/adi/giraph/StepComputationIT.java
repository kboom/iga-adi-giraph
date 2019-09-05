package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.IgaWorkerContext;
import edu.agh.iga.adi.giraph.direction.StepComputation;
import edu.agh.iga.adi.giraph.direction.io.StepVertexInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.loadProblem;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.problemLoaderConfig;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;
import static java.nio.file.Files.createDirectory;

class StepComputationIT {

  @Test
  @SneakyThrows
  void canRun(@TempDir Path dir) {
    val inputDir = dir.resolve("input");
    val outputDir = dir.resolve("output");

    createDirectory(inputDir);

    loadProblem(
        problemLoaderConfig()
            .resource("StepComputationIT/one.mat")
            .shards(2)
            .targetPath(inputDir)
            .build()
    );

    // given
    GiraphTestJob job = giraphJob()
        .computationClazz(StepComputation.class)
        .workerContextClazz(IgaWorkerContext.class)
        .vertexInputFormatClazz(StepVertexInputFormat.class)
        .vertexOutputFormatClazz(StepVertexOutputFormat.class)
        .coefficientsInputDir(inputDir)
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
          HEIGHT_PARTITIONS.set(conf, 2);
          INITIALISATION_TYPE.set(conf, COEFFICIENTS_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir)
        .areEqualToResource("StepComputationIT/one.mat", ROWS_BOUND_TO_NODE);
  }

}