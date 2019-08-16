package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

final class MergeAndEliminateLeavesMessageSerializer implements MessageSerializer<MergeAndEliminateLeavesMessage> {

  static final MergeAndEliminateLeavesMessageSerializer MERGE_AND_ELIMINATE_LEAVES_MESSAGE_SERIALIZER
      = new MergeAndEliminateLeavesMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 3;
  private static final int CONTRIBUTED_COLS = 3;

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateLeavesMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mb.countColumns());
    message.ma.asCollectable1D().supplyTo(receiveInto(dataOutput));
    message.mb.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public MergeAndEliminateLeavesMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dofs = dataInput.readInt();
    PrimitiveDenseStore ma = PrimitiveDenseStore.FACTORY.makeZero(CONTRIBUTED_ROWS, CONTRIBUTED_COLS);
    ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    PrimitiveDenseStore mb = PrimitiveDenseStore.FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new MergeAndEliminateLeavesMessage(srcId, ma, mb);
  }

}
