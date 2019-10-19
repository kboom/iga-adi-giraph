package edu.agh.iga.adi.giraph.calculator.core.views;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryAllocated;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryEvent;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryFreed;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import lombok.val;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.ZERO_MEMORY;
import static edu.agh.iga.adi.giraph.calculator.core.Memory.greaterOf;
import static edu.agh.iga.adi.giraph.calculator.core.views.MaxMemoryProcessor.MaxMemoryState;
import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@NoArgsConstructor
public class MaxMemoryProcessor implements MemoryEventProcessor<MaxMemoryState> {

  public static final MaxMemoryProcessor MAX_MEMORY_PROCESSOR = new MaxMemoryProcessor();

  @Override
  public MaxMemoryState process(MaxMemoryState previousState, SystemMemoryEvent event) {
    return Match(event).of(
        Case($(instanceOf(SystemMemoryAllocated.class)), evt -> handle(previousState, evt)),
        Case($(instanceOf(SystemMemoryFreed.class)), evt -> handle(previousState, evt))
    );
  }

  private static MaxMemoryState handle(MaxMemoryState state, SystemMemoryAllocated event) {
    val oldMemory = state.current;
    val currentMemory = state.getCurrent().plus(event.getMemory());

    return state
        .withCurrent(currentMemory)
        .withMax(greaterOf(currentMemory, oldMemory));
  }

  private static MaxMemoryState handle(MaxMemoryState state, SystemMemoryFreed event) {
    return state.withCurrent(state.current.minus(event.getMemory()));
  }

  @Value
  @Builder
  @Wither
  public static class MaxMemoryState {

    @Builder.Default
    Memory max = ZERO_MEMORY;

    @Builder.Default
    Memory current = ZERO_MEMORY;

    public static MaxMemoryState zeroMaxMemoryState() {
      return MaxMemoryState.builder().build();
    }

  }

}
