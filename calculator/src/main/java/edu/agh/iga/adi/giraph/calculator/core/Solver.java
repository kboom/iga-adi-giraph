package edu.agh.iga.adi.giraph.calculator.core;

import edu.agh.iga.adi.giraph.calculator.core.system.*;
import io.vavr.collection.List;
import lombok.val;

import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.interimHeight;
import static edu.agh.iga.adi.giraph.calculator.core.ProblemTree.totalHeight;
import static edu.agh.iga.adi.giraph.calculator.core.SystemMemoryManager.manage;
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
        .applyAll(Solver::backwardsSubstituteBranchStep)
        .applyAll(Solver::transposeAndInitializeStep)
        .getEvents();
  }

  private static List<SystemMemoryEvent> mergeAndEliminateLeavesStep(Problem problem, SystemMemory systemMemory) {
    return manage(systemMemory, problem)
        .apply(Solver::allocateLeavesForMerging)
        .apply(Solver::allocateMergeAndEliminateLeavesMessages)
        .apply(Solver::freeLeavesForMerging)
        .apply(totalHeight(problem) - 1, Solver::allocateBranchesForMerging)
        .apply(Solver::freeMergeAndEliminateLeavesMessages)
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
        .applyAll(height, Solver::freeInterimVertices)
        .apply(height, Solver::freeBackwardsSubstituteInterimMessages)
        .apply(height + 1, Solver::allocateInterimForBackwardsSubstitution)
        .getEvents();
  }

  private static List<SystemMemoryEvent> backwardsSubstituteBranchStep(Problem problem, SystemMemory systemMemory) {
    val height = totalHeight(problem) - 1;
    return manage(systemMemory, problem)
        .apply(height, Solver::allocateBackwardsSubstituteBranchMessages)
        .applyAll(height, Solver::freeInterimVertices)
        .apply(height, Solver::allocateBackwardsSubstituteBranchVertices)
        .apply(height, Solver::freeBackwardsSubstituteBranchMessages)
        .getEvents();
  }

  private static List<SystemMemoryEvent> transposeAndInitializeStep(Problem problem, SystemMemory systemMemory) {
    val height = totalHeight(problem) - 1;
    return manage(systemMemory, problem)
        .apply(Solver::transposeAndInitialize)
        .applyAll(height, Solver::freeBranchVertices)
        .apply(Solver::freeTransposeAndInitialize)
        .getEvents();
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteBranchVertices(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        BRANCH_FOR_BACKWARDS_SUBSTITUTION.totalMemory(problem.getSize(), value),
        BRANCH_FOR_BACKWARDS_SUBSTITUTION.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteBranchMessages(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE.totalMemory(problem.getSize(), value),
        BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE.handle(value)
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

  private static SystemMemoryEvent freeBackwardsSubstituteBranchMessages(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE.handle(value));
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

  private static SystemMemoryEvent allocateBranchesForMerging(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        BRANCHES_FOR_MERGING.totalMemory(problem.getSize(), value),
        BRANCHES_FOR_MERGING.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeBranchesForMerging(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, BRANCHES_FOR_MERGING.handle(value));
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

  private static List<SystemMemoryEvent> freeBranchVertices(Problem problem, SystemMemory systemMemory, int value) {
    return manage(systemMemory, problem)
        .apply(value, Solver::freeBranchesForMerging)
        .apply(value, Solver::freeBranchesForSubstitution)
        .getEvents();
  }

  private static SystemMemoryEvent freeBranchesForSubstitution(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, BRANCH_FOR_BACKWARDS_SUBSTITUTION.handle(value));
  }

  private static SystemMemoryEvent freeBackwardsSubstituteRootMessages(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_ROOT_MESSAGE);
  }

  private static SystemMemoryEvent freeBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory, int value) {
    return free(systemMemory, BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.handle(value));
  }

  private static SystemMemoryEvent allocateBackwardsSubstituteInterimMessages(Problem problem, SystemMemory systemMemory, int value) {
    return systemMemory.allocate(
        BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.totalMemory(problem.getSize(), value),
        BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE.handle(value)
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryAllocated transposeAndInitialize(Problem problem, SystemMemory systemMemory) {
    return systemMemory.allocate(
        TRANSPOSE_AND_INITIALISE_MESSAGE.totalMemory(problem.getSize(), totalHeight(problem)),
        TRANSPOSE_AND_INITIALISE_MESSAGE
    ).getOrElseThrow(Solver::fail);
  }

  private static SystemMemoryEvent freeTransposeAndInitialize(Problem problem, SystemMemory systemMemory) {
    return free(systemMemory, TRANSPOSE_AND_INITIALISE_MESSAGE);
  }

  private static SystemMemoryFreed free(SystemMemory systemMemory, MemoryHandle handle) {
    return systemMemory.free(handle).getOrElseThrow(Solver::fail);
  }

  private static IllegalStateException fail() {
    return new IllegalStateException();
  }

}
