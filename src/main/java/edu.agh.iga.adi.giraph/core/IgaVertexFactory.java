package edu.agh.iga.adi.giraph.core;

import java.util.Iterator;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static java.util.stream.LongStream.range;

public class IgaVertexFactory {

  private IgaVertexFactory() {
  }

  /**
   * Produces all vertices that are between the boundary set by the passed vertices,
   * including those vertices but excluding the root.
   *
   * @param parent not included in the results
   * @param height the height of the children included
   * @return vertices between the parent, left and right, including left and right
   */
  public static Iterator<IgaVertex> childrenOf(IgaVertex parent, int height) {
    final int parentHeight = parent.heightOf();
    return range(parentHeight, parentHeight + height)
        .flatMap(h -> range(parent.leftDescendantAt((int) h), parent.rightDescendantAt((int) h)))
        .boxed()
        .map(id -> vertexOf(parent.getTree(), id))
        .iterator();
  }

}
