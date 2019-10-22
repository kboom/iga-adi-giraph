package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;
import lombok.val;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class BackwardsSubstituteRootMessageSerializer implements MessageSerializer<BackwardsSubstituteRootMessage> {

  static final BackwardsSubstituteRootMessageSerializer BACKWARDS_SUBSTITUTE_ROOT_MESSAGE_SERIALIZER
      = new BackwardsSubstituteRootMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteRootMessage message) throws IOException {
    dataOutput.writeInt(message.getSrcId());
    dataOutput.writeInt((int) message.mx.countColumns());
    message.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public BackwardsSubstituteRootMessage readMessage(DataInput dataInput) throws IOException {
    val srcId = dataInput.readInt();
    val dofs = dataInput.readInt();
    PrimitiveDenseStore ds = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    ds.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new BackwardsSubstituteRootMessage(srcId, ds);
  }

  @Override
  public BackwardsSubstituteRootMessage readMessage(BackwardsSubstituteRootMessage message, DataInput dataInput) throws IOException {
    val srcId = dataInput.readInt();
    val dofs = dataInput.readInt();
    message.mx.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    message.reattach(srcId);
    return message;
  }

}
