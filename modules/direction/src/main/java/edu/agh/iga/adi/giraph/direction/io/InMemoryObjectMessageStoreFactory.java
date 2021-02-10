package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.messages.MessageStore;
import org.apache.giraph.comm.messages.MessageStoreFactory;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.conf.MessageClasses;
import org.apache.giraph.factories.MessageValueFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class InMemoryObjectMessageStoreFactory<I extends WritableComparable, M extends Writable>
    implements MessageStoreFactory<I, M, MessageStore<I, M>> {

  private CentralizedServiceWorker<I, ?, ?> service;
  private ImmutableClassesGiraphConfiguration<I, ?, ?> conf;

  @Override
  public MessageStore<I, M> newStore(MessageClasses<I, M> messageClasses) {
    if(conf.getVertexIdClass().equals(IntWritable.class)) {
      return (MessageStore<I, M>) new MessageListMessagesStore(
          (MessageValueFactory<IgaMessageWritable>) messageClasses.createMessageValueFactory(conf),
          (CentralizedServiceWorker<IntWritable, Writable, Writable>) service
      );
    } else {
      throw new IllegalStateException("Not supported vertex id type");
    }
  }

  @Override
  public void initialize(CentralizedServiceWorker<I, ?, ?> service, ImmutableClassesGiraphConfiguration<I, ?, ?> conf) {
    this.service = service;
    this.conf = conf;
  }

}
