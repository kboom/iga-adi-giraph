package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class BackwardsSubstituteRootMessageSerializer implements MessageSerializer<BackwardsSubstituteRootMessage> {

  static final BackwardsSubstituteRootMessageSerializer BACKWARDS_SUBSTITUTE_ROOT_MESSAGE_SERIALIZER
      = new BackwardsSubstituteRootMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteRootMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeLong(message.getDstId());
  }

  @Override
  public BackwardsSubstituteRootMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dstId = dataInput.readLong();
    return new BackwardsSubstituteRootMessage(srcId, dstId);
  }

}
