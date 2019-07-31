package edu.agh.iga.adi.giraph;


import org.apache.hadoop.fs.Path;

import static edu.agh.iga.adi.giraph.DirectionComputationLauncher.computationConfig;
import static edu.agh.iga.adi.giraph.DirectionComputationLauncher.solveDirection;
import static edu.agh.iga.adi.giraph.data.TransposeJob.transpose;

final class TwoDimensionalStepSolver {

  private Path input;
  private Path output;
  private Path tmp;

  public void solve() {
    Path transposeInput = tmp.suffix("transposeInput");
    Path transposeOutput = tmp.suffix("transposeOutput");

    solveDirection(
        computationConfig()
            .setInput(input)
            .setOutput(transposeInput)
    );

    transpose(transposeInput, transposeOutput);

    solveDirection(
        computationConfig()
            .setInput(transposeOutput)
            .setOutput(output)
    );
  }

}
