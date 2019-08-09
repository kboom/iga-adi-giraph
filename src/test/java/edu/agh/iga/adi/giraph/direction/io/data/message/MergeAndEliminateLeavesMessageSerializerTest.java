package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import org.ojalgo.random.Uniform;

import static edu.agh.iga.adi.giraph.test.SmallProblem.BRANCH_ID;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

final class MergeAndEliminateLeavesMessageSerializerTest extends MessageSerializerTest<MergeAndEliminateLeavesMessage> {

  MergeAndEliminateLeavesMessageSerializerTest() {
    super(new MergeAndEliminateLeavesMessageSerializer());
  }

  @Override
  MergeAndEliminateLeavesMessage createMessage() {
    return new MergeAndEliminateLeavesMessage(
        BRANCH_ID,
        FACTORY.makeFilled(6, 6, new Uniform()),
        FACTORY.makeFilled(6, 14, new Uniform())
    );
  }

}