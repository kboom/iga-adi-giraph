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
import lombok.val;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.structure.Access2D;

import static edu.agh.iga.adi.giraph.core.GaussPoints.*;
import static edu.agh.iga.adi.giraph.core.IgaConstants.*;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static org.ojalgo.function.constant.PrimitiveMath.ADD;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class HorizontalElementFactory implements ElementFactory {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private final Mesh mesh;
  private final MethodCoefficients coefficients;

  public HorizontalElementFactory(Mesh mesh, MethodCoefficients coefficients) {
    this.mesh = mesh;
    this.coefficients = coefficients;
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
    if (vertex.isLeading()) {
      element.mx.fillMatching(coefficients);
    } else {
      element.mx.regionByRows(0, 1, 2).fillMatching(coefficients);
    }
    return element;
  }

  private IgaElement branchElement(IgaVertex vertex) {
    val ma = FACTORY.makeZero(5, 5);
    val mb = FACTORY.makeZero(5, mesh.getDofsX());
    val mx = FACTORY.makeZero(5, mesh.getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  private IgaElement leafElement(Problem problem, IgaVertex vertex) {
    val ma = FACTORY.makeZero(LEAF_SIZE, LEAF_SIZE);
    coefficients.coefficients().supplyTo(ma);
    return igaElement(
        vertex.id(),
        ma,
        rhs(problem, vertex),
        null
    );
  }

  private IgaElement emptyElement(IgaVertex vertex) {
    final PrimitiveDenseStore ma = FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    final PrimitiveDenseStore mb = FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    final PrimitiveDenseStore mx = FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  private PrimitiveDenseStore rhs(Problem problem, IgaVertex vertex) {
    PrimitiveDenseStore ds = FACTORY.makeZero(LEAF_SIZE, mesh.getDofsX());
    for (int i = 0; i < mesh.getDofsY(); i++) {
      for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
        val x = GAUSS_POINTS[k] * mesh.getDx() + vertex.getLeftSegment();
        for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
          val wk = GAUSS_POINT_WEIGHTS[k];
          val wl = GAUSS_POINT_WEIGHTS[l];
          val gl = GAUSS_POINTS[l];
          val gk = GAUSS_POINTS[k];

          if (i > 1) {
            val y = (gl + (i - 2)) * mesh.getDy();
            val v = wk * wl * b1.getValue(gl) * problem.valueAt(x, y);
            ds.modifyOne(0, i, ADD.by(b3.getValue(gk) * v));
            ds.modifyOne(1, i, ADD.by(b2.getValue(gk) * v));
            ds.modifyOne(2, i, ADD.by(b1.getValue(gk) * v));
          }
          if (i > 0 && (i - 1) < mesh.getElementsY()) {
            val y = (gl + (i - 1)) * mesh.getDy();
            val v = wk * wl * b2.getValue(gl) * problem.valueAt(x, y);
            ds.modifyOne(0, i, ADD.by(b3.getValue(gk) * v));
            ds.modifyOne(1, i, ADD.by(b2.getValue(gk) * v));
            ds.modifyOne(2, i, ADD.by(b1.getValue(gk) * v));
          }
          if (i < mesh.getElementsY()) {
            val y = (gl + i) * mesh.getDy();
            val v = wk * wl * b3.getValue(gl) * problem.valueAt(x, y);
            ds.modifyOne(0, i, ADD.by(b3.getValue(gk) * v));
            ds.modifyOne(1, i, ADD.by(b2.getValue(gk) * v));
            ds.modifyOne(2, i, ADD.by(b1.getValue(gk) * v));
          }
        }
      }
    }
    return ds;
  }

}
