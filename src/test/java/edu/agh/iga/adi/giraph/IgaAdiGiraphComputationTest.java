package edu.agh.iga.adi.giraph;

import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;

class IgaAdiGiraphComputationTest {

  @Test
  void canRun() {
    giraphJob()
        .computationClazz(IgaAdiGiraphComputation.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(AdiTreeVertexFormat.class)
        .build();
  }

}