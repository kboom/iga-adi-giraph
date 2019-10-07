package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import org.ojalgo.structure.Access2D;

public interface ElementFactory {

  /**
   * Creates the leaves by using the values obtained from the problem corresponding to that leaf element.
   *
   * @param problem the problem to use for initialisation
   * @param vertex  the leaf vertex the element has to be returned for
   * @return the element for the {@code vertex} provided
   */
  IgaElement createLeafElement(Problem problem, IgaVertex vertex);

  /**
   * Produces branch elements using the values provided.
   *
   * @param vertex       a vertex to create
   * @param coefficients the coefficients to be used to initialize the {@code vertex}
   * @return the initialized element corresponding ot the {@code vertex}
   */
  IgaElement createBranchElement(IgaVertex vertex, Access2D<Double> coefficients);

}
