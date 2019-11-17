package edu.agh.iga.adi.giraph.core.factory;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;
import lombok.val;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.structure.Access2D;

import static edu.agh.iga.adi.giraph.core.GaussPoints.*;
import static edu.agh.iga.adi.giraph.core.IgaConstants.LEAF_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public final class HorizontalElementFactory implements ElementFactory {

  private static final BSpline1 b1 = new BSpline1();
  private static final BSpline2 b2 = new BSpline2();
  private static final BSpline3 b3 = new BSpline3();

  private static final int[] OTHER_ROWS = {0, 1, 2};

  private final Mesh mesh;
  private final MethodCoefficients coefficients;

  public HorizontalElementFactory(Mesh mesh, MethodCoefficients coefficients) {
    this.mesh = mesh;
    this.coefficients = coefficients;
  }

  @Override
  public IgaElement createLeafElement(Problem problem, IgaVertex vertex) {
    val ma = FACTORY.make(LEAF_SIZE, LEAF_SIZE);
    coefficients.coefficients().supplyTo(ma);
    return igaElement(
        vertex.id(),
        ma,
        rhs(problem, vertex),
        null
    );
  }

  @Override
  public IgaElement createBranchElement(IgaVertex vertex, Access2D<Double> coefficients) {
    val element = branchElement(vertex);
    if (vertex.isLeading()) {
      element.mx.fillMatching(coefficients);
    } else {
      element.mx.regionByRows(OTHER_ROWS).fillMatching(coefficients);
    }
    return element;
  }

  private IgaElement branchElement(IgaVertex vertex) {
    val ma = FACTORY.make(5, 5);
    val mb = FACTORY.make(5, mesh.getDofsX());
    val mx = FACTORY.make(5, mesh.getDofsX());
    return igaElement(vertex.id(), ma, mb, mx);
  }

  private PrimitiveDenseStore rhs(Problem problem, IgaVertex vertex) {
    val dx = mesh.getDx();
    val dy = mesh.getDy();
    val leftSegment = vertex.getLeftSegment();
    PrimitiveDenseStore ds = FACTORY.make(LEAF_SIZE, mesh.getDofsX());
    for (int i = 0; i < mesh.getDofsY(); i++) {
      for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
        val x = GAUSS_POINTS[k] * dx + leftSegment;
        for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
          val wk = GAUSS_POINT_WEIGHTS[k];
          val wl = GAUSS_POINT_WEIGHTS[l];
          val gl = GAUSS_POINTS[l];
          val gk = GAUSS_POINTS[k];

          if (i > 1) {
            val y = (gl + (i - 2)) * dy;
            val v = wk * wl * b1.getValue(gl) * problem.valueAt(x, y);
            ds.add(0, i, b3.getValue(gk) * v);
            ds.add(1, i, b2.getValue(gk) * v);
            ds.add(2, i, b1.getValue(gk) * v);
          }
          if (i > 0 && (i - 1) < mesh.getElementsY()) {
            val y = (gl + (i - 1)) * dy;
            val v = wk * wl * b2.getValue(gl) * problem.valueAt(x, y);
            ds.add(0, i, b3.getValue(gk) * v);
            ds.add(1, i, b2.getValue(gk) * v);
            ds.add(2, i, b1.getValue(gk) * v);
          }
          if (i < mesh.getElementsY()) {
            val y = (gl + i) * dy;
            val v = wk * wl * b3.getValue(gl) * problem.valueAt(x, y);
            ds.add(0, i, b3.getValue(gk) * v);
            ds.add(1, i, b2.getValue(gk) * v);
            ds.add(2, i, b1.getValue(gk) * v);
          }
        }
      }
    }
    return ds;
  }

}
