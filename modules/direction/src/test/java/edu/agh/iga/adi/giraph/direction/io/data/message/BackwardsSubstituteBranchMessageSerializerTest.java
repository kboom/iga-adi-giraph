package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class BackwardsSubstituteBranchMessageSerializerTest extends MessageSerializerTest<BackwardsSubstituteBranchMessage> {

  BackwardsSubstituteBranchMessageSerializerTest() {
    super(new BackwardsSubstituteBranchMessageSerializer());
  }

  @Override
  BackwardsSubstituteBranchMessage createMessage() {
    return new BackwardsSubstituteBranchMessage(
        BRANCH_ID,
        FACTORY.makeFilled(4, 14, new Uniform())
    );
  }

}