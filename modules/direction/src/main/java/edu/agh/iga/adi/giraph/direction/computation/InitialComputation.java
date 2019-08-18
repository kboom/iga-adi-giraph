package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.WorkerClientRequestProcessor;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.GraphState;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.worker.WorkerGlobalCommUsage;
import org.apache.hadoop.io.LongWritable;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationLogger.computationLog;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationLogger.logPhase;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.MERGE_AND_ELIMINATE_LEAVES;

/**
 * Kicks off the cascade of operations at the leaf vertices.
 * All vertices are voted to halt so that only the messages wake them up.
 */
public final class InitialComputation
    extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

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
    final IgaVertex igaVertex = vertexOf(directionTree, vertex.getId().get());
    if (igaVertex.is(LeafVertex.class)) {
      final IgaElement element = vertex.getValue().getElement();
      vertex.getEdges().forEach(edge -> {
        final LongWritable dstId = edge.getTargetVertexId();
        final IgaVertex dstVertex = vertexOf(directionTree, dstId.get());
        sendMessage(dstId, new IgaMessageWritable(edge.getValue().getIgaOperation().sendMessage(dstVertex, element)));
      });
    }
    vertex.voteToHalt();

    computationLog(vertex.getValue().getElement());
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
