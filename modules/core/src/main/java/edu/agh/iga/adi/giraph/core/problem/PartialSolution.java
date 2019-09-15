package edu.agh.iga.adi.giraph.core.problem;

/**
 * A solution for the problem which allows to compute values anywhere inside a single square element.
 * No node can hold an entire solution but it is split into independent parts and distributed across the cluster.
 */
public interface PartialSolution {

  double valueAt(double x, double y);

  double valueAt(double x, double y, SolutionTransformer transformer);

}
