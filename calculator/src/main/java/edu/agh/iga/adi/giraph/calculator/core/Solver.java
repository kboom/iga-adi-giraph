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

public class Solver {

  public static List<SystemMemoryEvent> solverEvents(Problem problem, SystemMemory systemMemory) {
    return manage(systemMemory, problem)
        .applyAll(Solver::mergeAndEliminateLeavesStep)
        .applyAll(Solver::mergeAndEliminateBranchesStep)
        .applyRepeated(
            interimHeight(problem),
            Solver::mergeAndEliminateInterimStep
        )
        .applyAll(Solver::solveRoot)
        .applyAll(Solver::backwardsSubstituteRoot)
        .applyRepeated(
            interimHeight(problem),
            Solver::backwardsSubstituteInterimStep
        )
//        .apply(Solver::allocateBackwardsSubstituteBranchVertices)
//        .apply(Solver::freeBackwardsSubstituteInterimMessages)
//        .apply(Solver::backwardsSubstituteBranch)
//        .apply(Solver::transposeAndInitialize)
        .getEvents();
  }

  private static List<SystemMemoryEvent> mergeAndEliminateLeavesStep(Problem problem, SystemMemory systemMemory) {
    return manage(systemMemory, problem)
        .apply(Solver::allocateLeavesForMerging)
        .apply(Solver::allocateMergeAndEliminateLeavesMessages)
        .apply(Solver::freeLeavesForMerging)
        .apply(Solver::allocateBranchesForMerging)
        .getEvents();
  }

  private static List<SystemMemoryEvent> mergeAndEliminateBranchesStep(Problem problem, SystemMemory systemMemory) {
    val height = totalHeight(problem) - 1;
    return manage(systemMemory, problem)
        .apply(Solver::allocateMergeAndEliminateBranchesMessages)
        .apply(height, Solver::allocateInterimForMerging)
        .apply(Solver::freeMergeAndEliminateBranchesMessages)
        .getEvents();
  }

  private static List<SystemMemoryEvent> mergeAndEliminateInterimStep(Problem problem, SystemMemory systemMemory, int step) {
    val height = totalHeight(problem) - 1 - step;
    return manage(systemMemory, problem)
        .apply(height, Solver::allocateMergeAndEliminateInterimMessages)
        .apply(height, Solver::allocateInterimForMerging)
        .apply(height, Solver::freeMergeAndEliminateInterimMessages)
        .getEvents();
  }

  private static List<SystemMemoryEvent> solveRoot(Problem problem, SystemMemory systemMemory) {
    return manage(systemMemory, problem)
        .apply(Solver::allocateMergeAndEliminateRootMessages)
        .apply(Solver::allocateRootVertex)
        .apply(Solver::freeMergeAndEliminateRootMessages)
        .apply(Solver::allocateBackwardsSubstituteRootMessages)
        .apply(Solver::freeRootVertex)
        .getEvents();
  }

  private static List<SystemMemoryEvent> backwardsSubstituteRoot(Problem problem, SystemMemory systemMemory) {
    val height = 3;
    return manage(systemMemory, problem)
        .apply(height, Solver::allocateInterimForBackwardsSubstitution)
        .apply(Solver::freeBackwardsSubstituteRootMessages)
        .getEvents();
  }

