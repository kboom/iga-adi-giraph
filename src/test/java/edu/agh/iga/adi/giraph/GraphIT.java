package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.DirectionComputation;
import edu.agh.iga.adi.giraph.direction.IgaAdiWorkerContext;
import edu.agh.iga.adi.giraph.direction.io.InMemoryStepInputFormat;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import org.apache.giraph.io.formats.InMemoryVertexOutputFormat;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;

class GraphIT {

  @Test
  void canRun() {
    GiraphTestJob job = giraphJob()
        .computationClazz(DirectionComputation.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(InMemoryStepInputFormat.class)
        .vertexOutputFormatClazz(InMemoryVertexOutputFormat.class)
        .build();

    job.run();
  }

}