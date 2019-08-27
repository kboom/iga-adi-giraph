package edu.agh.iga.adi.giraph.direction.computation.factorization;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaOperation;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_START;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationResolver.computationForStep;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.FactorizationLogger.computationLog;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.FactorizationLogger.logPhase;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationPhase.phaseFor;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public final class FactorisationComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(FactorisationComputation.class);

  private IgaComputationPhase phase;

  @Override
  public void preSuperstep() {
    IntWritable computationStart = getAggregatedValue(COMPUTATION_START);
    phase = phaseFor(getDirectionTree(), (int) getSuperstep() - computationStart.get());
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

    operationOf(messages).ifPresent(operation -> operation.preConsume(vertex.getValue().getElement(), getDirectionTree()));
    send(vertex, update(vertex, messages));

    if (computationForStep(getDirectionTree(), getSuperstep() + 1) == FactorisationComputation.class) {
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
    operationOf(messages).ifPresent(operation -> operation.postConsume(element, getDirectionTree()));

    vertex.setValue(vertex.getValue().withValue(element));
    return element;
  }


  @SuppressWarnings("unchecked")
  private void consume(IgaElement element, IgaMessage msg) {
    msg.getOperation().consumeMessage(element, msg, getDirectionTree());
  }

  private void send(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, IgaElement element) {
    vertex.getEdges().forEach(edge -> {
      final IgaOperation igaOperation = edge.getValue().getIgaOperation();
      final LongWritable dstIdWritable = edge.getTargetVertexId();
      final long dstId = dstIdWritable.get();
      if (phase.matchesDirection(element.id, dstId)) {
        final IgaVertex dstVertex = vertexOf(getDirectionTree(), dstId);
        final IgaMessage msg = igaOperation.sendMessage(dstVertex, element);
        sendMessage(dstIdWritable, new IgaMessageWritable(msg));
        if (LOG.isDebugEnabled()) {
          LOG.debug(format("Sending message to %d %s", dstId, igaOperation));
        }
      }
    });
  }

}
