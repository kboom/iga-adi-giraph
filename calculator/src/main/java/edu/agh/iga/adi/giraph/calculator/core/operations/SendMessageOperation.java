package edu.agh.iga.adi.giraph.calculator.core.operations;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import edu.agh.iga.adi.giraph.calculator.core.system.IdMemoryHandle;
import edu.agh.iga.adi.giraph.calculator.core.system.MemoryHandle;
import lombok.val;

import java.util.function.Function;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.sum;
import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.totalHeight;
import static edu.agh.iga.adi.giraph.calculator.core.TypesMemory.DOUBLE_MEMORY;

public enum SendMessageOperation implements MemoryHandle {
  MERGE_AND_ELIMINATE_LEAVES_MESSAGE(e -> sum(DOUBLE_MEMORY.times(3 * 3), DOUBLE_MEMORY.times(3 * e))),
  MERGE_AND_ELIMINATE_BRANCH_MESSAGE(e -> sum(DOUBLE_MEMORY.times(5 * 5), DOUBLE_MEMORY.times(5 * e))),
  MERGE_AND_ELIMINATE_INTERIM_MESSAGE(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e))),
  MERGE_AND_ELIMINATE_ROOT_MESSAGE(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  BACKWARDS_SUBSTITUTE_ROOT_MESSAGE(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE(e -> sum(DOUBLE_MEMORY.times(5 * 5), DOUBLE_MEMORY.times(5 * e), DOUBLE_MEMORY.times(5 * e))),
  TRANSPOSE_AND_INITIALISE_MESSAGE(e -> sum(DOUBLE_MEMORY.times(3 * 3), DOUBLE_MEMORY.times(3 * e)).times(3));

  Function<Integer, Memory> mapper;

  SendMessageOperation(Function<Integer, Memory> mapper) {
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
