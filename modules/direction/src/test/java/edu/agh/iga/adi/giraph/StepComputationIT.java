package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.IgaWorkerContext;
import edu.agh.iga.adi.giraph.direction.StepComputation;
import edu.agh.iga.adi.giraph.direction.io.StepVertexInputFormat;
import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import edu.agh.iga.adi.giraph.direction.test.GiraphTestJob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.loadProblem;
import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.problemLoaderConfig;
import static edu.agh.iga.adi.giraph.test.util.assertion.CoefficientsAssertions.assertThatCoefficients;

class StepComputationIT {

  @Test
  void canRun(
      @TempDir Path input,
      @TempDir Path output
  ) {
    loadProblem(
        problemLoaderConfig()
            .resource("StepComputationIT/one.mat")
            .shards(2)
            .targetPath(input)
            .build()
    );

    // given
    GiraphTestJob job = giraphJob()
        .computationClazz(StepComputation.class)
        .workerContextClazz(IgaWorkerContext.class)
        .vertexInputFormatClazz(StepVertexInputFormat.class)
        .vertexOutputFormatClazz(StepVertexOutputFormat.class)
        .coefficientsInputDir(input)
        .coefficientsOutputDir(output)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
          HEIGHT_PARTITIONS.set(conf, 2);
        })
        .build();

    // when
    job.run();

    // then
    assertThatCoefficients(output)
        .areEqualToResource("StepComputationIT/one.mat", ROWS_BOUND_TO_NODE);
  }

}