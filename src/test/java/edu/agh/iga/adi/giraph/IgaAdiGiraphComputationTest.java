package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.io.StepInputFormat;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;

class IgaAdiGiraphComputationTest {

  @Test
  void canRun() {
    GiraphTestJob job = giraphJob()
        .computationClazz(IgaStepComputation.class)
        .messageCombinerClazz(IgaMessageCombiner.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(StepInputFormat.class)
        .build();

    job.run();
  }

}