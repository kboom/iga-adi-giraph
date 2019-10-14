package edu.agh.iga.adi.giraph.calculator.core.system;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import lombok.Builder;
import lombok.experimental.Wither;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;
import static lombok.AccessLevel.PRIVATE;

@Wither(PRIVATE)
@Builder(access = PRIVATE)
public final class SystemMemory {

  private final Memory availableMemory;
  private final Map<MemoryHandle, Memory> allocatedMemory;

  public static SystemMemory systemMemory(SystemMemoryCreated createdEvent) {
    return SystemMemory.builder()
        .availableMemory(createdEvent.getTotalMemory())
        .allocatedMemory(HashMap.empty())
        .build();
  }

  public Either<OutOfSystemMemoryException, SystemMemoryAllocated> allocate(Memory memory, MemoryHandle handle) {
    if (hasFreeMemory(memory)) {
      return Either.right(
          SystemMemoryAllocated.builder()
              .memory(memory)
              .handle(handle)
              .build()
      );
    } else {
      return Either.left(new OutOfSystemMemoryException());
    }
  }

  public Either<IllegalMemoryAccessException, SystemMemoryFreed> free(MemoryHandle handle) {
    if (isAllocated(handle)) {
      return Either.right(new SystemMemoryFreed(handle));
    } else {
      return Either.left(new IllegalMemoryAccessException());
    }
  }

  public Either<IllegalTransitionException, SystemMemory> apply(SystemMemoryEvent event) {
    return Match(event).of(
        Case($(instanceOf(SystemMemoryAllocated.class)), this::withEvent),
        Case($(instanceOf(SystemMemoryFreed.class)), this::withEvent),
        Case($(), Either.left(new IllegalTransitionException()))
    );
  }

  private Either<IllegalTransitionException, SystemMemory> withEvent(SystemMemoryAllocated event) {
    return Either.right(withAllocatedMemory(currentWithAllocation(event.getHandle(), event.getMemory())));
  }

  private Either<IllegalTransitionException, SystemMemory> withEvent(SystemMemoryFreed event) {
    return Either.right(withAllocatedMemory(currentWithoutAllocation(event.getHandle())));
  }

  private Map<MemoryHandle, Memory> currentWithAllocation(MemoryHandle handle, Memory memory) {
    return allocatedMemory.put(handle, memory);
  }

  private Map<MemoryHandle, Memory> currentWithoutAllocation(MemoryHandle handle) {
    return allocatedMemory.remove(handle);
  }

  private boolean hasFreeMemory(Memory memory) {
    return allocatedMemory.values()
        .fold(memory, Memory::sum)
        .isNotGraterThan(availableMemory);
  }

  private boolean isAllocated(MemoryHandle handle) {
    return allocatedMemory.containsKey(handle);
  }

}
