package edu.agh.iga.adi.giraph.direction.io.data;

import com.google.common.io.ByteArrayDataOutput;
import org.junit.jupiter.api.Test;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.netio.BasicLogger;

import static com.google.common.io.ByteStreams.newDataInput;
import static com.google.common.io.ByteStreams.newDataOutput;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.assertj.core.api.Assertions.assertThat;

class DataOutputReceiverTest {

  @Test
  void canReceiveData() {
    PrimitiveDenseStore ds = PrimitiveDenseStore.FACTORY.makeZero(2, 2);
    ds.add(0, 0, 1);
    ds.add(0, 1, 2);
    ds.add(1, 0, 3);
    ds.add(1, 1, 4);

    ByteArrayDataOutput dataOutput = newDataOutput();
    ds.asCollectable1D().supplyTo(receiveInto(dataOutput));

    PrimitiveDenseStore out = PrimitiveDenseStore.FACTORY.makeZero(2, 2);
    out.fillMatching(dataInputAccessStore(newDataInput(dataOutput.toByteArray()), 4));
    assertThat(out.data).containsExactly(1, 3, 2, 4);

    BasicLogger.debug("Arr", out);
  }

}