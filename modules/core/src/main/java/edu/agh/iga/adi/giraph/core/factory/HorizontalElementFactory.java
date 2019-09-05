package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import edu.agh.iga.adi.giraph.core.splines.Spline;
import lombok.val;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.structure.Access2D;

import static edu.agh.iga.adi.giraph.core.GaussPoints.*;
import static edu.agh.iga.adi.giraph.core.IgaConstants.*;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static edu.agh.iga.adi.giraph.core.factory.ExplicitMethodCoefficients.COEFFICIENTS;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

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
    }
    if (vertex.is(BranchVertex.class)) {
      return branchElement(vertex);
    }
    return emptyElement(vertex);
  }

  @Override
  public IgaElement createBranchElement(IgaVertex vertex, Access2D<Double> coefficients) {
    val element = branchElement(vertex);
    element.mx.fillMatching(coefficients); // todo not sure if this works
    return element;
  }

  private IgaElement branchElement(IgaVertex vertex) {
    val ma = FACTORY.makeZero(5, 5);
    val mb = FACTORY.makeZero(5, mesh.getDofsX());
    val mx = FACTORY.makeZero(5, mesh.getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  private IgaElement leafElement(Problem problem, IgaVertex vertex) {
    val ma = PrimitiveDenseStore.FACTORY.makeZero(LEAF_SIZE, LEAF_SIZE);
    COEFFICIENTS.supplyTo(ma);
    return igaElement(
        vertex.id(),
        ma,
        rhs(problem, vertex),
        null
    );
  }

  private IgaElement emptyElement(IgaVertex vertex) {
    final PrimitiveDenseStore ma = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    final PrimitiveDenseStore mb = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    final PrimitiveDenseStore mx = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  private PrimitiveDenseStore rhs(Problem problem, IgaVertex vertex) {
    PrimitiveDenseStore ds = PrimitiveDenseStore.FACTORY.makeZero(LEAF_SIZE, mesh.getDofsX());
    for (int i = 0; i < mesh.getDofsY(); i++) {
      fillRightHandSide(ds, problem, b3, vertex, 0, i);
      fillRightHandSide(ds, problem, b2, vertex, 1, i);
      fillRightHandSide(ds, problem, b1, vertex, 2, i);
    }
    return ds;
  }

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
