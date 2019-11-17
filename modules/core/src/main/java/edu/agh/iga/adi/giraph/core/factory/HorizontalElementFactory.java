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
    PrimitiveDenseStore ds = FACTORY.make(LEAF_SIZE, mesh.getDofsX());
    initializeZero(problem, vertex, ds);
    initializeFirst(problem, vertex, ds);
    initializeCenter(problem, vertex, ds);
    initializeLastElement(problem, vertex, ds);
    initializeRightBorder(problem, vertex, ds);
    return ds;
  }

  private void initializeRightBorder(Problem problem, IgaVertex vertex, PrimitiveDenseStore ds) {
    val dx = mesh.getDx();
    val dy = mesh.getDy();
    val leftSegment = vertex.getLeftSegment();
    for (int i = mesh.getElementsY() + 1; i < mesh.getDofsY(); i++) {
      for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
        val x = GAUSS_POINTS[k] * dx + leftSegment;
        val b1gk = SPLINE_1_GAUSS_POINTS[k];
        val b2gk = SPLINE_2_GAUSS_POINTS[k];
        val b3gk = SPLINE_3_GAUSS_POINTS[k];

        for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
          val wkl = GAUSS_POINTS_WEIGHTS_MULTIPLIED[k * GAUSS_POINT_COUNT + l];
          val gl = GAUSS_POINTS[l];

          val y = (gl + (i - 2)) * dy;
          val v = wkl * b1.getValue(gl) * problem.valueAt(x, y);
          ds.add(0, i, b3gk * v);
          ds.add(1, i, b2gk * v);
          ds.add(2, i, b1gk * v);
        }
      }
    }
  }

  private void initializeLastElement(Problem problem, IgaVertex vertex, PrimitiveDenseStore ds) {
    val elementCount = mesh.getElementsY();
    val dx = mesh.getDx();
    val dy = mesh.getDy();
    val leftSegment = vertex.getLeftSegment();
    for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
      val x = GAUSS_POINTS[k] * dx + leftSegment;
      val b1gk = SPLINE_1_GAUSS_POINTS[k];
      val b2gk = SPLINE_2_GAUSS_POINTS[k];
      val b3gk = SPLINE_3_GAUSS_POINTS[k];

      for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
        val wkl = GAUSS_POINTS_WEIGHTS_MULTIPLIED[k * GAUSS_POINT_COUNT + l];
        val gl = GAUSS_POINTS[l];

        val yf = (gl + (elementCount - 2)) * dy;
        val vf = wkl * b1.getValue(gl) * problem.valueAt(x, yf);
        ds.add(0, elementCount, b3gk * vf);
        ds.add(1, elementCount, b2gk * vf);
        ds.add(2, elementCount, b1gk * vf);

        val yl = (gl + (elementCount - 1)) * dy;
        val vl = wkl * b2.getValue(gl) * problem.valueAt(x, yl);
        ds.add(0, elementCount, b3gk * vl);
        ds.add(1, elementCount, b2gk * vl);
        ds.add(2, elementCount, b1gk * vl);
      }
    }
  }

  private void initializeZero(Problem problem, IgaVertex vertex, PrimitiveDenseStore ds) {
    val dx = mesh.getDx();
    val dy = mesh.getDy();
    val leftSegment = vertex.getLeftSegment();
    for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
      val x = GAUSS_POINTS[k] * dx + leftSegment;
      val b1gk = SPLINE_1_GAUSS_POINTS[k];
      val b2gk = SPLINE_2_GAUSS_POINTS[k];
      val b3gk = SPLINE_3_GAUSS_POINTS[k];

      for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
        val wkl = GAUSS_POINTS_WEIGHTS_MULTIPLIED[k * GAUSS_POINT_COUNT + l];
        val gl = GAUSS_POINTS[l];

        val y = gl * dy;
        val v = wkl * b3.getValue(gl) * problem.valueAt(x, y);
        ds.add(0, 0, b3gk * v);
        ds.add(1, 0, b2gk * v);
        ds.add(2, 0, b1gk * v);
      }
    }
  }

  private void initializeFirst(Problem problem, IgaVertex vertex, PrimitiveDenseStore ds) {
    val dx = mesh.getDx();
    val dy = mesh.getDy();
    val leftSegment = vertex.getLeftSegment();
    for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
      val x = GAUSS_POINTS[k] * dx + leftSegment;
      val b1gk = SPLINE_1_GAUSS_POINTS[k];
      val b2gk = SPLINE_2_GAUSS_POINTS[k];
      val b3gk = SPLINE_3_GAUSS_POINTS[k];

      for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
        val wkl = GAUSS_POINTS_WEIGHTS_MULTIPLIED[k * GAUSS_POINT_COUNT + l];
        val gl = GAUSS_POINTS[l];

        val yf = gl * dy;
        val vf = wkl * SPLINE_2_GAUSS_POINTS[l] * problem.valueAt(x, yf);
        ds.add(0, 1, b3gk * vf);
        ds.add(1, 1, b2gk * vf);
        ds.add(2, 1, b1gk * vf);

        val ys = (gl + 1) * dy;
        val vs = wkl * SPLINE_3_GAUSS_POINTS[l] * problem.valueAt(x, ys);
        ds.add(0, 1, b3gk * vs);
        ds.add(1, 1, b2gk * vs);
        ds.add(2, 1, b1gk * vs);
      }
    }
  }

  private void initializeCenter(Problem problem, IgaVertex vertex, PrimitiveDenseStore ds) {
    val dx = mesh.getDx();
    val dy = mesh.getDy();
    val leftSegment = vertex.getLeftSegment();
    for (int i = 2; i < mesh.getElementsY(); i++) {
      for (int k = 0; k < GAUSS_POINT_COUNT; k++) {
        val x = GAUSS_POINTS[k] * dx + leftSegment;
        val b1gk = SPLINE_1_GAUSS_POINTS[k];
        val b2gk = SPLINE_2_GAUSS_POINTS[k];
        val b3gk = SPLINE_3_GAUSS_POINTS[k];

        for (int l = 0; l < GAUSS_POINT_COUNT; l++) {
          val wkl = GAUSS_POINTS_WEIGHTS_MULTIPLIED[k * GAUSS_POINT_COUNT + l];
          val gl = GAUSS_POINTS[l];

          val yl = (gl + (i - 2)) * dy;
          val vl = wkl * SPLINE_1_GAUSS_POINTS[l] * problem.valueAt(x, yl);

          ds.add(0, i, b3gk * vl);
          ds.add(1, i, b2gk * vl);
          ds.add(2, i, b1gk * vl);

          val ym = (gl + (i - 1)) * dy;
          val vm = wkl * SPLINE_2_GAUSS_POINTS[l] * problem.valueAt(x, ym);
          ds.add(0, i, b3gk * vm);
          ds.add(1, i, b2gk * vm);
          ds.add(2, i, b1gk * vm);

          val yr = (gl + i) * dy;
          val vr = wkl * SPLINE_3_GAUSS_POINTS[l] * problem.valueAt(x, yr);
          ds.add(0, i, b3gk * vr);
          ds.add(1, i, b2gk * vr);
          ds.add(2, i, b1gk * vr);
        }
      }
    }
  }

}
