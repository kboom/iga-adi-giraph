package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class BackwardsSubstituteInterimMessageSerializer implements MessageSerializer<BackwardsSubstituteInterimMessage> {

  static final BackwardsSubstituteInterimMessageSerializer BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE_SERIALIZER
      = new BackwardsSubstituteInterimMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteInterimMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mx.countColumns());
    message.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public BackwardsSubstituteInterimMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dofs = dataInput.readInt();
    PrimitiveDenseStore ds = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    ds.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new BackwardsSubstituteInterimMessage(srcId, ds);
  }

}
