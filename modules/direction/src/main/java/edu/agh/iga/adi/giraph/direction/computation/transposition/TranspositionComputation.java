package edu.agh.iga.adi.giraph.direction.computation.transposition;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.operations.transposition.TranspositionIgaOperation.TRANSPOSITION_IGA_OPERATION;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_START;
import static edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation.TranspositionPhase.phaseFor;

public class TranspositionComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(TranspositionComputation.class);

  private TranspositionPhase phase;

  @Override
  public void preSuperstep() {
    IntWritable computationStart = getAggregatedValue(COMPUTATION_START);
    phase = phaseFor(computationStart.get(), (int) getSuperstep());
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
    final long vertexId = vertex.getId().get();
    final IgaVertex igaVertex = vertexOf(getDirectionTree(), vertexId);
    if (LOG.isDebugEnabled()) {
      LOG.debug("Running transposition on " + igaVertex);
    }
    final IgaElement element = vertex.getValue().getElement();
    final long lastLeafIndex = getDirectionTree().lastIndexOfLeafRow();
    for (long l = getDirectionTree().firstIndexOfLeafRow(); l <= lastLeafIndex; l++) {
      val dst = vertexOf(getDirectionTree(), l);
      val igaMessage = TRANSPOSITION_IGA_OPERATION.sendMessage(dst, element);
      sendMessage(
          new LongWritable(l),
          new IgaMessageWritable(igaMessage)
      );
    }
  }

  private void receive(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex, Iterable<IgaMessageWritable> message) {
    message.forEach(msg -> TRANSPOSITION_IGA_OPERATION.consumeMessage(vertex.getValue().getElement(), msg.getMessage(), getDirectionTree()));
  }

  enum TranspositionPhase {
    SEND,
    RECEIVE,
    END;

    static TranspositionPhase phaseFor(int start, int current) {
      if (start == current) {
        return SEND;
      }
      if (current == start + 1) {
        return RECEIVE;
      }
      return END;
    }
  }

}
