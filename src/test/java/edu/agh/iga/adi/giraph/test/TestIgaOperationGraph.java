package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;

import static edu.agh.iga.adi.giraph.core.IgaOperationGraph.directionGraph;

public class TestIgaOperationGraph {

  public static IgaTestGraph igaTestGraph(IgaTestGraph igaTestGraph) {
    final DirectionTree tree = igaTestGraph.getDirectionTree();
    directionGraph(tree).forEach(o -> igaTestGraph.withVertex(o.getSrc().id(), o.getOperation(), o.getDst().id()));
    return igaTestGraph;
  }

}
