package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import java.util.stream.Stream;

import static java.util.stream.StreamSupport.stream;

final class IgaComputation extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  @Override
  public final void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    send(vertex, update(vertex, messages));
    vertex.voteToHalt();
  }

  private IgaElement update(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    IgaElement element = elementOf(vertex);
    messagesOf(messages).forEach(msg -> consume(element, msg));
    vertex.setValue(vertex.getValue().withValue(element));
    return element;
  }

  @SuppressWarnings("unchecked")
  private void consume(IgaElement element, IgaMessage msg) {
    msg.getOperation().consumeMessage(element, msg);
  }

  private void send(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, IgaElement element) {
    vertex.getEdges().forEach(edge -> {
      LongWritable dstId = edge.getTargetVertexId();
      sendMessage(dstId, new IgaMessageWritable(edge.getValue().getIgaOperation().sendMessage(dstId.get(), element)));
    });
  }

  private static IgaElement elementOf(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    return vertex.getValue().getElement();
  }

  private static Stream<IgaMessage> messagesOf(Iterable<IgaMessageWritable> iterable) {
    return stream(iterable.spliterator(), false)
        .map(IgaMessageWritable::getIgaMessage);
  }

}
