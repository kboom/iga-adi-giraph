package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateRootMessageSerializerTest extends MessageSerializerTest<MergeAndEliminateRootMessage> {

  MergeAndEliminateRootMessageSerializerTest() {
    super(new MergeAndEliminateRootMessageSerializer());
  }

  @Override
  MergeAndEliminateRootMessage createMessage() {
    return new MergeAndEliminateRootMessage(
        BRANCH_ID,
        FACTORY.makeFilled(4, 4, new Uniform()),
        FACTORY.makeFilled(4, 14, new Uniform())
    );
  }

}