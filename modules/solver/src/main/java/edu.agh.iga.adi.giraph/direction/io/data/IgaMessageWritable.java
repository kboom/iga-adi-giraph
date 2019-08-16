package edu.agh.iga.adi.giraph.direction.io.data;

import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.direction.io.data.message.MessageSerializerFacade;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;

public class IgaMessageWritable implements WritableComparable {

  private static final MessageSerializerFacade MESSAGE_SERIALIZER = new MessageSerializerFacade();

  private IgaMessage igaMessage;

  public IgaMessageWritable(IgaMessage igaMessage) {
    this.igaMessage = igaMessage;
  }

  @Override
  public void write(DataOutput dataOutput) {
    MESSAGE_SERIALIZER.writeMessage(dataOutput, igaMessage);
  }

  @Override
  public void readFields(DataInput dataInput) {
    igaMessage = MESSAGE_SERIALIZER.readMessage(dataInput);
  }

  @Override
  public int compareTo(Object o) {
    IgaMessageWritable other = (IgaMessageWritable) o;
    return (int) (igaMessage.getSrcId() - other.igaMessage.getSrcId());
  }

  public IgaMessage getIgaMessage() {
    return igaMessage;
  }

  @SuppressWarnings("unused")
  public IgaMessageWritable() {

  }

}
