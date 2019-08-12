package edu.agh.iga.adi.giraph.core;

import java.util.Iterator;

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
    return null;
  }

}
