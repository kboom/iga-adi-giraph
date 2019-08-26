package edu.agh.iga.adi.giraph.direction.computation.factorization;

import edu.agh.iga.adi.giraph.core.*;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.WorkerClientRequestProcessor;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.GraphState;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.worker.WorkerGlobalCommUsage;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_START;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationLogger.computationLog;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationLogger.logPhase;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationResolver.computationForStep;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.phaseFor;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.StreamSupport.stream;

public final class FactorisationComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(FactorisationComputation.class);

  private DirectionTree directionTree;
  private IgaComputationPhase phase;

  @Override
  public void preSuperstep() {
    IntWritable computationStart = getAggregatedValue(COMPUTATION_START);
    phase = phaseFor(directionTree, (int) getSuperstep() - computationStart.get());
    logPhase(phase);
    if (LOG.isDebugEnabled()) {
      LOG.debug(format("================ SUPERSTEP (%d) %s ================", getSuperstep() - 1, phase));
    }
  }

  @Override
  public final void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    if (phase == null) {
      vertex.voteToHalt();
      return;
    }

    operationOf(messages).ifPresent(operation -> operation.preConsume(vertex.getValue().getElement(), directionTree));
    send(vertex, update(vertex, messages));

    if (computationForStep(directionTree, getSuperstep() + 1) == FactorisationComputation.class) {
      vertex.voteToHalt();
    } else {
      vertex.wakeUp(); // this effectively keeps the algorithm running so that the next computation can happen
    }

    computationLog(vertex.getValue().getElement());
  }

  private IgaElement update(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(format(
          "Processing messages on %d with messages %s",
          vertex.getId().get(),
          messagesOf(messages).map(IgaMessage::getSrcId).map(String::valueOf).collect(joining(","))
      ));
    }
    IgaElement element = elementOf(vertex);
    messagesOf(messages).forEach(msg -> consume(element, msg));
    operationOf(messages).ifPresent(operation -> operation.postConsume(element, directionTree));

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
        final IgaMessage msg = igaOperation.sendMessage(dstVertex, element);
        sendMessage(dstIdWritable, new IgaMessageWritable(msg));
        if (LOG.isDebugEnabled()) {
          LOG.debug(format("Sending message to %d %s", dstId, igaOperation));
        }
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
