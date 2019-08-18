package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.direction.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateBranchMessageSerializerTest extends MessageSerializerTest<MergeAndEliminateBranchMessage> {

  MergeAndEliminateBranchMessageSerializerTest() {
    super(new MergeAndEliminateBranchMessageSerializer());
  }

  @Override
  MergeAndEliminateBranchMessage createMessage() {
    return new MergeAndEliminateBranchMessage(
        BRANCH_ID,
        FACTORY.makeFilled(4, 4, new Uniform()),
        FACTORY.makeFilled(4, 14, new Uniform())
    );
  }

}