package edu.agh.iga.adi.giraph.direction.computation.factorization;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_ITERATION;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.LAST_COMPUTATION_FLAG;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.FactorizationLogger.computationLog;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.FactorizationLogger.logPhase;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationPhase.phaseFor;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public final class FactorisationComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(FactorisationComputation.class);

  private IgaComputationPhase phase;
  private boolean isLastRun;

  @Override
  public void preSuperstep() {
    loadPhase();
    loadLastComputationFlag();
    logPhase(phase);
    if (LOG.isDebugEnabled()) {
      LOG.debug(format("================ SUPER STEP (%d) %s ================", getSuperstep() - 1, phase));
    }
  }

  @Override
  public final void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    operationOf(messages).ifPresent(operation -> vertex.getValue().withValue(operation.preConsume(vertexOf(vertex), getIgaContext(), vertex.getValue().getElement())));
    send(vertex, update(vertex, messages));

    if (isLastRun) {
      vertex.wakeUp(); // this effectively keeps the algorithm running so that the next computation can happen even if we didn't sent the messages in the last step
    } else {
      vertex.voteToHalt(); // we halt the vertices cause we are sending the messages which will wake the other
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
    operationOf(messages).ifPresent(operation -> operation.postConsume(element, getTree()));

    vertex.setValue(vertex.getValue().withValue(element));
    return element;
  }


  @SuppressWarnings("unchecked")
  private void consume(IgaElement element, IgaMessage msg) {
    msg.getOperation().consumeMessage(element, msg, getTree());
  }

  private void send(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, IgaElement element) {
    vertex.getEdges().forEach(edge -> {
      val igaOperation = edge.getValue().getIgaOperation();
      val dstIdWritable = edge.getTargetVertexId();
      val dstId = dstIdWritable.get();
      if (phase.matchesDirection(element.id, dstId)) {
        val dstVertex = vertexOf(dstId);
        val msg = igaOperation.sendMessage(dstVertex, element);
        sendMessage(dstIdWritable, new IgaMessageWritable(msg));
        if (LOG.isDebugEnabled()) {
          LOG.debug(format("Sending message to %d %s", dstId, igaOperation));
        }
      }
    });
  }

  private void loadPhase() {
    IntWritable iteration = getAggregatedValue(COMPUTATION_ITERATION);
    phase = phaseFor(getTree(), iteration.get());
  }

  private void loadLastComputationFlag() {
    BooleanWritable lastComputationFlag = getAggregatedValue(LAST_COMPUTATION_FLAG);
    isLastRun = lastComputationFlag.get();
  }

}
