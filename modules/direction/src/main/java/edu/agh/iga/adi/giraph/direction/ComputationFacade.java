package edu.agh.iga.adi.giraph.direction;

import org.apache.giraph.graph.Computation;

public interface ComputationFacade {

  void setComputation(Class<? extends Computation> computation);

}
