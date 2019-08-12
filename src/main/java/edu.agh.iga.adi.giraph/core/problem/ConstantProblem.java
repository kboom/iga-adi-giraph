package edu.agh.iga.adi.giraph.core.problem;

public final class ConstantProblem implements Problem {

  @Override
  public double valueAt(double x, double y) {
    return 1;
  }

}
