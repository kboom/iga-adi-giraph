package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

final class BackwardsSubstituteRootMessageSerializer implements MessageSerializer<BackwardsSubstituteRootMessage> {

  static final BackwardsSubstituteRootMessageSerializer BACKWARDS_SUBSTITUTE_ROOT_MESSAGE_SERIALIZER
      = new BackwardsSubstituteRootMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteRootMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mx.countColumns());
    message.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public BackwardsSubstituteRootMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dofs = dataInput.readInt();
    PrimitiveDenseStore ds = PrimitiveDenseStore.FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    ds.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new BackwardsSubstituteRootMessage(srcId, ds);
  }

}