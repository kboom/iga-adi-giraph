package edu.agh.iga.adi.giraph.calculator.core.operations;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import edu.agh.iga.adi.giraph.calculator.core.system.IdMemoryHandle;
import edu.agh.iga.adi.giraph.calculator.core.system.MemoryHandle;
import lombok.val;

import java.util.function.Function;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.sum;
import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.totalHeight;
import static edu.agh.iga.adi.giraph.calculator.core.TypesMemory.DOUBLE_MEMORY;

public enum CreateVertexOperation implements MemoryHandle {
  LEAVES_FOR_MERGING(e -> sum(DOUBLE_MEMORY.times(3 * 3), DOUBLE_MEMORY.times(3 * e))),
  BRANCHES_FOR_MERGING(e -> sum(DOUBLE_MEMORY.times(5 * 5), DOUBLE_MEMORY.times(5 * e))),
  INTERIM_FOR_MERGING(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e))),
  ROOT(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  INTERIM_FOR_BACKWARDS_SUBSTITUTION(e -> DOUBLE_MEMORY.times(5 * e)),
  BRANCH_FOR_BACKWARDS_SUBSTITUTION(e -> DOUBLE_MEMORY.times(6 * e));

  Function<Integer, Memory> mapper;

  CreateVertexOperation(Function<Integer, Memory> mapper) {
    this.mapper = mapper;
  }

  public Memory totalMemory(int elements, int level) {
    return mapper.apply(elements).times(elementsAtLevel(elements, level));
  }

  public MemoryHandle handle(int code) {
    return new IdMemoryHandle(toString(), code);
  }

  private int elementsAtLevel(int elements, int level) {
    val totalHeight = totalHeight(elements);
    if (level == totalHeight) {
      return elements;
    }
    if (level == totalHeight - 1) {
      return elements / 3;
    }
    return elements / (3 * (totalHeight - level));
  }

}
