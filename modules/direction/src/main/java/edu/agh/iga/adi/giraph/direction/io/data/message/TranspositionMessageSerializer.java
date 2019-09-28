package edu.agh.iga.adi.giraph.direction.io.data.message;

import lombok.val;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.core.operations.setup.TranspositionIgaOperation.TranspositionIgaMessage;
import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

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
    val srcId = dataInput.readLong();
    val rows = dataInput.readInt();
    val cols = dataInput.readInt();
    val x = FACTORY.makeZero(rows, cols);
    x.fillMatching(dataInputAccessStore(dataInput, rows * cols));
    return new TranspositionIgaMessage(srcId, x);
  }

  @Override
  public TranspositionIgaMessage readMessage(TranspositionIgaMessage message, DataInput dataInput) throws IOException {
    val srcId = dataInput.readLong();
    val rows = dataInput.readInt();
    val cols = dataInput.readInt();

    // the first leaf has 5 rows while others have only 3
    if (message.mxp.countRows() == rows) {
      message.reattach(srcId);
      message.mxp.fillMatching(dataInputAccessStore(dataInput, rows * cols));
      return message;
    } else {
      val x = FACTORY.makeZero(rows, cols);
      x.fillMatching(dataInputAccessStore(dataInput, rows * cols));
      return new TranspositionIgaMessage(srcId, x);
    }
  }

}
