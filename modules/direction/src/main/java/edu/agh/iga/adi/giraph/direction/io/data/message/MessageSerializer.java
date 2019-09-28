package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.IgaMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface MessageSerializer<M extends IgaMessage> {

  void writeMessage(DataOutput dataOutput, M message) throws IOException;

  /**
   * Reads the data from the input producing a brand new message.
   * Should only be used if no message was yet read in this session or if the message is of a different type.
   *
   * @param dataInput the data belonging to the new message.
   * @return the brand new message filled with the data
   * @throws IOException
   */
  M readMessage(DataInput dataInput) throws IOException;

  /**
   * Updates an existing message of same type with the data from the data input.
   * This way we don't put additional pressure on the GC.
   *
   * In rare cases this method needs to produce a new message anyway based on the contents of the message.
   * In the majority of cases this will be the input message updated with the data.
   *
   * @param message   other message that has already been read and can be overridden
   * @param dataInput the data of the new message to be used to populate the old message
   * @throws IOException
   */
  M readMessage(M message, DataInput dataInput) throws IOException;

}
