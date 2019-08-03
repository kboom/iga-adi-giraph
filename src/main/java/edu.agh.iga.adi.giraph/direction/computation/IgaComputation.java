package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.IgaOperation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import static edu.agh.iga.adi.giraph.direction.computation.ComputationUtil.elementOf;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationUtil.messagesOf;

abstract class IgaComputation<R extends IgaMessage, S extends IgaMessage>
    extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  private final IgaOperation<R, S> operation;
  private final Class<R> receivedMessagesClazz;

  IgaComputation(
      IgaOperation<R, S> operation,
      Class<R> receivedMessagesClazz
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

}
