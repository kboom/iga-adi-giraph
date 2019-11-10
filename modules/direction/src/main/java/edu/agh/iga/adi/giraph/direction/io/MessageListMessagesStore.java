package edu.agh.iga.adi.giraph.direction.io;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.factories.MessageValueFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;

public final class MessageListMessagesStore<M extends Writable> extends InMemoryObjectMessagesStore<M, ObjectArrayList<M>> {

  private final MessageValueFactory<M> messageValueFactory;

  public MessageListMessagesStore(
      MessageValueFactory<M> messageValueFactory,
      CentralizedServiceWorker<IntWritable, Writable, Writable> service
  ) {
    super(service);
    this.messageValueFactory = messageValueFactory;
  }

  @Override
  protected ObjectArrayList<M> createMessagesHolder() {
    return new ObjectArrayList<>();
  }

  @Override
  protected Iterable<M> messagesOf(ObjectArrayList<M> data) {
    return data;
  }

  @Override
  protected void store(ObjectArrayList<M> msgHolder, M currentData) {
    msgHolder.add(currentData);
  }

  @Override
  @SneakyThrows
  protected void write(ObjectArrayList<M> value, DataOutput out) {
    out.writeInt(value.size());
    for (M m : value) {
      m.write(out);
    }
  }

  @Override
  @SneakyThrows
  protected ObjectArrayList<M> read(DataInput in) {
    int size = in.readInt();
    val data = new ObjectArrayList<M>(size);
    while (size-- > 0) {
      val msg = messageValueFactory.newInstance();
      msg.readFields(in);
      data.add(msg);
    }
    return data;
  }

}
