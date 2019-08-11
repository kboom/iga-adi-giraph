package edu.agh.iga.adi.giraph.core.splines;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

final class BSpline3Test {

    private static final BSpline3 spline3 = new BSpline3();

    @Test
    void returnsSplineValueInStartPointOfDomain() {
        assertThat(spline3.getFunctionValue(0d)).isEqualTo(0.5d);
    }

    @Test
    void returnsSplineValueInEndPointOfDomain() {
        assertThat(spline3.getFunctionValue(1d)).isEqualTo(0d);
    }

    @Test
    void returnsSplineValueInCenterOfDomain() {
        assertThat(spline3.getFunctionValue(0.5d)).isEqualTo(0.125d);
    }

    @Test
    void returnsValidDerivativeInCenter() {
        assertThat(spline3.getFirstDerivativeValueAt(0.5d)).isEqualTo(-0.5d);
    }

    @Test
    void returnsValidDerivativeAtStart() {
        assertThat(spline3.getFirstDerivativeValueAt(0d)).isEqualTo(-1d);
    }

    @Test
    void returnsValidDerivativeAtEnd() {
        assertThat(spline3.getFirstDerivativeValueAt(1d)).isEqualTo(0d);
    }

    @Test
    void returnsZeroDerivativeLeftOfDomain() {
        assertThat(spline3.getFirstDerivativeValueAt(-0.1d)).isEqualTo(0);
    }

    @Test
    void returnsZeroDerivativeRightOfDomain() {
        assertThat(spline3.getFirstDerivativeValueAt(1.1d)).isEqualTo(0);
    }

    @Test
    void returnsValidSecondDerivativeInsideTheDomain() {
        assertThat(spline3.getSecondDerivativeValueAt(0.5d)).isEqualTo(1);
    }

    @Test
    void returnsZeroSecondDerivativeLeftToTheDomain() {
        assertThat(spline3.getSecondDerivativeValueAt(-1)).isEqualTo(0);
    }

    @Test
    void returnsZeroSecondDerivativeRightToTheDomain() {
        assertThat(spline3.getSecondDerivativeValueAt(1.1)).isEqualTo(0);
    }
}
