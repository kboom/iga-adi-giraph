package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.DirectionComputation;
import edu.agh.iga.adi.giraph.direction.IgaAdiWorkerContext;
import edu.agh.iga.adi.giraph.direction.IgaMessageCombiner;
import edu.agh.iga.adi.giraph.direction.IgaOperation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import edu.agh.iga.adi.giraph.test.VertexFactory;
import org.apache.giraph.io.formats.InMemoryVertexOutputFormat;
import org.apache.giraph.utils.InMemoryVertexInputFormat;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.test.GraphFactory.graph;

class GraphIT {

  @Test
  void canRun() {
    GiraphTestJob job = giraphJob()
        .computationClazz(DirectionComputation.class)
        .messageCombinerClazz(IgaMessageCombiner.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(InMemoryVertexInputFormat.class)
        .vertexOutputFormatClazz(InMemoryVertexOutputFormat.class)
        .build();

    TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph = graph(job.getConfiguration());

    VertexFactory vertexFactory = new VertexFactory(job.getConfiguration());

    graph.addVertex(vertexFactory.makeVertex(1, new IgaOperation(), 2));

    InMemoryVertexInputFormat.setGraph(graph);

    job.run();
  }

}