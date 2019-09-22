package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

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
    final long srcId = dataInput.readLong();
    final long dofs = dataInput.readInt();
    PrimitiveDenseStore ma = FACTORY.makeZero(CONTRIBUTED_ROWS, CONTRIBUTED_COLS);
    ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    PrimitiveDenseStore mb = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new MergeAndEliminateRootMessage(srcId, ma, mb);
  }

}
