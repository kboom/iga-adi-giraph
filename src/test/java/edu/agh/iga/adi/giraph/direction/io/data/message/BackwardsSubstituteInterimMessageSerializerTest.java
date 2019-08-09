package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class BackwardsSubstituteInterimMessageSerializerTest extends MessageSerializerTest<BackwardsSubstituteInterimMessage> {

  BackwardsSubstituteInterimMessageSerializerTest() {
    super(new BackwardsSubstituteInterimMessageSerializer());
  }

  @Override
  BackwardsSubstituteInterimMessage createMessage() {
    return new BackwardsSubstituteInterimMessage(
        BRANCH_ID,
        FACTORY.makeFilled(4, 14, new Uniform())
    );
  }

}