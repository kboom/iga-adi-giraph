package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.DirectionComputation;
import edu.agh.iga.adi.giraph.direction.IgaAdiWorkerContext;
import edu.agh.iga.adi.giraph.direction.io.CoefficientMatricesOutputFormat;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;

class GraphIT {

  @Test
  void canRun() {
    GiraphTestJob job = giraphJob()
        .computationClazz(DirectionComputation.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(InMemoryStepInputFormat.class)
        .vertexOutputFormatClazz(CoefficientMatricesOutputFormat.class)
        .configuration(conf -> {
          PROBLEM_SIZE.set(conf, 12);
          HEIGHT_PARTITIONS.set(conf, 2);
        })
        .build();

    job.run();
  }

}