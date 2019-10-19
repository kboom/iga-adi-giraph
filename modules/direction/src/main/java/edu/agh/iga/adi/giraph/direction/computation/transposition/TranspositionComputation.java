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
    if (LOG.isTraceEnabled()) {
      LOG.trace("Running transposition on " + vertex.getId().get());
    }
    val element = vertex.getValue().getElement();
    val lastLeafIndex = getTree().lastIndexOfLeafRow();

    val firstIndexOfLeafRow = getTree().firstIndexOfLeafRow();
    val dst = vertexOf(firstIndexOfLeafRow);

    val idWritable = new LongWritable();
    val msgWritable = new IgaMessageWritable();

    for (long l = firstIndexOfLeafRow; l <= lastLeafIndex; l++) {
      dst.reuseSameTypeFor(l);

      idWritable.set(l);
      msgWritable.set(TRANSPOSITION_IGA_OPERATION.sendMessage(dst, element));

      sendMessage(
          idWritable,
          msgWritable
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
