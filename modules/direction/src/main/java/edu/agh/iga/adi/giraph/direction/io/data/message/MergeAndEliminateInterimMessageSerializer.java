package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;
import lombok.val;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateInterimMessageSerializer implements MessageSerializer<MergeAndEliminateInterimMessage> {

  static final MergeAndEliminateInterimMessageSerializer MERGE_AND_ELIMINATE_INTERIM_MESSAGE_SERIALIZER
      = new MergeAndEliminateInterimMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;
  private static final int CONTRIBUTED_COLS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateInterimMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mb.countColumns());
    message.ma.asCollectable1D().supplyTo(receiveInto(dataOutput));
    message.mb.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public MergeAndEliminateInterimMessage readMessage(DataInput dataInput) throws IOException {
    val srcId = dataInput.readLong();
    val dofs = dataInput.readInt();
    val ma = FACTORY.makeZero(CONTRIBUTED_ROWS, CONTRIBUTED_COLS);
    ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    val mb = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new MergeAndEliminateInterimMessage(srcId, ma, mb);
  }

  @Override
  public MergeAndEliminateInterimMessage readMessage(MergeAndEliminateInterimMessage message, DataInput dataInput) throws IOException {
    val srcId = dataInput.readLong();
    val dofs = dataInput.readInt();
    message.ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    message.mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    message.reattach(srcId);
    return message;
  }

}
