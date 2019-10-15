package edu.agh.iga.adi.giraph.calculator.core.views;

import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryEvent;

public interface MemoryEventProcessor<T> {

  T process(T previousState, SystemMemoryEvent event);

}
