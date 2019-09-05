package edu.agh.iga.adi.giraph.core.setup;

import edu.agh.iga.adi.giraph.core.IgaVertex;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.stream.LongStream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
class VertexDependencies {

  private static final int COEFFICIENT_SKEW = 2;

  private static final int[] NONE = {};
  private static final int[] INTERIM_FIRST = {0};
  private static final int[] INTERIM_LEFT = {0, 1};
  private static final int[] INTERIM_RIGHT = {1, 2};
  private static final int[] INTERIM_LAST = {2};
  private static final int[] INTERIM_ALL = {0, 1, 2};

  static LongStream verticesDependingOn(IgaVertex vertex) {
    if (vertex.isLeading()) {
      long firstLeaf = vertex.getTree().firstIndexOfLeafRow();
      // first vertex holds 5 meaningful rows
      return LongStream.of(firstLeaf, firstLeaf + 1, firstLeaf + 2, firstLeaf + 3, firstLeaf + 4);
    }
    if (vertex.isTrailing()) {
      long lastLeaf = vertex.getTree().lastIndexOfLeafRow();
      return LongStream.of(lastLeaf - 1, lastLeaf);
    }
    // other branch vertices rely on their children and their closest cousins
    return LongStream.rangeClosed(vertex.leftDescendantAt(1) - 1, vertex.rightDescendantAt(1) + 1);
  }

  static int[] coefficientsFor(IgaVertex src, long dst) {
    if (src.isLeading()) {
      return leadingCoefficients(src, dst);
    }
    if (src.isTrailing()) {
      return trailingCoefficients(src, dst);
    }
    return interimCoefficients(src, dst);
  }

  private static int[] leadingCoefficients(IgaVertex src, long dst) {
    val offsetLeft = dst - src.leftDescendantAt(1);
    if (offsetLeft == 0) {
      return new int[] {0, 1, 2};
    }
    if (offsetLeft == 1) {
      return new int[] {1, 2, 3};
    }
    if (offsetLeft == 2) {
      return new int[] {2, 3, 4};
    }
    if (offsetLeft == 3) {
      return new int[] {3, 4};
    }
    if (offsetLeft == 4) {
      return new int[] {4};
    }
    return NONE;
  }

  private static int[] trailingCoefficients(IgaVertex src, long dst) {
    val offsetRight = src.rightDescendantAt(1) - dst;
    if (offsetRight == 0) {
      return INTERIM_ALL;
    }
    if (offsetRight == 1) {
      return INTERIM_LEFT;
    }
    if (offsetRight == 2) {
      return INTERIM_LEFT;
    }
    return NONE;
  }

  private static int[] interimCoefficients(IgaVertex src, long dst) {
    long left = src.leftDescendantAt(1) + COEFFICIENT_SKEW;
    long right = src.rightDescendantAt(1) + COEFFICIENT_SKEW;
    if (dst < left) {
      return INTERIM_FIRST;
    }
    if (dst > right) {
      return INTERIM_LAST;
    }
    if (dst == left + 1) {
      return INTERIM_ALL;
    }
    if (dst == left) {
      return INTERIM_LEFT;
    }
    if (dst == right) {
      return INTERIM_RIGHT;
    }
    return NONE;
  }

}
