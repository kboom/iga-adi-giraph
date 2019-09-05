package edu.agh.iga.adi.giraph.direction.computation.initialisation;

import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.ProblemFactory;
import edu.agh.iga.adi.giraph.core.setup.Initialisation;
import edu.agh.iga.adi.giraph.core.setup.Initialisation.InitialisationIgaMessage;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_START;
import static edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialisationComputation.InitialisationPhase.phaseFor;

/**
 * Computation triggered once the data is loaded into the branches.
 * This makes sure the branches send the initialisation messages to the appropriate leaves and that the leaves use
 * those messages to initialize their matrices.
 */
public class InitialisationComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(InitialisationComputation.class);

  private InitialisationPhase phase;
  private Initialisation initialisation;

  @Override
  public void preSuperstep() {
    IntWritable computationStart = getAggregatedValue(COMPUTATION_START);
    phase = phaseFor(computationStart.get(), (int) getSuperstep());
    val ef = new HorizontalElementFactory(getMesh());
    ProblemFactory pf = partialSolution -> partialSolution::valueAt; // for now don't do nothing
    initialisation = new Initialisation(getIgaContext(), ef, pf);
  }

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    switch (phase) {
      case SEND:
        doSend(vertex);
        vertex.voteToHalt();
        break;
      case RECEIVE:
        receive(vertex, messages);
        break;
      case END:
        break;
    }
  }

  private void doSend(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    val igaVertex = vertexOf(vertex);
    if (LOG.isDebugEnabled()) {
      LOG.debug("Running transposition on " + igaVertex);
    }
    initialisation.sendMessages(igaVertex, elementOf(vertex))
        .forEach(msg -> sendMessage(
            new LongWritable(msg.getDstId()),
            new IgaMessageWritable(msg)
        ));
  }

  private void receive(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    Stream<InitialisationIgaMessage> msg = messagesOf(messages).map(f -> (InitialisationIgaMessage) f);
    val element = initialisation.receiveMessages(vertexOf(vertex), msg);
    vertex.setValue(new IgaElementWritable(element));
  }

  enum InitialisationPhase {
    SEND,
    RECEIVE,
    END;

    static InitialisationPhase phaseFor(int start, int current) {
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
