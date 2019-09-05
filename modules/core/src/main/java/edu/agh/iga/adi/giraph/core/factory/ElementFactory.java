package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import org.ojalgo.structure.Access2D;

public interface ElementFactory {

  /*
   * This should create leaves (as only they are initialised from problem.
   */
  @Deprecated
  IgaElement createElement(Problem problem, IgaVertex vertex);

  IgaElement createBranchElement(IgaVertex vertex, Access2D<Double> coefficients);

}
