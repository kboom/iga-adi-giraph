package edu.agh.iga.adi.giraph.core.splines;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

final class BSpline1Test {

    private static final BSpline1 spline1 = new BSpline1();

    @Test
    void returnsSplineValueInStartPointOfDomain() {
        assertThat(spline1.getFunctionValue(0d)).isEqualTo(0d);
    }

    @Test
    void returnsSplineValueInEndPointOfDomain() {
        assertThat(spline1.getFunctionValue(1d)).isEqualTo(0.5d);
    }

    @Test
    void returnsSplineValueInCenterOfDomain() {
        assertThat(spline1.getFunctionValue(0.5d)).isEqualTo(0.125d);
    }

    @Test
    void returnsValidDerivativeInTheMiddle() {
        assertThat(spline1.getFirstDerivativeValueAt(0.5d)).isEqualTo(0.5d);
    }

    @Test
    void returnsValidDerivativeAtStart() {
        assertThat(spline1.getFirstDerivativeValueAt(0d)).isEqualTo(0d);
    }

    @Test
    void returnsValidDerivativeAtEnd() {
        assertThat(spline1.getFirstDerivativeValueAt(1d)).isEqualTo(1d);
    }

    @Test
    void returnsZeroDerivativeLeftOfDomain() {
        assertThat(spline1.getFirstDerivativeValueAt(-0.1d)).isEqualTo(0);
    }

    @Test
    void returnsZeroDerivativeRightOfDomain() {
        assertThat(spline1.getFirstDerivativeValueAt(1.1d)).isEqualTo(0);
    }

    @Test
    void returnsValidSecondDerivativeInsideTheDomain() {
        assertThat(spline1.getSecondDerivativeValueAt(0.5d)).isEqualTo(1);
    }

    @Test
    void returnsZeroSecondDerivativeLeftToTheDomain() {
        assertThat(spline1.getSecondDerivativeValueAt(-1)).isEqualTo(0);
    }

    @Test
    void returnsZeroSecondDerivativeRightToTheDomain() {
        assertThat(spline1.getSecondDerivativeValueAt(1.1)).isEqualTo(0);
    }

}
