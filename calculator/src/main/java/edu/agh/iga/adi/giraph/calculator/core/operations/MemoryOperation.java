package edu.agh.iga.adi.giraph.calculator.core.operations;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import edu.agh.iga.adi.giraph.calculator.core.system.MemoryHandle;
import lombok.val;

import java.util.function.Function;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.sum;
import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.totalHeight;
import static edu.agh.iga.adi.giraph.calculator.core.TypesMemory.DOUBLE_MEMORY;

public enum MemoryOperation implements MemoryHandle {
  MERGE_AND_ELIMINATE_LEAVES(e -> sum(DOUBLE_MEMORY.times(3 * 3), DOUBLE_MEMORY.times(3 * e))),
  MERGE_AND_ELIMINATE_BRANCH(e -> sum(DOUBLE_MEMORY.times(5 * 5), DOUBLE_MEMORY.times(5 * e))),
  MERGE_AND_ELIMINATE_INTERIM(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e))),
  MERGE_AND_ELIMINATE_ROOT(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  BACKWARDS_SUBSTITUTE_ROOT(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  BACKWARDS_SUBSTITUTE_INTERIM(e -> sum(DOUBLE_MEMORY.times(6 * 6), DOUBLE_MEMORY.times(6 * e), DOUBLE_MEMORY.times(6 * e))),
  BACKWARDS_SUBSTITUTE_BRANCH(e -> sum(DOUBLE_MEMORY.times(5 * 5), DOUBLE_MEMORY.times(5 * e), DOUBLE_MEMORY.times(5 * e))),
  TRANSPOSE_AND_INITIALISE(e -> sum(DOUBLE_MEMORY.times(3 * 3), DOUBLE_MEMORY.times(3 * e)).times(3));

  Function<Integer, Memory> mapper;

  MemoryOperation(Function<Integer, Memory> mapper) {
    this.mapper = mapper;
  }

  public Memory totalMemory(int elements, int level) {
    return mapper.apply(elements).times(elementsAtLevel(elements, level));
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
