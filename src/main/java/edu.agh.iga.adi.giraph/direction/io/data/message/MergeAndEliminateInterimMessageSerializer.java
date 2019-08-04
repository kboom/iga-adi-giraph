package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class MergeAndEliminateInterimMessageSerializer implements MessageSerializer<MergeAndEliminateInterimMessage> {

  static final MergeAndEliminateInterimMessageSerializer MERGE_AND_ELIMINATE_INTERIM_MESSAGE_SERIALIZER
      = new MergeAndEliminateInterimMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateInterimMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
    dataOutput.writeLong(message.getDstId());
  }

  @Override
  public MergeAndEliminateInterimMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    final long dstId = dataInput.readLong();
    return new MergeAndEliminateInterimMessage(srcId, dstId);
  }

}
