package edu.agh.iga.adi.giraph.calculator.core.views;

import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryEvent;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemoryProcessorReplay {

  private final List<SystemMemoryEvent> events;

  public <R, T extends MemoryEventProcessor<R>> R foldEvents(T processor, R initial) {
    return events.foldLeft(initial, processor::process);
  }

  public static MemoryProcessorReplay memoryProcessorReplayOf(List<SystemMemoryEvent> events) {
    return new MemoryProcessorReplay(events);
  }

}
