package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class BackwardsSubstituteBranchMessageSerializer implements MessageSerializer<BackwardsSubstituteBranchMessage> {

  static final BackwardsSubstituteBranchMessageSerializer BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE_SERIALIZER
      = new BackwardsSubstituteBranchMessageSerializer();

  @Override
  public void writeMessage(DataOutput dataOutput, BackwardsSubstituteBranchMessage message) throws IOException {
    dataOutput.writeLong(message.getSrcId());
  }

  @Override
  public BackwardsSubstituteBranchMessage readMessage(DataInput dataInput) throws IOException {
    final long srcId = dataInput.readLong();
    return new BackwardsSubstituteBranchMessage(srcId);
  }

}
