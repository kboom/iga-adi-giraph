package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class MergeAndEliminateRootMessageSerializer implements MessageSerializer<MergeAndEliminateRootMessage> {

  static final MergeAndEliminateRootMessageSerializer MERGE_AND_ELIMINATE_ROOT_MESSAGE_SERIALIZER
      = new MergeAndEliminateRootMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateRootMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
  }

  @Override
  public MergeAndEliminateRootMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    return new MergeAndEliminateRootMessage(srcId);
  }

}
