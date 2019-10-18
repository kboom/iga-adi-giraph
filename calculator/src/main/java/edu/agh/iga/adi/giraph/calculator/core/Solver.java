package edu.agh.iga.adi.giraph.calculator.core;

import edu.agh.iga.adi.giraph.calculator.core.system.*;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.function.BiFunction;

import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.interimHeight;
import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.totalHeight;
import static edu.agh.iga.adi.giraph.calculator.core.Solver.SystemMemoryManager.manage;
import static edu.agh.iga.adi.giraph.calculator.core.operations.CreateVertexOperation.*;
import static edu.agh.iga.adi.giraph.calculator.core.operations.SendMessageOperation.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class Solver {

  public static List<SystemMemoryEvent> solverEvents(Problem problem, SystemMemory systemMemory) {
    return manage(systemMemory, problem)
        .apply(Solver::allocateMergeLeaves)
        .apply(Solver::allocateMergeAndEliminateLeavesMessages)
        .apply(Solver::freeMergeLeaves)
        .apply(Solver::allocateMergeBranches)
        .apply(Solver::freeMergeAndEliminateLeavesMessages)
        .apply(Solver::allocateMergeAndEliminateBranchesMessages)
        .applyRepeated(
            interimHeight(problem),
            Solver::interimLeadingBlock,
            Solver::interimInternalBlock,
            Solver::interimTrailingBlock
        )
        .apply(Solver::allocateMergeAndEliminateRootMessages)
        .apply(Solver::allocateRootVertex)
        .apply(Solver::freeMergeAndEliminateRootMessages)
        .apply(Solver::allocateBackwardsSubstituteRootMessages)
        .apply(Solver::freeRootVertex)
        .apply(Solver::allocateBackwardsSubstituteInterimVertices)
        .apply(Solver::freeBackwardsSubstituteRootMessages)
        .apply(Solver::allocateBackwardsSubstituteInterimMessages)
        .applyAll(Solver::freeInterimVertices)
        .applyRepeated(
            interimHeight(problem) - 1,
            Solver::backwardsSubstituteInterimBlock
        )
        .apply(Solver::allocateBackwardsSubstituteBranchVertices)
        .apply(Solver::freeBackwardsSubstituteInterimMessages)
        .apply(Solver::backwardsSubstituteBranch)
        .apply(Solver::transposeAndInitialize)
        .getEvents();
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteBranchVertices(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BRANCH_SUBSITUTION.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        BRANCH_SUBSITUTION
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeMergeAndEliminateRootMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, MERGE_AND_ELIMINATE_ROOT);
  }

  private static SystemMemoryEvent freeRootVertex(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, ROOT);
  }

  private static SystemMemoryEvent allocateRootVertex(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        ROOT.totalMemory(problem.getSize(), 1),
        ROOT
    ).getOrElseThrow(Solver::fail);
  }

  private static List<SystemMemoryEvent> interimLeadingBlock(Problem problem, SystemMemory systemMemory, int step) {
    SystemMemory currentSystemMemory = systemMemory;
    val allocatedInterimVertices = allocateMergeInterimVertices(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(allocatedInterimVertices).get();
    val freeMergeAndEliminateBranchesMessages = freeMergeAndEliminateBranchesMessages(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(freeMergeAndEliminateBranchesMessages).get();
    val allocatedMergeAndEliminateInterimMessages = allocateMergeAndEliminateInterimMessages(problem, currentSystemMemory, step);
    return List.of(
        allocatedInterimVertices,
        freeMergeAndEliminateBranchesMessages,
        allocatedMergeAndEliminateInterimMessages
    );
  }

  private static List<SystemMemoryEvent> interimInternalBlock(Problem problem, SystemMemory systemMemory, int step) {
    SystemMemory currentSystemMemory = systemMemory;
    val allocatedInterimVertices = allocateMergeInterimVertices(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(allocatedInterimVertices).get();
    val freedPreviousMergeAndEliminateInterimMessages = freeMergeAndEliminateInterimMessages(problem, currentSystemMemory, step - 1);
    currentSystemMemory = systemMemory.apply(freedPreviousMergeAndEliminateInterimMessages).get();
    val allocatedMergeAndEliminateInterimMessages = allocateMergeAndEliminateInterimMessages(problem, currentSystemMemory, step);
    return List.of(
        allocatedInterimVertices,
        freedPreviousMergeAndEliminateInterimMessages,
        allocatedMergeAndEliminateInterimMessages
    );
  }

  private static List<SystemMemoryEvent> interimTrailingBlock(Problem problem, SystemMemory systemMemory, int step) {
    SystemMemory currentSystemMemory = systemMemory;
    val allocatedInterimVertices = allocateMergeInterimVertices(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(allocatedInterimVertices).get();
    val freedPreviousMergeAndEliminateInterimMessages = freeMergeAndEliminateInterimMessages(problem, currentSystemMemory, step - 1);
    return List.of(
        allocatedInterimVertices,
        freedPreviousMergeAndEliminateInterimMessages
    );
  }

  private static SystemMemoryEvent freeMergeAndEliminateBranchesMessages(Problem problem, SystemMemory systemMemory, int step) {
    return free(systemMemory, MERGE_AND_ELIMINATE_BRANCH.handle(step));
  }

  private static SystemMemoryEvent freeMergeAndEliminateInterimMessages(Problem problem, SystemMemory systemMemory, int step) {
    return free(systemMemory, INTERIM_MERGING.handle(step));
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimVertices(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        INTERIM_SUBSTITUTION.totalMemory(problem.getSize(), totalHeight(problem) - 2),
        INTERIM_SUBSTITUTION.handle(1)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimVertices(Problem problem, SystemMemory systemMemory, int step) {
    return systemMemory.allocate(
        INTERIM_SUBSTITUTION.totalMemory(problem.getSize(), totalHeight(problem) - 1 - step),
        INTERIM_SUBSTITUTION.handle(step)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent allocateMergeInterimVertices(Problem problem, SystemMemory systemMemory, int step) {
    return systemMemory.allocate(
        INTERIM_MERGING.totalMemory(problem.getSize(), totalHeight(problem) - 1 - step),
        INTERIM_MERGING.handle(step)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeMergeInterimVertices(Problem problem, SystemMemory systemMemory, int step) {
    return free(systemMemory, INTERIM_MERGING.handle(step));
  }

  private static SystemMemoryEvent freeSubstituteInterimVertices(Problem problem, SystemMemory systemMemory, int step) {
    return free(systemMemory, INTERIM_SUBSTITUTION.handle(step));
  }

  private static SystemMemoryEvent freeMergeAndEliminateLeavesMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, MERGE_AND_ELIMINATE_LEAVES_MESSAGES);
  }

  private static SystemMemoryEvent allocateMergeBranches(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BRANCH_MERGING.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        BRANCH_MERGING
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent allocateMergeLeaves(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        LEAF_MERGING.totalMemory(problem.getSize(), totalHeight(problem)),
        LEAF_MERGING
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeMergeLeaves(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, LEAF_MERGING);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateLeavesMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_LEAVES_MESSAGES.totalMemory(problem.getSize(), totalHeight(problem)),
        MERGE_AND_ELIMINATE_LEAVES_MESSAGES
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateBranchesMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_BRANCH.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        MERGE_AND_ELIMINATE_BRANCH
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateInterimMessages(Problem problem, SystemMemory systemMemory, int step) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_INTERIM.totalMemory(problem.getSize(), totalHeight(problem) - 1 - step),
        MERGE_AND_ELIMINATE_INTERIM.handle(step)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateRootMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_ROOT.totalMemory(problem.getSize(), 1),
        MERGE_AND_ELIMINATE_ROOT
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateBackwardsSubstituteRootMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_ROOT.totalMemory(problem.getSize(), 1),
        BACKWARDS_SUBSTITUTE_ROOT
    ).getOrElseThrow(Solver::fail);
  }

  private static List<SystemMemoryEvent> freeInterimVertices(Problem problem, SystemMemory systemMemory) {
    SystemMemory currentSystemMemory = systemMemory;

    val freeMergeInterimVertices = freeMergeInterimVertices(problem, currentSystemMemory, interimHeight(problem));
    currentSystemMemory = systemMemory.apply(freeMergeInterimVertices).get();

    val freeSubstituteInterimVertices = freeSubstituteInterimVertices(problem, currentSystemMemory, interimHeight(problem));
    systemMemory.apply(freeSubstituteInterimVertices).get();

    return List.of(
        freeMergeInterimVertices,
        freeSubstituteInterimVertices
    );
  }

  private static List<SystemMemoryEvent> backwardsSubstituteInterimBlock(Problem problem, SystemMemory systemMemory, int step) {
    SystemMemory currentSystemMemory = systemMemory;

    val allocatedVertices = allocateBackwardsSubstituteInterimVertices(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(allocatedVertices).get();

    val freedMessages = freeBackwardsSubstituteInterimMessages(problem, currentSystemMemory, step - 1);
    currentSystemMemory = systemMemory.apply(freedMessages).get();

    val allocatedMessages = allocateBackwardsSubstituteInterimMessages(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(allocatedMessages).get();

    val freeMergeInterimVertices = freeMergeInterimVertices(problem, currentSystemMemory, step);
    currentSystemMemory = systemMemory.apply(freeMergeInterimVertices).get();

    val freeSubstituteInterimVertices = freeSubstituteInterimVertices(problem, currentSystemMemory, step);
    systemMemory.apply(freeSubstituteInterimVertices).get();

    return List.of(
        allocatedVertices,
        freedMessages,
        allocatedMessages,
        freeMergeInterimVertices,
        freeSubstituteInterimVertices
    );
  }

  private static SystemMemoryEvent freeBackwardsSubstituteRootMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_ROOT);
  }

  private static SystemMemoryEvent freeBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_INTERIM.handle(interimHeight(problem)));
  }

  private static SystemMemoryEvent freeBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory, int step) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_INTERIM.handle(step));
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory) {
    return allocateBackwardsSubstituteInterimMessages(problem, systemMemory, 1);
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory, int step) {
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

    SystemMemoryManager applyAll(BiFunction<Problem, SystemMemory, List<SystemMemoryEvent>> mapper) {
      eventList = eventList.appendAll(mapper.apply(problem, systemMemory));
      systemMemory = systemMemory.apply(eventList.last()).getOrElseThrow(() -> new IllegalStateException());
      return this;
    }

    SystemMemoryManager applyRepeated(int times, TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> mapper) {
      eventList = eventList.appendAll(
          rangeClosed(1, times)
              .boxed()
              .flatMap(t -> mapper.apply(problem, systemMemory, t).toJavaStream())
              .collect(toList())
      );
      return this;
    }

    SystemMemoryManager applyRepeated(
        int times,
        TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> leading,
        TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> center
    ) {
      return applyRepeated(times, leading, center, center);
    }

    SystemMemoryManager applyRepeated(
        int times,
        TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> leading,
        TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> center,
        TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> trailing
    ) {
      eventList = eventList
          .appendAll(leading.apply(problem, systemMemory, 1))
          .appendAll(
              rangeClosed(2, times - 1)
                  .boxed()
                  .flatMap(t -> center.apply(problem, systemMemory, t).toJavaStream())
                  .collect(toList())
          )
          .appendAll(trailing.apply(problem, systemMemory, times));
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
