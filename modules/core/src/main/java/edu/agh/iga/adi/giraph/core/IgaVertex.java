package edu.agh.iga.adi.giraph.core;

import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import lombok.val;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static edu.agh.iga.adi.giraph.core.IgaVertex.ChildPosition.*;
import static java.lang.String.format;
import static java.math.RoundingMode.FLOOR;

public abstract class IgaVertex {

  private final DirectionTree tree;
  private int id;

  private IgaVertex(DirectionTree tree, int id) {
    this.tree = tree;
    this.id = id;
  }

  public IgaVertex reuseSameTypeFor(int id) {
    this.id = id;
    return this;
  }

  public static IgaVertex vertexOf(DirectionTree tree, int id) {
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

  private InterimVertex interimVertex(int id) {
    return new InterimVertex(tree, id);
  }

  private BranchVertex branchVertex(int id) {
    return new BranchVertex(tree, id);
  }

  private LeafVertex leafVertex(int id) {
    return new LeafVertex(tree, id);
  }

  public boolean is(Class<? extends IgaVertex> clazz) {
    return getClass().isAssignableFrom(clazz);
  }

  public boolean after(IgaVertex dst) {
    return id > dst.id;
  }

  public int id() {
    return id;
  }

  public DirectionTree getTree() {
    return tree;
  }

  public int rowIndexOf() {
    if (is(RootVertex.class)) {
      return 1;
    }
    if (is(LeafVertex.class)) {
      return tree.leafHeight();
    }
    if (is(BranchVertex.class)) {
      return tree.branchingHeight();
    }
    return LongMath.log2(id, FLOOR) + 1;
  }

  public boolean onTopOfBranchingRow() {
    return is(InterimVertex.class) && rowIndexOf() == tree.branchingHeight() - 1;
  }

  public int strengthOf() {
    return tree.strengthOfRow(rowIndexOf());
  }

  public ChildPosition childPosition() {
    if (is(LeafVertex.class)) {
      int position = (tree.strengthOfLeaves() + offsetLeft()) % 3;
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

  public int offsetLeft() {
    return id - tree.firstIndexOfRow(rowIndexOf());
  }

  public int offsetRight() {
    return tree.lastIndexOfRow(rowIndexOf()) - id;
  }

  public boolean isLeading() {
    return offsetLeft() == 0;
  }

  public boolean isTrailing() {
    return offsetRight() == 0;
  }

  public Optional<? extends IgaVertex> leftChildOf() {
    if (is(RootVertex.class)) {
      return Optional.of(interimVertex(2));
    }
    if (is(InterimVertex.class)) {
      return onTopOfBranchingRow() ? Optional.of(branchVertex(2 * id)) : Optional.of(interimVertex(2 * id));
    }
    if (is(BranchVertex.class)) {
      return Optional.of(leafVertex(tree.firstIndexOfLeafRow() + 3 * (id - tree.firstIndexOfBranchingRow())));
    }
    return Optional.empty();
  }

  public List<IgaVertex> children() {
    return leftChildOf().map(child -> {
      val offset = child.id();
      if (child.is(InterimVertex.class)) {
        return ImmutableList.<IgaVertex>of(interimVertex(offset), interimVertex(offset + 1));
      }
      if (child.is(LeafVertex.class)) {
        return ImmutableList.<IgaVertex>of(leafVertex(offset), leafVertex(offset + 1), leafVertex(offset + 2));
      }
      if (child.is(BranchVertex.class)) {
        return ImmutableList.<IgaVertex>of(branchVertex(offset), branchVertex(offset + 1));
      }
      if (child.is(RootVertex.class)) {
        return ImmutableList.<IgaVertex>of(interimVertex(2), interimVertex(3));
      }
      return null;
    }).orElse(ImmutableList.of());
  }

  public int getLeftSegment() {
    val share = tree.strengthOfLeaves() / strengthOf();
    return share * offsetLeft();
  }

  public int leftDescendantAt(int height) {
    final int currentHeight = rowIndexOf();
    final int branchingHeight = tree.branchingHeight();
    if (currentHeight + height <= branchingHeight) {
      return IntMath.pow(2, height) * id;
    } else {
      val parentId = IntMath.pow(2, height - 1) * id;
      val parentOffset = parentId - tree.firstIndexOfBranchingRow();
      return tree.firstIndexOfLeafRow() + 3 * parentOffset;
    }
  }

  public int rightDescendantAt(int height) {
    final int currentHeight = rowIndexOf();
    final int branchingHeight = tree.branchingHeight();
    if (currentHeight + height <= branchingHeight) {
      return IntMath.pow(2, height) * (id + 1) - 1;
    } else {
      val parentId = IntMath.pow(2, height - 1) * (id + 1) - 1;
      val parentOffset = parentId - tree.firstIndexOfBranchingRow();
      return tree.firstIndexOfLeafRow() + 3 * (parentOffset + 1) - 1;
    }
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

    private InterimVertex(DirectionTree tree, int id) {
      super(tree, id);
    }

  }

  public static class BranchVertex extends IgaVertex {

    private BranchVertex(DirectionTree tree, int id) {
      super(tree, id);
    }

  }

  public static class LeafVertex extends IgaVertex {

    private LeafVertex(DirectionTree tree, int id) {
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
