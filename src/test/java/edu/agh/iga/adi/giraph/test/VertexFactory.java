package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaOperation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import java.util.List;

import static java.util.Collections.singletonList;

public final class VertexFactory {

  private final ImmutableClassesGiraphConfiguration configuration;

  public VertexFactory(GiraphConfiguration configuration) {
    this.configuration = new ImmutableClassesGiraphConfiguration(configuration);
  }

  public Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> makeVertex(
      long srcId, IgaOperation operation, long dstId
  ) {
    return makeVertex(srcId, new IgaElement(), operation, dstId);
  }

  public Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> makeVertex(
      long srcId, IgaElement srcElement, IgaOperation operation, long dstId
  ) {
    Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex = createVertex();

    List<Edge<LongWritable, IgaOperationWritable>> edgesList = singletonList(
        EdgeFactory.create(new LongWritable(dstId), new IgaOperationWritable(operation))
    );

    vertex.initialize(
        new LongWritable(srcId),
        new IgaElementWritable(srcElement),
        edgesList
    );

    return vertex;
  }

  private Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> createVertex() {
    return configuration.createVertex();
  }

}
