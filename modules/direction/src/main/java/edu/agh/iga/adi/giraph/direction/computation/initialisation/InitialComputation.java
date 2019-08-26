package edu.agh.iga.adi.giraph.direction.computation.initialisation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.WorkerClientRequestProcessor;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.GraphState;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.worker.WorkerGlobalCommUsage;
import org.apache.hadoop.io.LongWritable;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationLogger.computationLog;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationLogger.logPhase;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.MERGE_AND_ELIMINATE_LEAVES;
import static java.util.stream.StreamSupport.stream;

/**
 * Kicks off the cascade of operations at the leaf vertices.
 * All vertices are voted to halt so that only the messages wake them up.
 */
public final class InitialComputation extends IgaComputation {

  private DirectionTree directionTree;

  @Override
  public void preSuperstep() {
    logPhase(MERGE_AND_ELIMINATE_LEAVES);
  }

  @Override
  public void compute(
      Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex,
      Iterable<IgaMessageWritable> messages
  ) {
    val igaVertex = vertexOf(directionTree, vertex.getId().get());
    val edges = vertex.getEdges();

    if (igaVertex.is(LeafVertex.class)) {
      val element = vertex.getValue().getElement();
      edges.forEach(edge -> {
        val dstId = edge.getTargetVertexId();
        val dstVertex = vertexOf(directionTree, dstId.get());
        val igaOperation = edge.getValue().getIgaOperation();
        sendMessage(dstId, new IgaMessageWritable(igaOperation.sendMessage(dstVertex, element)));
      });

      computationLog(vertex.getValue().getElement());

//      stream(edges.spliterator(), false)
//          .map(Edge::getValue)
//          .map(IgaOperationWritable::getIgaOperation)
//          .distinct()
//          .forEach(operation -> operation.postSend(element, directionTree));
    }

    vertex.voteToHalt();
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
