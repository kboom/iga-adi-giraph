package edu.agh.iga.adi.giraph.core;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.OperationFactory.operationFor;
import static java.util.stream.LongStream.*;

public class IgaOperationFactory {

  public static Iterator<DirectedOperation> operationsFor(DirectionTree tree) {
    return operationsFor(tree, vertexOf(tree, 1L), tree.height());
  }

  public static Iterator<DirectedOperation> operationsFor(DirectionTree tree, IgaVertex parent, int height) {
    return concat(of(parent.id()), childrenOf(parent, height))
        .boxed()
        .map(i -> vertexOf(tree, i))
        .flatMap(va -> va.children().stream().flatMap(vb -> Stream.of(
            operationFor(va, vb).map(o -> new DirectedOperation(va, vb, o)),
            operationFor(vb, va).map(o -> new DirectedOperation(vb, va, o))
        )))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .iterator();
  }

  private static LongStream childrenOf(IgaVertex parent, int height) {
    return range(1, height)
        .flatMap(level -> range(
            parent.leftDescendantAt((int) level),
            parent.rightDescendantAt((int) level) + 1)
        );
  }

  public static class DirectedOperation {
    private final IgaVertex src;
    private final IgaVertex dst;
    private final IgaOperation operation;

    public DirectedOperation(IgaVertex src, IgaVertex dst, IgaOperation operation) {
      this.src = src;
      this.dst = dst;
      this.operation = operation;
    }

    public IgaVertex getSrc() {
      return src;
    }

    public IgaVertex getDst() {
      return dst;
    }

    public IgaOperation getOperation() {
      return operation;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      DirectedOperation that = (DirectedOperation) o;
      return src.equals(that.src) &&
          dst.equals(that.dst) &&
          operation.equals(that.operation);
    }

    @Override
    public int hashCode() {
      return Objects.hash(src, dst, operation);
    }

    @Override
    public String toString() {
      return String.format("%s - %s - %s", src, operation, dst);
    }
  }

}
