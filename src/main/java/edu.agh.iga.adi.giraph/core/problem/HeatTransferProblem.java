package edu.agh.iga.adi.giraph.core.problem;

public final class HeatTransferProblem implements Problem {

  private final PartialSolution solution;

  public HeatTransferProblem(PartialSolution solution) {
    this.solution = solution;
  }

  @Override
  public double valueAt(double x, double y) {
    return 0;
  }

}
