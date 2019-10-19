package edu.agh.iga.adi.giraph.direction.computation.initialisation;

import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
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

import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_ITERATION;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.STEP;
import static edu.agh.iga.adi.giraph.direction.computation.ProblemFactoryResolver.getProblemFactory;
import static edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialisationComputation.InitialisationPhase.resolvePhase;

/**
 * Computation triggered once the data is loaded into the branches.
 * This makes sure the branches send the initialisation messages to the appropriate leaves and that the leaves use
 * those messages to initialize their matrices.
 */
public class InitialisationComputation extends IgaComputation {

  private static final Logger LOG = Logger.getLogger(InitialisationComputation.class);

  private InitialisationPhase phase;
  private Initialisation initialisation;

  private final IgaMessageWritable msgWritable = new IgaMessageWritable();
  private final LongWritable idWritable = new LongWritable();

  @Override
  public void preSuperstep() {
    final IntWritable iteration = getAggregatedValue(COMPUTATION_ITERATION);
    final IntWritable step = getAggregatedValue(STEP);
    phase = resolvePhase(iteration.get());
    val pf = getProblemFactory(getConf(), step.get());
    val ef = new HorizontalElementFactory(getMesh(), pf.coefficients());
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
    if (igaVertex.is(BranchVertex.class)) {
      if (LOG.isTraceEnabled()) {
        LOG.trace("Sending coefficients from " + igaVertex);
      }

      initialisation.sendMessages(igaVertex, elementOf(vertex))
          .forEach(msg -> {
            idWritable.set(msg.getDstId());
            sendMessage(
                idWritable,
                msgWritable.withMessage(msg)
            );
          });
    }
  }

  private void receive(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    Stream<InitialisationIgaMessage> msg = messagesOf(messages).map(f -> (InitialisationIgaMessage) f);
    val element = initialisation.receiveMessages(vertexOf(vertex), msg);
    vertex.setValue(vertex.getValue().withValue(element));
  }

  enum InitialisationPhase {
    SEND,
    RECEIVE,
    END;

    static InitialisationPhase resolvePhase(int current) {
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
