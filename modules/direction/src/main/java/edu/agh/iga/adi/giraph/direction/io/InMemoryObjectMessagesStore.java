package edu.agh.iga.adi.giraph.direction.io;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import lombok.val;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.messages.MessageStore;
import org.apache.giraph.utils.EmptyIterable;
import org.apache.giraph.utils.VertexIdMessages;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

/**
 * A store that does not serialize and deserialize messages unless necessary.
 * This is extremely inefficient if most of the messages have to go over the network and very efficient if most of
 * the messages are being sent to vertices within the same partition as the sender vertices.
 *
 * @param <I> Message identifier type
 * @param <M> Message type
 * @param <T> Message holder for one vertex type
 */
public abstract class InMemoryObjectMessagesStore<M extends Writable, T>
    implements MessageStore<IntWritable, M> {

  /**
   * Efficiently maps partitions -> vertices -> messages.
   */
  private final Int2ObjectOpenHashMap<Int2ObjectOpenHashMap<T>> map;

  private final CentralizedServiceWorker<IntWritable, ?, ?> service;

  public InMemoryObjectMessagesStore(CentralizedServiceWorker<IntWritable, Writable, Writable> service) {
    this.service = service;
    val partitionStore = service.getPartitionStore();
    map = new Int2ObjectOpenHashMap<>(partitionStore.getNumPartitions());
    for (int partitionId : service.getPartitionStore().getPartitionIds()) {
      Int2ObjectOpenHashMap<T> partitionMap = new Int2ObjectOpenHashMap<>(
          (int) service.getPartitionStore().getPartitionVertexCount(partitionId)
      );
      map.put(partitionId, partitionMap);
    }
  }

  private Int2ObjectOpenHashMap<T> getPartitionMap(IntWritable vertexId) {
    return map.get(service.getPartitionId(vertexId));
  }

  private T getVertexMessagesHolder(IntWritable vertexId) {
    return getPartitionMap(vertexId).get(vertexId.get());
  }

  private T getOrCreateMessageHolder(
      Int2ObjectOpenHashMap<T> partitionMap,
      int vertexId
  ) {
    return partitionMap.computeIfAbsent(vertexId, __ -> createMessagesHolder());
  }

  protected abstract T createMessagesHolder();

  protected abstract Iterable<M> messagesOf(T data);

  protected abstract void store(T msgHolder, M currentData);

  protected abstract void write(T value, DataOutput out);

  protected abstract T read(DataInput in);

  @Override
  public boolean isPointerListEncoding() {
    return false;
  }

  @Override
  public Iterable<M> getVertexMessages(IntWritable vertexId) {
    val holder = getVertexMessagesHolder(vertexId);
    if (holder != null) {
      return messagesOf(holder);
    } else {
      return EmptyIterable.get();
    }
  }

  @Override
  public void clearVertexMessages(IntWritable vertexId) {
    getPartitionMap(vertexId).remove(vertexId.get());
  }

  @Override
  public void clearAll() {
    map.clear();
  }

  @Override
  public boolean hasMessagesForVertex(IntWritable vertexId) {
    return getPartitionMap(vertexId).containsKey(vertexId.get());
  }

  @Override
  public boolean hasMessagesForPartition(int partitionId) {
    val partitionMessages = map.get(partitionId);
    return partitionMessages != null && !partitionMessages.isEmpty();
  }

  @Override
  public void addPartitionMessages(int partitionId, VertexIdMessages<IntWritable, M> messages) {
    val partitionMap = map.get(partitionId);
    synchronized (partitionMap) {
      val msgIterator = messages.getVertexIdMessageIterator();
      while (msgIterator.hasNext()) {
        msgIterator.next();
        val msgHolder = getOrCreateMessageHolder(partitionMap, msgIterator.getCurrentVertexId().get());
        store(msgHolder, msgIterator.getCurrentData());
      }
    }
  }

  @Override
  public void finalizeStore() {

  }

  /*
   * TODO check if we can reuse the same IntWritable
   */
  @Override
  public Iterable<IntWritable> getPartitionDestinationVertices(int partitionId) {
    val partitionMap = map.get(partitionId);
    List<IntWritable> vertices = Lists.newArrayListWithCapacity(partitionMap.size());
    final IntIterator iterator = partitionMap.keySet().iterator();
    while (iterator.hasNext()) {
      vertices.add(new IntWritable(iterator.nextInt()));
    }
    return vertices;
  }

  @Override
  public void clearPartition(int partitionId) {
    map.get(partitionId).clear();
  }

  @Override
  public void writePartition(DataOutput out, int partitionId) throws IOException {
    val partitionMap = map.get(partitionId);
    out.writeInt(partitionMap.size());
    val it = partitionMap.int2ObjectEntrySet().fastIterator();
    while (it.hasNext()) {
      val entry = it.next();
      out.writeInt(entry.getIntKey());
      write(entry.getValue(), out);
    }
  }


  @Override
  public void readFieldsForPartition(DataInput in, int partitionId) throws IOException {
    int size = in.readInt();
    val partitionMap = new Int2ObjectOpenHashMap<T>(size);
    while (size-- > 0) {
      int vertexId = in.readInt();
      partitionMap.put(vertexId, read(in));
    }
    synchronized (map) {
      map.put(partitionId, partitionMap);
    }
  }

}
