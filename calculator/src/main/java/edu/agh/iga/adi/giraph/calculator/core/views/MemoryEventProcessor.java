package edu.agh.iga.adi.giraph.calculator.core.views;

import edu.agh.iga.adi.giraph.calculator.core.system.SystemMemoryEvent;

public interface MemoryEventProcessor {

  void process(SystemMemoryEvent event);

}
