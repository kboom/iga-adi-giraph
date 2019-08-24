package edu.agh.iga.adi.giraph;


import lombok.RequiredArgsConstructor;
import org.apache.hadoop.fs.Path;

import static edu.agh.iga.adi.giraph.direction.DirectionComputationConfig.directionComputationConfig;
import static edu.agh.iga.adi.giraph.direction.DirectionComputationLauncher.solveDirection;

@RequiredArgsConstructor
final class StepSolver {

  private final StepSolverConfig config;

  public void solve() {
    Path transposeInput = config.getInput().suffix("transposeInput");
    Path transposeOutput = config.getOutput().suffix("transposeOutput");

    solveDirection(
        directionComputationConfig()
            .input(transposeInput)
            .output(transposeInput)
            .build()
    );

//    new DirectionFlipperJob(mesh);
//    transpose(transposeInput, transposeOutput);

    solveDirection(
        directionComputationConfig()
            .input(transposeOutput)
            .output(transposeOutput)
            .build()
    );
  }

}
