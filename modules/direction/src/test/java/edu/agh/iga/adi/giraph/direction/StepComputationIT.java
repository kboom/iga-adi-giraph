package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.hadoop.yarn.server.MiniYARNCluster;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.test.EnvironmentVariables.withEnvironmentVariables;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.loadProblem;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.problemLoaderConfig;
import static edu.agh.iga.adi.giraph.direction.test.YarnTestClusterFactory.localYarnCluster;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;
import static java.lang.System.getProperty;
import static java.nio.file.Files.createDirectory;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

class StepComputationIT {

  private static final String IDENTITY_MAT = "StepComputationIT/identity.mat";

  private static final MiniYARNCluster YARN_CLUSTER = localYarnCluster();

  @AfterEach
  @SneakyThrows
  void stop() {
    YARN_CLUSTER.stop();
  }

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
          INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    YARN_CLUSTER.init(job.getConfig());
    YARN_CLUSTER.start();

    // when
    withEnvironmentVariables()
        .set("CLASSPATH", resolveClasspath())
        .runWithVariables(job::run);

    // then
    assertThatCoefficients(outputDir)
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
  }

  private String resolveClasspath() {
    return Stream.of(getProperty("java.class.path").split(":"))
//        .map(t -> substringAfterLast(t, "/"))
//        .filter(t -> t.contains("giraph"))
        .filter(t -> t.endsWith(".jar"))
        .filter(t -> !t.contains("IntelliJ")) // maybe create full e2e test run on a fat jar instead?
        .map(t -> substringBeforeLast(t, "/"))
        .collect(joining(":"));
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
          INITIALISATION_TYPE.set(conf, COEFFICIENTS_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir)
        .areEqualToResource(IDENTITY_MAT, ROWS_BOUND_TO_NODE);
  }

}