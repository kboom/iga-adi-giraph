package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;

final class MergeAndEliminateLeavesMessageSerializer implements MessageSerializer<MergeAndEliminateLeavesMessage> {

  static final MergeAndEliminateLeavesMessageSerializer MERGE_AND_ELIMINATE_LEAVES_MESSAGE_SERIALIZER
      = new MergeAndEliminateLeavesMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateLeavesMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    message.ma.asCollectable1D().supplyTo(receiveInto(dataOutput));
    message.mb.asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public MergeAndEliminateLeavesMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    return new MergeAndEliminateLeavesMessage(srcId, null, null);
  }

}
