package edu.agh.iga.adi.giraph.calculator.core.views;

import edu.agh.iga.adi.giraph.calculator.core.Memory;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryAllocated;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryEvent;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryFreed;
import lombok.NoArgsConstructor;

import static edu.agh.iga.adi.giraph.calculator.core.Memory.ZERO_MEMORY;
import static edu.agh.iga.adi.giraph.calculator.core.Memory.greaterOf;
import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@NoArgsConstructor
public class MaxMemoryProcessor implements MemoryEventProcessor {

  private Memory max = ZERO_MEMORY;
  private Memory current = ZERO_MEMORY;

  @Override
  public void process(SystemMemoryEvent event) {
    Match(event).of(
        Case($(instanceOf(SystemMemoryAllocated.class)), o -> run(() -> handle(o))),
        Case($(instanceOf(SystemMemoryFreed.class)), o -> run(() -> handle(o)))
    );
  }

  private void handle(SystemMemoryAllocated event) {
    current = current.plus(event.getMemory());
    max = greaterOf(max, current);
  }

  private void handle(SystemMemoryFreed event) {
    current = current.minus(event.getMemory());
  }

}