  private static List<SystemMemoryEvent> backwardsSubstituteInterimStep(Problem problem, SystemMemory systemMemory, int step) {
    val height = step + 2;
    return manage(systemMemory, problem)
        .apply(height, Solver::allocateBackwardsSubstituteInterimMessages)
        .apply(height, Solver::allocateInterimForBackwardsSubstitution)
        .apply(height, Solver::freeBackwardsSubstituteInterimMessages)
        .applyAll(height, Solver::freeInterimVertices)
        .getEvents();
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteBranchVertices(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BRANCH_FOR_BACKWARDS_SUBSTITUTION.totalMemory(problem.getSize(), 1),
        BRANCH_FOR_BACKWARDS_SUBSTITUTION
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeMergeAndEliminateRootMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, MERGE_AND_ELIMINATE_ROOT_MESSAGE);
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

  private static SystemMemoryEvent freeMergeAndEliminateBranchesMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, MERGE_AND_ELIMINATE_BRANCH_MESSAGE);
  }

  private static SystemMemoryEvent freeMergeAndEliminateInterimMessages(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, MERGE_AND_ELIMINATE_INTERIM_MESSAGE.handle(value));
  }

  private static SystemMemoryEvent allocateInterimForBackwardsSubstitution(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        INTERIM_FOR_BACKWARDS_SUBSTITUTION.totalMemory(problem.getSize(), value),
        INTERIM_FOR_BACKWARDS_SUBSTITUTION.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent allocateInterimForMerging(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        INTERIM_FOR_MERGING.totalMemory(problem.getSize(), value),
        INTERIM_FOR_MERGING.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeInterimForMerging(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, INTERIM_FOR_MERGING.handle(value));
  }

  private static SystemMemoryEvent freeSubstituteInterimVertices(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, INTERIM_FOR_BACKWARDS_SUBSTITUTION.handle(value));
  }

  private static SystemMemoryEvent freeMergeAndEliminateLeavesMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, MERGE_AND_ELIMINATE_LEAVES_MESSAGE);
  }

  private static SystemMemoryEvent allocateBranchesForMerging(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BRANCHES_FOR_MERGING.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        BRANCHES_FOR_MERGING
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeBranchesForMerging(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, BRANCHES_FOR_MERGING);
  }

  private static SystemMemoryEvent allocateLeavesForMerging(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        LEAVES_FOR_MERGING.totalMemory(problem.getSize(), totalHeight(problem)),
        LEAVES_FOR_MERGING
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeLeavesForMerging(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, LEAVES_FOR_MERGING);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateLeavesMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_LEAVES_MESSAGE.totalMemory(problem.getSize(), totalHeight(problem)),
        MERGE_AND_ELIMINATE_LEAVES_MESSAGE
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateBranchesMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_BRANCH_MESSAGE.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        MERGE_AND_ELIMINATE_BRANCH_MESSAGE
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateInterimMessages(Problem problem, SystemMemory systemMemory) {
    return allocateMergeAndEliminateInterimMessages(problem, systemMemory, totalHeight(problem) - 2);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateInterimMessages(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_INTERIM_MESSAGE.totalMemory(problem.getSize(), value),
        MERGE_AND_ELIMINATE_INTERIM_MESSAGE.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateMergeAndEliminateRootMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        MERGE_AND_ELIMINATE_ROOT_MESSAGE.totalMemory(problem.getSize(), 1),
        MERGE_AND_ELIMINATE_ROOT_MESSAGE
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated allocateBackwardsSubstituteRootMessages(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_ROOT_MESSAGE.totalMemory(problem.getSize(), 1),
        BACKWARDS_SUBSTITUTE_ROOT_MESSAGE
    ).getOrElseThrow(Solver::fail);
  }

  private static List<SystemMemoryEvent> freeInterimVertices(Problem problem, SystemMemory systemMemory, int value) {
    return manage(systemMemory, problem)
        .apply(value, Solver::freeInterimForMerging)
        .apply(value, Solver::freeSubstituteInterimVertices)
        .getEvents();
  }

  private static SystemMemoryEvent freeBackwardsSubstituteRootMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_ROOT_MESSAGE);
  }

  private static SystemMemoryEvent freeBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.handle(interimHeight(problem)));
  }

  private static SystemMemoryEvent freeBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.handle(value));
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory) {
    return allocateBackwardsSubstituteInterimMessages(problem, systemMemory, 1);
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.totalMemory(problem.getSize(), value),
        BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated backwardsSubstituteBranch(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE.totalMemory(problem.getSize(), totalHeight(problem) - 1),
        BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated transposeAndInitialize(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        TRANSPOSE_AND_INITIALISE_MESSAGE.totalMemory(problem.getSize(), totalHeight(problem)),
        TRANSPOSE_AND_INITIALISE_MESSAGE
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
      val newEvent = mapper.apply(problem, systemMemory);
      systemMemory = systemMemory.apply(newEvent).getOrElseThrow(() -> new IllegalStateException());
      eventList = eventList.append(newEvent);
      return this;
    }

    SystemMemoryManager applyAll(BiFunction<Problem, SystemMemory, List<SystemMemoryEvent>> mapper) {
      val newEvents = mapper.apply(problem, systemMemory);
      systemMemory = newEvents.foldLeft(systemMemory, (m, e) -> m.apply(e).getOrElseThrow(() -> new IllegalStateException()));
      eventList = eventList.appendAll(newEvents);
      return this;
    }

    <T> SystemMemoryManager apply(T value, TriFunction<Problem, SystemMemory, T, SystemMemoryEvent> mapper) {
      return applyAll(value, (problem, memory, v) -> List.of(mapper.apply(problem, memory, v)));
    }

    <T> SystemMemoryManager applyAll(T value, TriFunction<Problem, SystemMemory, T, List<SystemMemoryEvent>> mapper) {
      val newEvents = mapper.apply(problem, systemMemory, value);
      systemMemory = newEvents.foldLeft(systemMemory, (m, e) -> m.apply(e).getOrElseThrow(() -> new IllegalStateException()));
      eventList = eventList.appendAll(newEvents);
      return this;
    }

    SystemMemoryManager applyRepeated(int times, TriFunction<Problem, SystemMemory, Integer, List<SystemMemoryEvent>> mapper) {
      return List.rangeClosed(1, times)
          .foldLeft(this, (context, step) -> context.applyAll(step, mapper));
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
