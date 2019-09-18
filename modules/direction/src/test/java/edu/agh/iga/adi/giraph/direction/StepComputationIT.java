package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.loadProblem;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.problemLoaderConfig;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;
import static java.nio.file.Files.createDirectory;

class StepComputationIT {

  private static final String IDENTITY_MAT = "StepComputationIT/identity.mat";

  @Test
  @SneakyThrows
  void canRunProjectionProblem(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
          HEIGHT_PARTITIONS.set(conf, 2);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
  }

  @Test
  @SneakyThrows
  void canRunCoefficientsProblem(@TempDir Path dir) {
    val inputDir = dir.resolve("input");
    val outputDir = dir.resolve("output");

    createDirectory(inputDir);

    loadProblem(
        problemLoaderConfig()
            .resource("StepComputationIT/identity.mat")
            .shards(2)
            .targetPath(inputDir)
            .build()
    );

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsInputDir(inputDir)
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
          HEIGHT_PARTITIONS.set(conf, 2);
          FIRST_INITIALISATION_TYPE.set(conf, COEFFICIENTS_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
  }

}