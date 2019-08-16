package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;

import java.util.List;

import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static java.util.Collections.singletonList;

public final class IgaTestGraph {

  private final TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph;
  private final DirectionTree directionTree;
  private final Mesh mesh;

  public IgaTestGraph(
      TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph,
      Mesh mesh,
      DirectionTree directionTree
  ) {
    this.graph = graph;
    this.mesh = mesh;
    this.directionTree = directionTree;
  }

  public DirectionTree getDirectionTree() {
    return directionTree;
  }

  public static IgaTestGraph igaTestGraphOn(
      TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph,
      Mesh mesh,
      DirectionTree directionTree
  ) {
    return new IgaTestGraph(graph, mesh, directionTree);
  }

  public IgaTestGraph withVertex(
      long srcId, IgaOperation operation, long dstId
  ) {
    graph.addVertex(withVertex(srcId, igaElement(srcId, mesh.getDofsX()), operation, dstId));
    return this;
  }

  public IgaTestGraph withVertexElement(IgaElement element) {
    graph.getVertex(new LongWritable(element.id)).setValue(new IgaElementWritable(element));
    return this;
  }

  public IgaTestGraph withVertexElements(Iterable<IgaElement> elements) {
    elements.forEach(this::withVertexElement);
    return this;
  }

  private Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> withVertex(
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
    return graph.getConf().createVertex();
  }

}
