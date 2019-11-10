package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.InitialProblemType.*;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.loadProblem;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.problemLoaderConfig;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;
import static java.nio.file.Files.createDirectory;

class StepComputationIntTest {

  private static final String IDENTITY_MAT = "StepComputationIT/identity.mat";
  private static final String PROJECTION_SUM_S0_MAT = "StepComputationIT/projection_sum.step-0.mat";
  private static final String PROJECTION_SUM_S1_MAT = "StepComputationIT/projection_sum.step-1.mat";

  private static final String PROJECTION_X_S0_MAT = "StepComputationIT/projection_x.step-0.mat";
  private static final String PROJECTION_X_S1_MAT = "StepComputationIT/projection_x.step-1.mat";

  private static final String PROJECTION_Y_S0_MAT = "StepComputationIT/projection_y.step-0.mat";
  private static final String PROJECTION_Y_S1_MAT = "StepComputationIT/projection_y.step-1.mat";

  @Test
  @SneakyThrows
  void canRunProjectionProblem(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
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
          FIRST_INITIALISATION_TYPE.set(conf, COEFFICIENTS_PROBLEM.getType());
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
  void canRunTwoIterationsOfProjection(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          STEP_COUNT.set(conf, 2);
          PROBLEM_SIZE.set(conf, 12);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .as("First step coefficients match")
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-1"))
        .as("Second step coefficients match")
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
  }

  @Test
  @SneakyThrows
  void canRunTwoIterationsOfLinearXProjection(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          STEP_COUNT.set(conf, 2);
          INITIAL_PROBLEM_TYPE.set(conf, LINEAR_X);
          PROBLEM_SIZE.set(conf, 12);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .as("First step coefficients match")
        .areEqualToResource(PROJECTION_X_S0_MAT, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-1"))
        .as("Second step coefficients match")
        .areEqualToResource(PROJECTION_X_S1_MAT, ROWS_BOUND_TO_NODE);
  }

  @Test
  @SneakyThrows
  void canRunTwoIterationsOfLinearYProjection(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          STEP_COUNT.set(conf, 2);
          INITIAL_PROBLEM_TYPE.set(conf, LINEAR_Y);
          PROBLEM_SIZE.set(conf, 12);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .as("First step coefficients match")
        .areEqualToResource(PROJECTION_Y_S0_MAT, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-1"))
        .as("Second step coefficients match")
        .areEqualToResource(PROJECTION_Y_S1_MAT, ROWS_BOUND_TO_NODE);
  }

  @Test
  @SneakyThrows
  void canRunTwoIterationsOfSumProjection(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          STEP_COUNT.set(conf, 2);
          INITIAL_PROBLEM_TYPE.set(conf, LINEAR_SUM);
          PROBLEM_SIZE.set(conf, 12);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .as("First step coefficients match")
        .areEqualToResource(PROJECTION_SUM_S0_MAT, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-1"))
        .as("Second step coefficients match")
        .areEqualToResource(PROJECTION_SUM_S1_MAT, ROWS_BOUND_TO_NODE);
  }

  @Test
  @SneakyThrows
  void canRunThreeIterationsOfProjection(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    val job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          STEP_COUNT.set(conf, 3);
          PROBLEM_SIZE.set(conf, 12);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .as("First step coefficients match")
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-1"))
        .as("Second step coefficients match")
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-2"))
        .as("Third step coefficients match")
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
  }

}