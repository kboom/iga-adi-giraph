package edu.agh.iga.adi.giraph.calculator.core;

import edu.agh.iga.adi.giraph.calculator.core.system.*;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;

import java.util.function.BiFunction;

import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.interimHeight;
import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.totalHeight;
import static edu.agh.iga.adi.giraph.calculator.core.Solver.SystemMemoryManager.manage;
import static edu.agh.iga.adi.giraph.calculator.core.operations.CreateVertexOperation.LEAF_MERGING;
import static edu.agh.iga.adi.giraph.calculator.core.operations.SendMessageOperation.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class Solver {

  public static List<SystemMemoryEvent> solverEvents(Problem problem, SystemMemory systemMemory) {
    return manage(systemMemory, problem)
        .apply(Solver::createLeaves)
        .apply(Solver::mergeAndEliminateLeaves)
        .apply(Solver::deleteLeaves)
        .apply(Solver::mergeAndEliminateBranches)
        .applyRepeated(interimHeight(problem), Solver::mergeAndEliminateInterim)
        .apply(Solver::mergeAndEliminateRoot)
        .apply(Solver::backwardsSubstituteRoot)
        .applyRepeated(interimHeight(problem), Solver::backwardsSubstituteInterim)
        .apply(Solver::backwardsSubstituteBranch)
        .apply(Solver::transposeAndInitialize)
        .getEvents();
  }

  private static SystemMemoryEvent createLeaves(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        LEAF_MERGING.totalMemory(problem.getSize(), totalHeight(problem)),
        LEAF_MERGING
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent deleteLeaves(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, LEAF_MERGING);
  }

  private static SystemMemoryAllocated mergeAndEliminateLeaves(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_LEAVES.totalMemory(problem.getSize(), totalHeight(problem)),
        MERGE_AND_ELIMINATE_LEAVES
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated mergeAndEliminateBranches(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_BRANCH.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        MERGE_AND_ELIMINATE_BRANCH
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated mergeAndEliminateInterim(Problem problem, SystemMemory systemMemory, int step) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_INTERIM.totalMemory(problem.getSize(), totalHeight(problem) - 1 - step),
        MERGE_AND_ELIMINATE_INTERIM
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated mergeAndEliminateRoot(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_ROOT.totalMemory(problem.getSize(), 1),
        MERGE_AND_ELIMINATE_ROOT
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated backwardsSubstituteRoot(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_ROOT.totalMemory(problem.getSize(), 1),
        BACKWARDS_SUBSTITUTE_ROOT
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated backwardsSubstituteInterim(Problem problem, SystemMemory systemMemory, int step) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_INTERIM.totalMemory(problem.getSize(), 1 + step),
        BACKWARDS_SUBSTITUTE_INTERIM
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated backwardsSubstituteBranch(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_BRANCH.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        BACKWARDS_SUBSTITUTE_BRANCH
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated transposeAndInitialize(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        TRANSPOSE_AND_INITIALISE.totalMemory(problem.getSize(), totalHeight(problem)),
        TRANSPOSE_AND_INITIALISE
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryFreed free(SystemMemory systemMemory, MemoryHandle handle) {
    return systemMemory.free(handle).getOrElseThrow(Solver::fail);
  }

  private static IllegalStateException fail() {
    return new IllegalStateException();
  }

  @AllArgsConstructor
  static class SystemMemoryManager {

    private final Problem problem;

    private SystemMemory systemMemory;
    private List<SystemMemoryEvent> eventList;

    static SystemMemoryManager manage(SystemMemory memory, Problem problem) {
      return new SystemMemoryManager(problem, memory, List.empty());
    }

    SystemMemoryManager apply(BiFunction<Problem, SystemMemory, SystemMemoryEvent> mapper) {
      eventList = eventList.append(mapper.apply(problem, systemMemory));
      systemMemory = systemMemory.apply(eventList.last()).getOrElseThrow(() -> new IllegalStateException());
      return this;
    }

    SystemMemoryManager applyRepeated(int times, TriFunction<Problem, SystemMemory, Integer, SystemMemoryEvent> mapper) {
      eventList = eventList.appendAll(
          rangeClosed(1, times)
              .mapToObj(t -> mapper.apply(problem, systemMemory, t))
              .collect(toList())
      );
      return this;
    }

    List<SystemMemoryEvent> getEvents() {
      return eventList;
    }

  }

  @FunctionalInterface
  public interface TriFunction<A, B, C, D> {
    D apply(A a, B b, C c);
  }

}
