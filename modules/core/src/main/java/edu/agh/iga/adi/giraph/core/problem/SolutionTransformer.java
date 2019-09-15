package edu.agh.iga.adi.giraph.core.problem;

import org.ojalgo.structure.Access2D;

@FunctionalInterface
public interface SolutionTransformer {

  double valueAt(Access2D<Double> c, double x, double y);

}
