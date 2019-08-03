package edu.agh.iga.adi.giraph.core;

import static java.lang.String.format;

public class IgaVertex {

  private final long id;

  private IgaVertex(long id) {
    this.id = id;
  }

  static IgaVertex vertexOf(DirectionTree tree, long id) {
    if (id == 1) {
      return new RootVertex();
    }
    if (id < tree.firstIndexOfBranchingRow()) {
      return new InterimVertex(id);
    }
    if (id < tree.firstIndexOfLeafRow()) {
      return new BranchVertex(id);
    }
    if (id < tree.lastIndexOfLeafRow()) {
      return new LeafVertex(id);
    }
    throw new IllegalStateException(format("The problem tree does not have vertex %d", id));
  }

  public boolean is(Class<? extends IgaVertex> clazz) {
    return getClass().isAssignableFrom(clazz);
  }

  public boolean after(IgaVertex dst) {
    return id > dst.id;
  }

  public static class RootVertex extends IgaVertex {

    private RootVertex() {
      super(1);
    }

  }


  public static class InterimVertex extends IgaVertex {

    private InterimVertex(long id) {
      super(id);
    }

  }

  public static class BranchVertex extends IgaVertex {

    private BranchVertex(long id) {
      super(id);
    }

  }

  public static class LeafVertex extends IgaVertex {

    private LeafVertex(long id) {
      super(id);
    }

  }

}
