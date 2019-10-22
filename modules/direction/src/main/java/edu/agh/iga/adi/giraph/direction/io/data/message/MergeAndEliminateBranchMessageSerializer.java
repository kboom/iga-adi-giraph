package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;
import lombok.val;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static edu.agh.iga.adi.giraph.direction.io.data.DataInputAccessStore.dataInputAccessStore;
import static edu.agh.iga.adi.giraph.direction.io.data.DataOutputReceiver.receiveInto;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateBranchMessageSerializer implements MessageSerializer<MergeAndEliminateBranchMessage> {

  static final MergeAndEliminateBranchMessageSerializer MERGE_AND_ELIMINATE_BRANCH_MESSAGE_SERIALIZER
      = new MergeAndEliminateBranchMessageSerializer();

  private static final int CONTRIBUTED_ROWS = 4;
  private static final int CONTRIBUTED_COLS = 4;

  @Override
  public void writeMessage(DataOutput dataOutput, MergeAndEliminateBranchMessage message) throws IOException {
    dataOutput.writeInt(message.getSrcId());
    dataOutput.writeInt((int) message.getMb().countColumns());
    message.getMa().asCollectable1D().supplyTo(receiveInto(dataOutput));
    message.getMb().asCollectable1D().supplyTo(receiveInto(dataOutput));
  }

  @Override
  public MergeAndEliminateBranchMessage readMessage(DataInput dataInput) throws IOException {
    val srcId = dataInput.readInt();
    val dofs = dataInput.readInt();
    PrimitiveDenseStore ma = FACTORY.makeZero(CONTRIBUTED_ROWS, CONTRIBUTED_COLS);
    ma.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    PrimitiveDenseStore mb = FACTORY.makeZero(CONTRIBUTED_ROWS, dofs);
    mb.fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    return new MergeAndEliminateBranchMessage(srcId, ma, mb);
  }

  @Override
  public MergeAndEliminateBranchMessage readMessage(MergeAndEliminateBranchMessage message, DataInput dataInput) throws IOException {
    val srcId = dataInput.readInt();
    val dofs = dataInput.readInt();
    message.getMa().fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * CONTRIBUTED_COLS));
    message.getMb().fillMatching(dataInputAccessStore(dataInput, CONTRIBUTED_ROWS * dofs));
    message.reattach(srcId);
    return message;
  }

}
