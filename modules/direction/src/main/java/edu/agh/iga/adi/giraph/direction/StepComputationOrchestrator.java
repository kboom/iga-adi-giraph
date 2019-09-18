package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.computation.ComputationResolver;
import lombok.Builder;
import lombok.experimental.Wither;

@Builder
@Wither
public class StepComputationOrchestrator {

  ComputationResolver computationResolver;
  int currentLocalStep;

  /**
   * Sets the operations.
   */
  public void orchestrate(ComputationFacade facade) {

  }

}
