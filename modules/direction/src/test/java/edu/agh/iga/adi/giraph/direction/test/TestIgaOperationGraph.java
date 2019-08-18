package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;

import static edu.agh.iga.adi.giraph.core.IgaOperationFactory.operationsFor;

public class TestIgaOperationGraph {

  public static IgaTestGraph igaTestGraph(IgaTestGraph igaTestGraph) {
    final DirectionTree tree = igaTestGraph.getDirectionTree();
    operationsFor(tree).forEachRemaining(o -> igaTestGraph.withVertex(o.getSrc().id(), o.getOperation(), o.getDst().id()));
    return igaTestGraph;
  }

}
