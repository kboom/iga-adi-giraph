package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.direction.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class BackwardsSubstituteRootMessageSerializerTest extends MessageSerializerTest<BackwardsSubstituteRootMessage> {

  BackwardsSubstituteRootMessageSerializerTest() {
    super(new BackwardsSubstituteRootMessageSerializer());
  }

  @Override
  BackwardsSubstituteRootMessage createMessage() {
    return new BackwardsSubstituteRootMessage(
        BRANCH_ID,
        FACTORY.makeFilled(4, 14, new Uniform())
    );
  }

}