package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.factories.MessageValueFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;

public final class MessageListMessagesStore extends InMemoryObjectMessagesStore<IgaMessageWritable,
    ObjectArrayList<IgaMessageWritable>> {

  private final MessageValueFactory<IgaMessageWritable> messageValueFactory;

  public MessageListMessagesStore(
      MessageValueFactory<IgaMessageWritable> messageValueFactory,
      CentralizedServiceWorker<IntWritable, Writable, Writable> service
  ) {
    super(service);
    this.messageValueFactory = messageValueFactory;
  }

  @Override
  protected ObjectArrayList<IgaMessageWritable> createMessagesHolder() {
    return new ObjectArrayList<>();
  }

  @Override
  protected Iterable<IgaMessageWritable> messagesOf(ObjectArrayList<IgaMessageWritable> data) {
    return data;
  }

  @Override
  protected void store(ObjectArrayList<IgaMessageWritable> msgHolder, IgaMessageWritable currentData) {
    msgHolder.add(messageValueFactory.newInstance().withData(currentData.getIgaMessage()));
  }

  @Override
  @SneakyThrows
  protected void write(ObjectArrayList<IgaMessageWritable> value, DataOutput out) {
    out.writeInt(value.size());
    for (IgaMessageWritable m : value) {
      m.write(out);
    }
  }

  @Override
  @SneakyThrows
  protected ObjectArrayList<IgaMessageWritable> read(DataInput in) {
    int size = in.readInt();
    val data = new ObjectArrayList<IgaMessageWritable>(size);
    while (size-- > 0) {
      val msg = messageValueFactory.newInstance();
      msg.readFields(in);
      data.add(msg);
    }
    return data;
  }

}
