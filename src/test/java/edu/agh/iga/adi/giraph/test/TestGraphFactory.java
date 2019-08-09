package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.IgaVertex;

import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.OperationFactory.operationFor;

public class TestGraphFactory {

  public static IgaTestGraph directionGraph(IgaTestGraph igaTestGraph) {
    final DirectionTree tree = igaTestGraph.getDirectionTree();
    LongStream.range(1L, tree.lastIndexOfLeafRow() + 1)
        .boxed()
        .map(i -> vertexOf(tree, i))
        .flatMap(va -> va.children().stream().flatMap(vb -> Stream.of(
            operationFor(va, vb).map(o -> new DirectedOperation(va, vb, o)),
            operationFor(vb, va).map(o -> new DirectedOperation(vb, va, o))
        )))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(o -> igaTestGraph.withVertex(o.src.id(), o.operation, o.dst.id()));
    return igaTestGraph;
  }

  private static class DirectedOperation {
    private final IgaVertex src;
    private final IgaVertex dst;
    private final IgaOperation operation;

    private DirectedOperation(IgaVertex src, IgaVertex dst, IgaOperation operation) {
      this.src = src;
      this.dst = dst;
      this.operation = operation;
    }
  }

}
