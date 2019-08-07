package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class MergeAndEliminateBranchMessageSerializer implements MessageSerializer<MergeAndEliminateBranchMessage> {

  static final MergeAndEliminateBranchMessageSerializer MERGE_AND_ELIMINATE_BRANCH_MESSAGE_SERIALIZER
      = new MergeAndEliminateBranchMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateBranchMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
  }

  @Override
  public MergeAndEliminateBranchMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    return new MergeAndEliminateBranchMessage(srcId);
  }

}
