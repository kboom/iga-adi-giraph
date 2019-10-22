package edu.agh.iga.adi.giraph.core;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.IntStream.range;

public class IgaVertexFactory {

  private IgaVertexFactory() {
  }

  /**
   * Produces all vertices that {@link #childrenOf(IgaVertex, int)} produces including the parent.
   *
   * @param parent included in the results
   * @param height the height of the children included
   * @return vertices between the parent, left and right, including top, left and right
   */
  public static Iterator<IgaVertex> familyOf(IgaVertex parent, int height) {
    return Stream.concat(
        Stream.of(parent),
        iteratorToFiniteStream(childrenOf(parent, height))
    ).iterator();
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
    return range(1, height + 1)
        .flatMap(h -> range(parent.leftDescendantAt((int) h), parent.rightDescendantAt((int) h) + 1))
        .boxed()
        .map(id -> vertexOf(parent.getTree(), id))
        .iterator();
  }

  private static <T> Stream<T> iteratorToFiniteStream(final Iterator<T> iterator) {
    return StreamSupport.stream(spliteratorUnknownSize(iterator, 0), false);
  }

}
