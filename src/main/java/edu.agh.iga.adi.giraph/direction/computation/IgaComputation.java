package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.*;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.WorkerClientRequestProcessor;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.GraphState;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.worker.WorkerGlobalCommUsage;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.StreamSupport.stream;

public final class IgaComputation extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  private static final Logger LOG = Logger.getLogger(IgaComputation.class);

  public static final String PHASE = "iga.direction.phase";

  private DirectionTree directionTree;
  private IgaComputationPhase phase;

  @Override
  public void preSuperstep() {
    IntWritable phaseWritable = getAggregatedValue(PHASE);
    phase = IgaComputationPhase.getPhase(phaseWritable.get());
    if (LOG.isDebugEnabled()) {
      LOG.debug("Before superstep " + phase);
    }
  }

  @Override
  public void postSuperstep() {
    if (LOG.isDebugEnabled()) {
      LOG.debug("After superstep " + phase);
    }
  }

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
    if (LOG.isDebugEnabled()) {
      LOG.debug(format(
          "Running on %d with messages %s",
          vertex.getId().get(),
          messagesOf(messages).map(IgaMessage::getSrcId).map(String::valueOf).collect(joining(","))
      ));
    }
    IgaElement element = elementOf(vertex);
    messagesOf(messages).forEach(msg -> consume(element, msg));
    operationOf(messages).ifPresent(operation -> operation.process(element, directionTree));

    vertex.setValue(vertex.getValue().withValue(element));
    return element;
  }

  private Optional<IgaOperation> operationOf(Iterable<IgaMessageWritable> messages) {
    return messagesOf(messages)
        .map(IgaMessage::getOperation)
        .findFirst();
  }

  @SuppressWarnings("unchecked")
  private void consume(IgaElement element, IgaMessage msg) {
    msg.getOperation().consumeMessage(element, msg, directionTree);
  }

  private void send(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, IgaElement element) {
    vertex.getEdges().forEach(edge -> {
      final IgaOperation igaOperation = edge.getValue().getIgaOperation();
      final LongWritable dstIdWritable = edge.getTargetVertexId();
      final long dstId = dstIdWritable.get();
      if (phase.matchesDirection(element.id, dstId)) {
        final IgaVertex dstVertex = vertexOf(directionTree, dstId);
        sendMessage(dstIdWritable, new IgaMessageWritable(igaOperation.sendMessage(dstVertex, element)));
      }
    });
  }

  private static IgaElement elementOf(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    return vertex.getValue().getElement();
  }

  private static Stream<IgaMessage> messagesOf(Iterable<IgaMessageWritable> iterable) {
    return stream(iterable.spliterator(), false)
        .map(IgaMessageWritable::getIgaMessage);
  }

  @Override
  public void initialize(
      GraphState graphState,
      WorkerClientRequestProcessor<LongWritable, IgaElementWritable, IgaOperationWritable> workerClientRequestProcessor,
      CentralizedServiceWorker<LongWritable, IgaElementWritable, IgaOperationWritable> serviceWorker,
      WorkerGlobalCommUsage workerGlobalCommUsage
  ) {
    super.initialize(graphState, workerClientRequestProcessor, serviceWorker, workerGlobalCommUsage);
    directionTree = new DirectionTree(PROBLEM_SIZE.get(getConf()));
  }

}
