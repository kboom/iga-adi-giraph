package edu.agh.iga.adi.giraph;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.ojalgo.array.Array2D;
import org.ojalgo.array.BufferArray;
import org.ojalgo.function.aggregator.Aggregator;
import org.ojalgo.function.constant.PrimitiveMath;
import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.netio.BasicLogger;

import static org.assertj.core.api.Assertions.assertThat;

class MatrixTest {

  @Test
  void addsWithCreatingNew() {
    MatrixStore<Double> b1 = MatrixStore.PRIMITIVE.makeZero(5, 5).operateOnAll(10D, PrimitiveMath.ADD).get();
    MatrixStore<Double> b2 = MatrixStore.PRIMITIVE.makeZero(10, 10).operateOnAll(-5D, PrimitiveMath.SUBTRACT).get();


    MatrixStore<Double> b3 = b2
        .logical()
        .column(2, 3, 4)
        .row(2, 3, 4)
        .operateOnMatching(b2, PrimitiveMath.ADD)
        .get();

    assertThat(b3.aggregateAll(Aggregator.SMALLEST)).isEqualTo(10);
    BasicLogger.debug("Arr", b3);
  }

  @Test
  void addsInPlace() {
    PrimitiveDenseStore m1 = PrimitiveDenseStore.FACTORY.makeZero(10, 10);
    PrimitiveDenseStore m2 = PrimitiveDenseStore.FACTORY.makeZero(3, 3);

    m1.add(0, 0, 999D);
    m1.add(4, 4, -100D);

    m2.add(0, 0, 5D);
    m2.add(1, 1, 2D);
    m2.add(1, 2, 3D);
    m2.add(2, 1, -10D);

    m1
        .regionByColumns(4, 5, 6)
        .regionByRows(4, 5, 6)
        .modifyMatching(PrimitiveMath.ADD, m2);

    assertThat(m1.aggregateAll(Aggregator.MINIMUM)).isEqualTo(-95);
    BasicLogger.debug("Arr", m1);
  }

  @Test
  void modifiesUsingOffsets() {
    PrimitiveDenseStore m1 = PrimitiveDenseStore.FACTORY.makeZero(10, 10);
    PrimitiveDenseStore m2 = PrimitiveDenseStore.FACTORY.makeZero(3, 3);

    m2.add(0, 0, 5D);
    m2.add(1, 1, 2D);
    m2.add(1, 2, 3D);
    m2.add(2, 1, -10D);

    m1
        .regionByOffsets(2, 2)
        .regionByLimits(2, 2)
        .fillMatching(m1, PrimitiveMath.ADD, m2);

    AssertionsForClassTypes.assertThat(m1.aggregateAll(Aggregator.MINIMUM)).isEqualTo(-10);
  }

  @Test
  void directBuffer() {
    Array2D.factory(BufferArray.DIRECT32)
        .makeZero(1_000, 1_000);

  }

}
