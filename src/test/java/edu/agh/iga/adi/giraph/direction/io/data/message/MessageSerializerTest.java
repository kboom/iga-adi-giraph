package edu.agh.iga.adi.giraph.direction.io.data.message;

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
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    T wroteMessage = createMessage();

    // when
    messageSerializer.writeMessage(newDataOutput(baos), wroteMessage);
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    T readMessage = messageSerializer.readMessage(newDataInput(bais));

    // then
    assertThat(readMessage).isEqualToComparingFieldByField(wroteMessage);
  }

  abstract T createMessage();

}
