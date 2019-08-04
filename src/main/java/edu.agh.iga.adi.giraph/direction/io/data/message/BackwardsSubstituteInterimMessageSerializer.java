package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class BackwardsSubstituteInterimMessageSerializer implements MessageSerializer<BackwardsSubstituteInterimMessage> {

  static final BackwardsSubstituteInterimMessageSerializer BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE_SERIALIZER
      = new BackwardsSubstituteInterimMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteInterimMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeLong(message.getDstId());
  }

  @Override
  public BackwardsSubstituteInterimMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dstId = dataInput.readLong();
    return new BackwardsSubstituteInterimMessage(srcId, dstId);
  }

}
