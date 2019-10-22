package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import lombok.val;

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
    dataOutput.writeInt(message.getSrcId());
    dataOutput.writeInt((int) message.mx.countColumns());
    message.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public BackwardsSubstituteInterimMessage readMessage(DataInput dataInput) throws IOException {
    val srcId = dataInput.readInt();
    val dofs = dataInput.readInt();
    val ds = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    ds.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new BackwardsSubstituteInterimMessage(srcId, ds);
  }

  @Override
  public BackwardsSubstituteInterimMessage readMessage(BackwardsSubstituteInterimMessage message, DataInput dataInput) throws IOException {
    val srcId = dataInput.readInt();
    val dofs = dataInput.readInt();
    message.reattach(srcId);
    message.mx.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return  message;
  }

}
