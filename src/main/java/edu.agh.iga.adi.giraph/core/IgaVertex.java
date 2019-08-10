package edu.agh.iga.adi.giraph.core;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.math.LongMath.log2;
import static edu.agh.iga.adi.giraph.core.IgaVertex.ChildPosition.*;
import static java.lang.String.format;
import static java.math.RoundingMode.FLOOR;

public class IgaVertex {

  private final long id;
  private final DirectionTree tree;

  private IgaVertex(DirectionTree tree, long id) {
    this.tree = tree;
    this.id = id;
  }

  public static IgaVertex vertexOf(DirectionTree tree, long id) {
    if (id == 1) {
      return new RootVertex(tree);
    }
    if (id < tree.firstIndexOfBranchingRow()) {
      return new InterimVertex(tree, id);
    }
    if (id < tree.firstIndexOfLeafRow()) {
      return new BranchVertex(tree, id);
    }
    if (id <= tree.lastIndexOfLeafRow()) {
      return new LeafVertex(tree, id);
    }
    throw new IllegalStateException(format("The problem tree does not have vertex %d", id));
  }

  private RootVertex rootVertex() {
    return new RootVertex(tree);
  }

  private InterimVertex interimVertex(long id) {
    return new InterimVertex(tree, id);
  }

  private BranchVertex branchVertex(long id) {
    return new BranchVertex(tree, id);
  }

  private LeafVertex leafVertex(long id) {
    return new LeafVertex(tree, id);
  }

  public boolean is(Class<? extends IgaVertex> clazz) {
    return getClass().isAssignableFrom(clazz);
  }

  public boolean after(IgaVertex dst) {
    return id > dst.id;
  }

  public long id() {
    return id;
  }

  public int rowIndexOf() {
    if(is(RootVertex.class)) {
      return 1;
    }
    if(is(LeafVertex.class)) {
      return tree.leafHeight();
    }
    if(is(BranchVertex.class)) {
      return tree.branchingHeight();
    }
    return log2(id, FLOOR) + 1;
  }

  public boolean onTopOfBranchingRow() {
      return is(InterimVertex.class) && rowIndexOf() == tree.branchingHeight() - 1;
  }

  public long strengthOf() {
    return tree.strengthOfRow(rowIndexOf());
  }

  public ChildPosition childPosition() {
    if(is(LeafVertex.class)) {
      int position = (int) (tree.strengthOfLeaves() + offsetLeft()) % 3;
      switch (position) {
        case 0:
          return LEFT;
        case 1:
          return MIDDLE;
        case 2:
          return RIGHT;
        default:
          throw new IllegalStateException("Should have three children");
      }
    }
    return id % 2 == 0 ? LEFT : RIGHT;
  }

  public long offsetLeft() {
    return id - tree.firstIndexOfRow(rowIndexOf());
  }

  public Optional<? extends IgaVertex> leftChildOf() {
    if(is(RootVertex.class)) {
      return Optional.of(interimVertex(2));
    }
    if(is(InterimVertex.class)) {
      return onTopOfBranchingRow() ? Optional.of(branchVertex(2 * id)) : Optional.of(interimVertex(2 * id));
    }
    if(is(BranchVertex.class)) {
      return Optional.of(leafVertex(tree.firstIndexOfLeafRow() + 3 * (id - tree.firstIndexOfBranchingRow())));
    }
    return Optional.empty();
  }

  public List<IgaVertex> children() {
    return leftChildOf().map(child -> {
      final long offset = child.id();
      if(child.is(InterimVertex.class)) {
        return ImmutableList.<IgaVertex>of(interimVertex(offset), interimVertex(offset + 1));
      }
      if(child.is(LeafVertex.class)) {
        return ImmutableList.<IgaVertex>of(leafVertex(offset), leafVertex(offset + 1), leafVertex(offset + 2));
      }
      if(child.is(BranchVertex.class)) {
        return ImmutableList.<IgaVertex>of(branchVertex(offset), branchVertex(offset + 1));
      }
      if(child.is(RootVertex.class)) {
        return ImmutableList.<IgaVertex>of(interimVertex(2), interimVertex(3));
      }
      return null;
    }).orElse(ImmutableList.of());
}

  public boolean inRegularArea() {
    return id < tree.firstIndexOfBranchingRow();
  }

  public Pair<Double, Double> segmentOf() {
    long share = tree.size() / strengthOf();
    double lb = share * offsetLeft();
    double ub = share * (offsetLeft() + 1);
    return Pair.of(lb, ub);
  }

  public enum ChildPosition {
    LEFT,
    MIDDLE,
    RIGHT
  }

  public static class RootVertex extends IgaVertex {

    private RootVertex(DirectionTree tree) {
      super(tree, 1);
    }

  }

  public static class InterimVertex extends IgaVertex {

    private InterimVertex(DirectionTree tree, long id) {
      super(tree, id);
    }

  }

  public static class BranchVertex extends IgaVertex {

    private BranchVertex(DirectionTree tree, long id) {
      super(tree, id);
    }

  }

  public static class LeafVertex extends IgaVertex {

    private LeafVertex(DirectionTree tree, long id) {
      super(tree, id);
    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IgaVertex igaVertex = (IgaVertex) o;
    return id == igaVertex.id &&
        tree.equals(igaVertex.tree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tree);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ":" + id;
  }

}
