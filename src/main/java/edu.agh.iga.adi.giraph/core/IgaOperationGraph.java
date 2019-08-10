package edu.agh.iga.adi.giraph.core;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.OperationFactory.operationFor;
import static java.util.stream.Collectors.collectingAndThen;

public class IgaOperationGraph {

  public static Set<DirectedOperation> directionGraph(DirectionTree tree) {
    return LongStream.range(1L, tree.lastIndexOfLeafRow() + 1)
        .boxed()
        .map(i -> vertexOf(tree, i))
        .flatMap(va -> va.children().stream().flatMap(vb -> Stream.of(
            operationFor(va, vb).map(o -> new DirectedOperation(va, vb, o)),
            operationFor(vb, va).map(o -> new DirectedOperation(vb, va, o))
        )))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
  }

  public static class DirectedOperation {
    private final IgaVertex src;
    private final IgaVertex dst;
    private final IgaOperation operation;

    private DirectedOperation(IgaVertex src, IgaVertex dst, IgaOperation operation) {
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
