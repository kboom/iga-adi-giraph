package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import java.util.Iterator;

import static java.util.stream.StreamSupport.stream;

abstract class IgaComputation<M extends IgaMessage>
    extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  private final IgaOperation<M> operation;
  private final Class<M> receivedMessagesClazz;

  IgaComputation(
      IgaOperation<M> operation,
      Class<M> receivedMessagesClazz
  ) {
    this.operation = operation;
    this.receivedMessagesClazz = receivedMessagesClazz;
  }

  @Override
  public final void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    IgaElement igaElement = operation.consumeMessages(elementOf(vertex), messagesOf(messages, receivedMessagesClazz));
    vertex.setValue(vertex.getValue().withValue(igaElement));
    operation.sendMessages(igaElement).forEachRemaining(
        s -> sendMessage(new LongWritable(s.getDstId()), new IgaMessageWritable(s))
    );
    vertex.voteToHalt();
  }

  private static IgaElement elementOf(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    return vertex.getValue().getElement();
  }

  private static <T extends IgaMessage> Iterator<T> messagesOf(Iterable<IgaMessageWritable> iterable, Class<T> msgClazz) {
    return stream(iterable.spliterator(), false)
        .map(msg -> msg.getMessageAs(msgClazz))
        .iterator();
  }

}
