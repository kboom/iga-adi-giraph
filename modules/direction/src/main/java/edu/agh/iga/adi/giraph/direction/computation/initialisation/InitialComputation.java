package edu.agh.iga.adi.giraph.direction.computation.initialisation;

import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import static edu.agh.iga.adi.giraph.direction.computation.factorization.FactorizationLogger.computationLog;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.FactorizationLogger.logPhase;
import static edu.agh.iga.adi.giraph.direction.computation.factorization.IgaComputationPhase.MERGE_AND_ELIMINATE_LEAVES;

/**
 * Kicks off the operations for a single time step.
 * This computation is introduced to separate {@link edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation}
 * from the actual operations it has to perform.
 */
public final class InitialComputation extends IgaComputation {

  @Override
  public void preSuperstep() {
    logPhase(MERGE_AND_ELIMINATE_LEAVES);
  }

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    val igaVertex = vertexOf(vertex);
    val edges = vertex.getEdges();

    if (igaVertex.is(LeafVertex.class)) {
      IgaElementWritable value = vertex.getValue();
      val element = value.getElement();
      edges.forEach(edge -> {
        val dstId = edge.getTargetVertexId();
        val dstVertex = vertexOf(dstId.get());
        val igaOperation = edge.getValue().getIgaOperation();
        sendMessage(dstId, new IgaMessageWritable(igaOperation.sendMessage(dstVertex, element)));
      });

      computationLog(element);
      operationOf(vertex).ifPresent(operation -> vertex.setValue(value.withValue(operation.postSend(element, getTree()))));
    }

    vertex.voteToHalt();
  }

}
