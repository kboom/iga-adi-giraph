package edu.agh.iga.adi.giraph.direction.io.data.message;

import com.google.common.io.ByteArrayDataOutput;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.google.common.io.ByteStreams.newDataInput;
import static com.google.common.io.ByteStreams.newDataOutput;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

abstract class MessageSerializerTest<T extends IgaMessage> {

  private final MessageSerializer<T> messageSerializer;

  MessageSerializerTest(MessageSerializer<T> messageSerializer) {
    this.messageSerializer = messageSerializer;
  }

  @Test
  void canSerializeAndDeserialize() throws IOException {
    // given
    ByteArrayDataOutput dataOutput = newDataOutput();
    T wroteMessage = createMessage();

    // when
    messageSerializer.writeMessage(dataOutput, wroteMessage);
    T readMessage = messageSerializer.readMessage(newDataInput(dataOutput.toByteArray()));

    // then
    assertThat(readMessage).isEqualToComparingFieldByField(wroteMessage);
  }

  abstract T createMessage();

}
