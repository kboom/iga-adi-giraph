package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.core.IgaConstants.COLS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

final class MergeAndEliminateLeavesMessageSerializer implements MessageSerializer<MergeAndEliminateLeavesMessage> {

  static final MergeAndEliminateLeavesMessageSerializer MERGE_AND_ELIMINATE_LEAVES_MESSAGE_SERIALIZER
      = new MergeAndEliminateLeavesMessageSerializer();

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
    PrimitiveDenseStore ma = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE);
    ma.fillMatching(dataInputAccessStore(dataInput, ROWS_BOUND_TO_NODE * COLS_BOUND_TO_NODE));
    PrimitiveDenseStore mb = PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, ROWS_BOUND_TO_NODE * dofs));
    return new MergeAndEliminateLeavesMessage(srcId, ma, mb);
  }

}
