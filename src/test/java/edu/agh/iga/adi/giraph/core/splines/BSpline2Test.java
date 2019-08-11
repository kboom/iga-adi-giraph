package edu.agh.iga.adi.giraph.core.splines;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

final class BSpline2Test {

    private static final BSpline2 spline2 = new BSpline2();

    @Test
    void returnsSplineValueInStartPointOfDomain() {
        assertThat(spline2.getFunctionValue(0d)).isEqualTo(0.5d);
    }

    @Test
    void returnsSplineValueInEndPointOfDomain() {
        assertThat(spline2.getFunctionValue(1d)).isEqualTo(0.5d);
    }

    @Test
    void returnsSplineValueInCenterOfDomain() {
        assertThat(spline2.getFunctionValue(0.5d)).isEqualTo(0.75d);
    }

    @Test
    void returnsValidDerivativeInTheMiddle() {
        assertThat(spline2.getFirstDerivativeValueAt(0.5d)).isEqualTo(0);
    }

    @Test
    void returnsValidDerivativeAtStart() {
        assertThat(spline2.getFirstDerivativeValueAt(0d)).isEqualTo(1d);
    }

    @Test
    void returnsValidDerivativeAtEnd() {
        assertThat(spline2.getFirstDerivativeValueAt(1d)).isEqualTo(-1d);
    }

    @Test
    void returnsZeroDerivativeLeftOfDomain() {
        assertThat(spline2.getFirstDerivativeValueAt(-0.1d)).isEqualTo(0);
    }

    @Test
    void returnsZeroDerivativeRightOfDomain() {
        assertThat(spline2.getFirstDerivativeValueAt(1.1d)).isEqualTo(0);
    }

    @Test
    void returnsValidSecondDerivativeInsideTheDomain() {
        assertThat(spline2.getSecondDerivativeValueAt(0.5d)).isEqualTo(-2);
    }

    @Test
    void returnsZeroSecondDerivativeLeftToTheDomain() {
        assertThat(spline2.getSecondDerivativeValueAt(-1)).isEqualTo(0);
    }

    @Test
    void returnsZeroSecondDerivativeRightToTheDomain() {
        assertThat(spline2.getSecondDerivativeValueAt(1.1)).isEqualTo(0);
    }

}