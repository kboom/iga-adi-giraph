package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import edu.agh.iga.adi.giraph.core.splines.Spline;
import lombok.val;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static edu.agh.iga.adi.giraph.core.GaussPoints.*;
import static edu.agh.iga.adi.giraph.core.IgaConstants.COLS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients.COEFFICIENTS;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;

public final class HorizontalElementFactory implements ElementFactory {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private final Mesh mesh;

  public HorizontalElementFactory(Mesh mesh) {
    this.mesh = mesh;
  }

  @Override
  public IgaElement createElement(Problem problem, IgaVertex vertex) {
    if (vertex.is(LeafVertex.class)) {
      return leafElement(problem, vertex);
    } else {
      return emptyElement(vertex);
    }
  }

  private IgaElement leafElement(Problem problem, IgaVertex vertex) {
    final PrimitiveDenseStore ma = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    final PrimitiveDenseStore mx = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    ma.regionByLimits(3, 3).fillMatching(COEFFICIENTS);
    return igaElement(
        vertex.id(),
        ma,
        rhs(problem, vertex),
        mx
    );
  }

  private IgaElement emptyElement(IgaVertex vertex) {
    final PrimitiveDenseStore ma = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    final PrimitiveDenseStore mb = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    final PrimitiveDenseStore mx = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  private PrimitiveDenseStore rhs(Problem problem, IgaVertex vertex) {
    PrimitiveDenseStore ds = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    for (int i = 0; i < mesh.getDofsY(); i++) {
      fillRightHandSide(ds, problem, b3, vertex, 0, i);
      fillRightHandSide(ds, problem, b2, vertex, 1, i);
      fillRightHandSide(ds, problem, b1, vertex, 2, i);
    }
    return ds;
  }

  /*

  private void initializeRightHandSides(Vertex node) {
        for (int i = 1; i <= node.mesh.getDofsY(); i++) {
            fillRightHandSide(node, spline3, 1, i);
            fillRightHandSide(node, spline2, 2, i);
            fillRightHandSide(node, spline1, 3, i);
        }
    }

    private void fillRightHandSide(Vertex node, Spline spline, int r, int i) {
        for (int k = 1; k <= GaussPoints.GAUSS_POINT_COUNT; k++) {
            double x = GaussPoints.GAUSS_POINTS[k] * node.mesh.getDx() + node.beginning;
            for (int l = 1; l <= GaussPoints.GAUSS_POINT_COUNT; l++) {
                if (i > 2) {
                    double y = (GaussPoints.GAUSS_POINTS[l] + (i - 3)) * node.mesh.getDy();
                    node.m_b[r][i] += GaussPoints.GAUSS_POINT_WEIGHTS[k] * spline.getValue(GaussPoints.GAUSS_POINTS[k]) * GaussPoints.GAUSS_POINT_WEIGHTS[l] * spline1.getValue(GaussPoints.GAUSS_POINTS[l]) * problem.getValue(x, y);
                }
                if (i > 1 && (i - 2) < node.mesh.getElementsY()) {
                    double y = (GaussPoints.GAUSS_POINTS[l] + (i - 2)) * node.mesh.getDy();
                    node.m_b[r][i] += GaussPoints.GAUSS_POINT_WEIGHTS[k] * spline.getValue(GaussPoints.GAUSS_POINTS[k]) * GaussPoints.GAUSS_POINT_WEIGHTS[l] * spline2.getValue(GaussPoints.GAUSS_POINTS[l]) * problem.getValue(x, y);
                }
                if ((i - 1) < node.mesh.getElementsY()) {
                    double y = (GaussPoints.GAUSS_POINTS[l] + (i - 1)) * node.mesh.getDy();
                    node.m_b[r][i] += GaussPoints.GAUSS_POINT_WEIGHTS[k] * spline.getValue(GaussPoints.GAUSS_POINTS[k]) * GaussPoints.GAUSS_POINT_WEIGHTS[l] * spline3.getValue(GaussPoints.GAUSS_POINTS[l]) * problem.getValue(x, y);
                }
            }
        }
    }

   */
  private void fillRightHandSide(PrimitiveDenseStore ds, Problem problem, Spline spline, IgaVertex vertex, int r, int i) {
    for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
      val x = GAUSS_POINTS[k] * mesh.getDx() + vertex.segmentOf().getLeft();
      for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
        val wk = GAUSS_POINT_WEIGHTS[k];
        val wl = GAUSS_POINT_WEIGHTS[l];
        val gl = GAUSS_POINTS[l];
        val sk = spline.getValue(GAUSS_POINTS[k]);

        if (i > 1) {
          val y = (gl + (i - 2)) * mesh.getDy();
          ds.modifyOne(r, i, ADD.by(wk * sk * wl * b1.getValue(gl) * problem.valueAt(x, y)));
        }
        if (i > 0 && (i - 1) < mesh.getElementsY()) {
          val y = (gl + (i - 1)) * mesh.getDy();
          ds.modifyOne(r, i, ADD.by(wk * sk * wl * b2.getValue(gl) * problem.valueAt(x, y)));
        }
        if (i < mesh.getElementsY()) {
          val y = (gl + i) * mesh.getDy();
          ds.modifyOne(r, i, ADD.by(wk * sk * wl * b3.getValue(gl) * problem.valueAt(x, y)));
        }
      }
    }
  }

}
