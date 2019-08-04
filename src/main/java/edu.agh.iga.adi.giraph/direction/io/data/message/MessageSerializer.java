package edu.agh.iga.adi.giraph.direction.io.data.message;

import edu.agh.iga.adi.giraph.core.IgaMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface MessageSerializer<M extends IgaMessage> {

  void writeMessage(DataOutput dataOutput, M message) throws IOException;

  M readMessage(DataInput dataInput) throws IOException;

}
