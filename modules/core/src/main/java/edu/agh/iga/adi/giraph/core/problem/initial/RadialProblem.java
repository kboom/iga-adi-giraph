package edu.agh.iga.adi.giraph.core.problem.initial;

import edu.agh.iga.adi.giraph.core.problem.Problem;
import lombok.Builder;

@Builder
public class RadialProblem implements Problem {

  private double center;
  private double radius;
  private double value;

  @Override
  public double valueAt(double x, double y) {
    if (x > center - radius && x < center + radius && y < center + radius && y > center - radius) {
      return value;
    } else {
      return 0;
    }
  }

}
