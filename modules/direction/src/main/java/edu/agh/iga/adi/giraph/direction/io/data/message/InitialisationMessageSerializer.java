package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.setup.Initialisation.InitialisationIgaMessage;
import lombok.val;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class InitialisationMessageSerializer implements MessageSerializer<InitialisationIgaMessage> {

  static final InitialisationMessageSerializer INITIALISATION_MESSAGE_SERIALIZER
      = new InitialisationMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, InitialisationIgaMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeInt((int) message.getMxp().countRows());
    dataOutput.writeInt((int) message.getMxp().countColumns());
    message.getMxp().asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public InitialisationIgaMessage readMessage(DataInput dataInput) throws IOException {
    val srcId = dataInput.readLong();
    val rows = dataInput.readInt();
    val cols = dataInput.readInt();
    val x = FACTORY.makeZero(rows, cols);
    x.fillMatching(dataInputAccessStore(dataInput, rows * cols));
    return new InitialisationIgaMessage(srcId, -1, x);
  }

  @Override
  public InitialisationIgaMessage readMessage(InitialisationIgaMessage message, DataInput dataInput) throws IOException {
    return readMessage(dataInput);
//    val srcId = dataInput.readLong();
//    val rows = dataInput.readInt();
//    val cols = dataInput.readInt();
//
//    if (message.getMxp().countRows() < rows) {
//      val x = FACTORY.makeZero(rows, cols);
//      x.fillMatching(dataInputAccessStore(dataInput, rows * cols));
//      return new InitialisationIgaMessage(srcId, -1, x);
//    } else {
//      message
//          .withRows(rows) // ensure the client knows that we are using matrix with potentially empty row
//          .reattach(srcId);
//
//      message.getMxp().fillMatching(dataInputAccessStore(dataInput, rows * cols));
//      return message;
//    }
  }

}
