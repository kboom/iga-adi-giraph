package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.problem.ProblemType.HEAT;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.SURFACE_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.InitialProblemType.RADIAL;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;

class HeatTransferComputationIntTest {

  @Test
  @SneakyThrows
  void canRunHeatTransfer(@TempDir Path dir) {
    val outputDir = dir.resolve("output");

    // given
    GiraphTestJob job = giraphJob()
        .coefficientsOutputDir(outputDir)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 24);
          HEIGHT_PARTITIONS.set(conf, 2);
          STEP_COUNT.set(conf, 2);
          STEP_DELTA.set(conf, 0.1f);
          INITIAL_PROBLEM_TYPE.set(conf, RADIAL);
          PROBLEM_TYPE.set(conf, HEAT);
          FIRST_INITIALISATION_TYPE.set(conf, SURFACE_PROBLEM.getType());
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(outputDir.resolve("step-0"))
        .checksumEquals(460.79993271450707, ROWS_BOUND_TO_NODE);
    assertThatCoefficients(outputDir.resolve("step-1"))
        .checksumEquals(460.800138987652, ROWS_BOUND_TO_NODE);
  }

}
