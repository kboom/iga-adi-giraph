package edu.agh.iga.adi.giraph.core;

import edu.agh.iga.adi.giraph.core.splines.BSpline1;
import edu.agh.iga.adi.giraph.core.splines.BSpline2;
import edu.agh.iga.adi.giraph.core.splines.BSpline3;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class GaussPoints {

  private static final BSpline1 bs1 = new BSpline1();
  private static final BSpline2 bs2 = new BSpline2();
  private static final BSpline3 bs3 = new BSpline3();

  public static final double[] GAUSS_POINTS;
  public static final double[] GAUSS_POINT_WEIGHTS;
  public static final double[] GAUSS_POINTS_WEIGHTS_MULTIPLIED;
  public static final int GAUSS_POINT_COUNT;

  public static final double[] SPLINE_1_GAUSS_POINTS;
  public static final double[] SPLINE_2_GAUSS_POINTS;
  public static final double[] SPLINE_3_GAUSS_POINTS;

  static {
    GAUSS_POINTS = new double[10];
    GAUSS_POINT_WEIGHTS = new double[10];
    GAUSS_POINT_COUNT = 10;
    GAUSS_POINTS[0] = (1.0 - 0.973906528517171720077964) * 0.5;
    GAUSS_POINTS[1] = (1.0 - 0.8650633666889845107320967) * 0.5;
    GAUSS_POINTS[2] = (1.0 - 0.6794095682990244062343274) * 0.5;
    GAUSS_POINTS[3] = (1.0 - 0.4333953941292471907992659) * 0.5;
    GAUSS_POINTS[4] = (1.0 - 0.1488743389816312108848260) * 0.5;
    GAUSS_POINTS[5] = (1.0 + 0.1488743389816312108848260) * 0.5;
    GAUSS_POINTS[6] = (1.0 + 0.4333953941292471907992659) * 0.5;
    GAUSS_POINTS[7] = (1.0 + 0.6794095682990244062343274) * 0.5;
    GAUSS_POINTS[8] = (1.0 + 0.8650633666889845107320967) * 0.5;
    GAUSS_POINTS[9] = (1.0 + 0.9739065285171717200779640) * 0.5;


    GAUSS_POINT_WEIGHTS[0] = 0.0666713443086881375935688 * 0.5;
    GAUSS_POINT_WEIGHTS[1] = 0.1494513491505805931457763 * 0.5;
    GAUSS_POINT_WEIGHTS[2] = 0.2190863625159820439955349 * 0.5;
    GAUSS_POINT_WEIGHTS[3] = 0.2692667193099963550912269 * 0.5;
    GAUSS_POINT_WEIGHTS[4] = 0.2955242247147528701738930 * 0.5;
    GAUSS_POINT_WEIGHTS[5] = 0.2955242247147528701738930 * 0.5;
    GAUSS_POINT_WEIGHTS[6] = 0.2692667193099963550912269 * 0.5;
    GAUSS_POINT_WEIGHTS[7] = 0.2190863625159820439955349 * 0.5;
    GAUSS_POINT_WEIGHTS[8] = 0.1494513491505805931457763 * 0.5;
    GAUSS_POINT_WEIGHTS[9] = 0.0666713443086881375935688 * 0.5;

    GAUSS_POINTS_WEIGHTS_MULTIPLIED = new double[GAUSS_POINT_WEIGHTS.length * GAUSS_POINT_WEIGHTS.length];
    for (int i = 0; i < GAUSS_POINT_WEIGHTS.length; i++) {
      for (int j = 0; j < GAUSS_POINT_WEIGHTS.length; j++) {
        GAUSS_POINTS_WEIGHTS_MULTIPLIED[GAUSS_POINT_WEIGHTS.length * i + j] =
            GAUSS_POINT_WEIGHTS[i] * GAUSS_POINT_WEIGHTS[j];
      }
    }

    SPLINE_1_GAUSS_POINTS = Arrays.stream(GAUSS_POINTS).map(bs1::getValue).toArray();
    SPLINE_2_GAUSS_POINTS = Arrays.stream(GAUSS_POINTS).map(bs2::getValue).toArray();
    SPLINE_3_GAUSS_POINTS = Arrays.stream(GAUSS_POINTS).map(bs3::getValue).toArray();


  }

}
