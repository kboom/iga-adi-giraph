package edu.agh.iga.adi.giraph.direction.io.data.message;

import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.core.operations.setup.TranspositionIgaOperation.TranspositionIgaMessage;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

final class TranspositionMessageSerializer implements MessageSerializer<TranspositionIgaMessage> {

  static final TranspositionMessageSerializer TRANSPOSITION_MESSAGE_SERIALIZER
      = new TranspositionMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, TranspositionIgaMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.mxp.countRows());
    dataOutput.writeInt((int) message.mxp.countColumns());
    message.mxp.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public TranspositionIgaMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long rows = dataInput.readInt();
    final long cols = dataInput.readInt();
    PrimitiveDenseStore x = PrimitiveDenseStore.FACTORY.makeZero(rows, cols);
    x.fillMatching(dataInputAccessStore(dataInput, rows * cols));
    return new TranspositionIgaMessage(srcId, x);
  }

}
