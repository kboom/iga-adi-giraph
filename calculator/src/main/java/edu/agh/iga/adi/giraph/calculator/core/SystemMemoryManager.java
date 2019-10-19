package edu.agh.iga.adi.giraph.calculator.core;

import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemory;
import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryEvent;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.function.BiFunction;

@AllArgsConstructor
class SystemMemoryManager {

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
