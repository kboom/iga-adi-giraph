package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

final class BackwardsSubstituteBranchMessageSerializer implements MessageSerializer<BackwardsSubstituteBranchMessage> {

  static final BackwardsSubstituteBranchMessageSerializer BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE_SERIALIZER
      = new BackwardsSubstituteBranchMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteBranchMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mx.countColumns());
    message.mx.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public BackwardsSubstituteBranchMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dofs = dataInput.readInt();
    PrimitiveDenseStore ds = PrimitiveDenseStore.FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    ds.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new BackwardsSubstituteBranchMessage(srcId, ds);
  }

}