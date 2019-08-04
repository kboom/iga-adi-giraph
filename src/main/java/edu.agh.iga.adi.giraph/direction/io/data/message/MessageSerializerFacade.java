package edu.agh.iga.adi.giraph.direction.io.data.message;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteBranchOperation.BackwardsSubstituteBranchMessage;
import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteInterimOperation.BackwardsSubstituteInterimMessage;
import edu.agh.iga.adi.giraph.core.operations.BackwardsSubstituteRootOperation.BackwardsSubstituteRootMessage;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateBranchOperation.MergeAndEliminateBranchMessage;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateInterimOperation.MergeAndEliminateInterimMessage;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateLeavesOperation.MergeAndEliminateLeavesMessage;
import edu.agh.iga.adi.giraph.core.operations.MergeAndEliminateRootOperation.MergeAndEliminateRootMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;

import static edu.agh.iga.adi.giraph.direction.io.data.message.BackwardsSubstituteBranchMessageSerializer.BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE_SERIALIZER;
import static edu.agh.iga.adi.giraph.direction.io.data.message.BackwardsSubstituteInterimMessageSerializer.BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE_SERIALIZER;
import static edu.agh.iga.adi.giraph.direction.io.data.message.BackwardsSubstituteRootMessageSerializer.BACKWARDS_SUBSTITUTE_ROOT_MESSAGE_SERIALIZER;
import static edu.agh.iga.adi.giraph.direction.io.data.message.MergeAndEliminateBranchMessageSerializer.MERGE_AND_ELIMINATE_BRANCH_MESSAGE_SERIALIZER;
import static edu.agh.iga.adi.giraph.direction.io.data.message.MergeAndEliminateInterimMessageSerializer.MERGE_AND_ELIMINATE_INTERIM_MESSAGE_SERIALIZER;
import static edu.agh.iga.adi.giraph.direction.io.data.message.MergeAndEliminateLeavesMessageSerializer.MERGE_AND_ELIMINATE_LEAVES_MESSAGE_SERIALIZER;
import static edu.agh.iga.adi.giraph.direction.io.data.message.MergeAndEliminateRootMessageSerializer.MERGE_AND_ELIMINATE_ROOT_MESSAGE_SERIALIZER;

public final class MessageSerializerFacade {

  private static final Map<Class<?>, MessageSerializer> SERIALIZER_MAP = ImmutableMap.<Class<?>, MessageSerializer>builder()
      .put(BackwardsSubstituteBranchMessage.class, BACKWARDS_SUBSTITUTE_BRANCH_MESSAGE_SERIALIZER)
      .put(BackwardsSubstituteInterimMessage.class, BACKWARDS_SUBSTITUTE_INTERIM_MESSAGE_SERIALIZER)
      .put(BackwardsSubstituteRootMessage.class, BACKWARDS_SUBSTITUTE_ROOT_MESSAGE_SERIALIZER)
      .put(MergeAndEliminateBranchMessage.class, MERGE_AND_ELIMINATE_BRANCH_MESSAGE_SERIALIZER)
      .put(MergeAndEliminateInterimMessage.class, MERGE_AND_ELIMINATE_INTERIM_MESSAGE_SERIALIZER)
      .put(MergeAndEliminateLeavesMessage.class, MERGE_AND_ELIMINATE_LEAVES_MESSAGE_SERIALIZER)
      .put(MergeAndEliminateRootMessage.class, MERGE_AND_ELIMINATE_ROOT_MESSAGE_SERIALIZER)
      .build();

  private static final BiMap<Class<?>, Integer> MESSAGE_TYPE_MAPPING = ImmutableBiMap.<Class<?>, Integer>builder()
      .put(BackwardsSubstituteBranchMessage.class, 1)
      .put(BackwardsSubstituteInterimMessage.class, 2)
      .put(BackwardsSubstituteRootMessage.class, 3)
      .put(MergeAndEliminateBranchMessage.class, 4)
      .put(MergeAndEliminateInterimMessage.class, 5)
      .put(MergeAndEliminateLeavesMessage.class, 6)
      .put(MergeAndEliminateRootMessage.class, 7)
      .build();

  @SuppressWarnings("unchecked")
  public void writeMessage(DataOutput dataOutput, IgaMessage message) {
    final Class<? extends IgaMessage> clazz = message.getClass();
    try {
      dataOutput.writeInt(MESSAGE_TYPE_MAPPING.get(clazz));
      SERIALIZER_MAP.get(clazz).writeMessage(dataOutput, message);
    } catch (IOException e) {
      throw new IllegalStateException("Could not serialize message");
    }
  }

  public IgaMessage readMessage(DataInput dataInput) {
    try {
      final int messageType = dataInput.readInt();
      final Class<?> messageClazz = MESSAGE_TYPE_MAPPING.inverse().get(messageType);
      final MessageSerializer messageSerializer = SERIALIZER_MAP.get(messageClazz);
      return messageSerializer.readMessage(dataInput);
    } catch (IOException e) {
      throw new IllegalStateException("Could not deserialize message");
    }
  }

}
