package edu.agh.iga.adi.giraph.calculator.core;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProblemTree {

  public static int interimHeight(Problem problem) {
    return totalHeight(problem) - 3;
  }

  public static int totalHeight(Problem problem) {
    return totalHeight(problem.getSize());
  }

  public static int totalHeight(int size) {
    return (int) (Math.log(size / 3.0) / Math.log(2) + 2);
  }

}
