package edu.agh.iga.adi.giraph.direction.io.data;

import org.junit.jupiter.api.Test;
import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.netio.BasicLogger;
import org.ojalgo.structure.Access2D;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static com.google.common.io.ByteStreams.newDataInput;
import static com.google.common.io.ByteStreams.newDataOutput;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ojalgo.array.Array2D.DIRECT32;

class DataOutputReceiverTest {

  @Test
  void canReceiveData() {
    PrimitiveDenseStore ds = PrimitiveDenseStore.FACTORY.makeZero(2, 2);
    ds.add(0, 0, 1);
    ds.add(0, 1, 2);
    ds.add(1, 0, 3);
    ds.add(1, 1, 4);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ds.asCollectable1D().supplyTo(receiveInto(newDataOutput(baos)));
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

    PrimitiveDenseStore out = PrimitiveDenseStore.FACTORY.makeZero(2, 2);
    out.fillMatching(dataInputAccessStore(newDataInput(bais), 4));
    assertThat(out.data).containsExactly(1, 3, 2, 4);

    BasicLogger.debug("Arr", out);
  }

  @Test
  void somethingElse() {
    PrimitiveDenseStore out = PrimitiveDenseStore.FACTORY.copy(new Access2D<Number>() {
      @Override
      public double doubleValue(long row, long col) {
        return 0;
      }

      @Override
      public Number get(long row, long col) {
        return null;
      }

      @Override
      public long countColumns() {
        return 0;
      }

      @Override
      public long countRows() {
        return 0;
      }
    });
  }

  @Test
  void somethingElse2() {
    MatrixStore.Factory<Double> out = PrimitiveDenseStore.FACTORY.builder();
  }

}