package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.*;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import edu.agh.iga.adi.giraph.core.splines.Spline;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.structure.Access2D;

import static edu.agh.iga.adi.giraph.core.GaussPoints.GAUSS_POINTS;
import static edu.agh.iga.adi.giraph.core.GaussPoints.GAUSS_POINT_COUNT;
import static edu.agh.iga.adi.giraph.core.IgaConstants.COLS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients.COEFFICIENTS;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class HorizontalElementFactory implements ElementFactory {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private final Mesh mesh;
  private final DirectionTree directionTree;

  public HorizontalElementFactory(Mesh mesh, DirectionTree directionTree) {
    this.mesh = mesh;
    this.directionTree = directionTree;
  }

  @Override
  public IgaElement createElement(Problem problem, IgaVertex vertex) {
    PrimitiveDenseStore ma = FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    ma.fillMatching(COEFFICIENTS);
    return igaElement(
        vertex.id(),
        ma,
        FACTORY.copy(rhs(problem, vertex)),
        FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX())
    );
  }

  private Access2D<Double> rhs(Problem problem, IgaVertex vertex) {
    PrimitiveDenseStore ds = FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    for (int i = 1; i <= mesh.getDofsY(); i++) {
      fillRightHandSide(ds, problem, b3, vertex, 1, i);
      fillRightHandSide(ds, problem, b2, vertex, 2, i);
      fillRightHandSide(ds, problem, b1, vertex, 3, i);
    }
    return ds;
  }

  private void fillRightHandSide(PrimitiveDenseStore ds, Problem problem, Spline spline, IgaVertex vertex, int r, int i) {
    for (int k = 1; k <= GAUSS_POINT_COUNT; k++) {
      double x = GAUSS_POINTS[k] * mesh.getDx() + vertex.segmentOf().getLeft();
      for (int l = 1; l <= GAUSS_POINT_COUNT; l++) {
        if (i > 2) {
          double y = (GAUSS_POINTS[l] + (i - 3)) * mesh.getDy();
          ds.modifyOne(r, i, ADD.by(GaussPoints.GAUSS_POINT_WEIGHTS[k] * spline.getValue(GAUSS_POINTS[k]) * GaussPoints.GAUSS_POINT_WEIGHTS[l] * b1.getValue(GAUSS_POINTS[l]) * problem.valueAt(x, y)));
        }
        if (i > 1 && (i - 2) < mesh.getElementsY()) {
          double y = (GAUSS_POINTS[l] + (i - 2)) * mesh.getDy();
          ds.modifyOne(r, i, ADD.by(GaussPoints.GAUSS_POINT_WEIGHTS[k] * spline.getValue(GAUSS_POINTS[k]) * GaussPoints.GAUSS_POINT_WEIGHTS[l] * b2.getValue(GAUSS_POINTS[l]) * problem.valueAt(x, y)));
        }
        if ((i - 1) < mesh.getElementsY()) {
          double y = (GAUSS_POINTS[l] + (i - 1)) * mesh.getDy();
          ds.modifyOne(r, i, ADD.by(GaussPoints.GAUSS_POINT_WEIGHTS[k] * spline.getValue(GAUSS_POINTS[k]) * GaussPoints.GAUSS_POINT_WEIGHTS[l] * b3.getValue(GAUSS_POINTS[l]) * problem.valueAt(x, y)));
        }
      }
    }
  }

}
