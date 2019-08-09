package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateInterimMessageSerializerTest extends MessageSerializerTest<MergeAndEliminateInterimMessage> {

  MergeAndEliminateInterimMessageSerializerTest() {
    super(new MergeAndEliminateInterimMessageSerializer());
  }

  @Override
  MergeAndEliminateInterimMessage createMessage() {
    return new MergeAndEliminateInterimMessage(
        BRANCH_ID,
        FACTORY.makeFilled(4, 4, new Uniform()),
        FACTORY.makeFilled(4, 14, new Uniform())
    );
  }

}