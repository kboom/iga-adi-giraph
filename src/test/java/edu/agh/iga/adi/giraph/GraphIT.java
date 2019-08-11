package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.direction.DirectionComputation;
import edu.agh.iga.adi.giraph.direction.IgaAdiWorkerContext;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.test.GiraphTestJob;
import org.apache.giraph.io.formats.InMemoryVertexOutputFormat;
import org.apache.giraph.utils.InMemoryVertexInputFormat;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static edu.agh.iga.adi.giraph.test.GiraphTestJob.giraphJob;
import static edu.agh.iga.adi.giraph.test.GraphFactory.graph;
import static edu.agh.iga.adi.giraph.test.IgaTestGraph.igaTestGraphOn;
import static edu.agh.iga.adi.giraph.test.SmallProblem.DIRECTION_TREE;
import static edu.agh.iga.adi.giraph.test.SmallProblem.MESH;
import static edu.agh.iga.adi.giraph.test.TestIgaOperationGraph.igaTestGraph;

class GraphIT {

  @Test
  @Disabled
  void canRun() {
    GiraphTestJob job = giraphJob()
        .computationClazz(DirectionComputation.class)
        .workerContextClazz(IgaAdiWorkerContext.class)
        .vertexInputFormatClazz(InMemoryVertexInputFormat.class)
        .vertexOutputFormatClazz(InMemoryVertexOutputFormat.class)
        .build();

    TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph = graph(job.getConfiguration());

    igaTestGraph(igaTestGraphOn(graph, MESH, DIRECTION_TREE));

    InMemoryVertexInputFormat.setGraph(graph);

    job.run();
  }

}