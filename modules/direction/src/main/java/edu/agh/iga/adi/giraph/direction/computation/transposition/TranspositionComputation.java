package edu.agh.iga.adi.giraph.direction.computation.transposition;

import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.operations.setup.TranspositionIgaOperation.TRANSPOSITION_IGA_OPERATION;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_ITERATION;
import static edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation.TranspositionPhase.phaseFor;

/**
 * Computation that happens in between the directions.
 * The leaves send their columns to appropriate leaves which effectively does the transposition of the coefficients.
 */
public class TranspositionComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(TranspositionComputation.class);

  private TranspositionPhase phase;

  @Override
  public void preSuperstep() {
    IntWritable iteration = getAggregatedValue(COMPUTATION_ITERATION);
    phase = phaseFor(iteration.get());
  }

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> message
  ) {
    switch (phase) {
      case SEND:
        send(vertex);
        vertex.voteToHalt();
        break;
      case RECEIVE:
        receive(vertex, message);
        break;
    }
  }

  private void send(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    val igaVertex = vertexOf(vertex);
    if (LOG.isTraceEnabled()) {
      LOG.trace("Running transposition on " + igaVertex);
    }
    val element = vertex.getValue().getElement();
    val lastLeafIndex = getTree().lastIndexOfLeafRow();
    for (long l = getTree().firstIndexOfLeafRow(); l <= lastLeafIndex; l++) {
      val dst = vertexOf(l);
      val igaMessage = TRANSPOSITION_IGA_OPERATION.sendMessage(dst, element); // todo reuse same object, send previous message to reuse?
      sendMessage(
          new LongWritable(l), // todo reuse same object
          new IgaMessageWritable(igaMessage) // todo reuse same object
      );
    }
  }

  private void receive(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, Iterable<IgaMessageWritable> message) {
    vertex.setValue(
        vertex.getValue()
            .withValue(TRANSPOSITION_IGA_OPERATION.preConsume(vertexOf(vertex), getIgaContext(), elementOf(vertex)))
    );
    message.forEach(msg -> TRANSPOSITION_IGA_OPERATION.consumeMessage(vertex.getValue().getElement(), msg.getMessage(), getTree()));
  }

  enum TranspositionPhase {
    SEND,
    RECEIVE,
    END;

    static TranspositionPhase phaseFor(int current) {
      if (current == 0) {
        return SEND;
      }
      if (current == 1) {
        return RECEIVE;
      }
      return END;
    }
  }

}
