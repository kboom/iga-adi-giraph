package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;
import lombok.val;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateRootMessageSerializer implements MessageSerializer<MergeAndEliminateRootMessage> {

  static final MergeAndEliminateRootMessageSerializer MERGE_AND_ELIMINATE_ROOT_MESSAGE_SERIALIZER
      = new MergeAndEliminateRootMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;
  private static final int CONTRIBUTED_COLS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateRootMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mb.countColumns());
    message.ma.asCollectable1D().supplyTo(receiveInto(dataOutput));
    message.mb.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public MergeAndEliminateRootMessage readMessage(DataInput dataInput) throws IOException {
    val srcId = dataInput.readLong();
    val dofs = dataInput.readInt();
    val ma = FACTORY.makeZero(CONTRIBUTED_ROWS, CONTRIBUTED_COLS);
    ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    val mb = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new MergeAndEliminateRootMessage(srcId, ma, mb);
  }

  @Override
  public MergeAndEliminateRootMessage readMessage(MergeAndEliminateRootMessage message, DataInput dataInput) throws IOException {
    val srcId = dataInput.readLong();
    val dofs = dataInput.readInt();
    message.ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    message.mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    message.reattach(srcId);
    return message;
  }

}
