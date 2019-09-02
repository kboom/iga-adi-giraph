package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.*;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.val;
import org.apache.giraph.bsp.CentralizedServiceWorker;
import org.apache.giraph.comm.WorkerClientRequestProcessor;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.GraphState;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.worker.WorkerGlobalCommUsage;
import org.apache.hadoop.io.LongWritable;

import java.util.Optional;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static java.util.stream.StreamSupport.stream;

public abstract class IgaComputation extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable> {

  @Getter
  @Delegate
  private IgaContext igaContext;

  @Override
  public void initialize(
      GraphState graphState,
      WorkerClientRequestProcessor<LongWritable, IgaElementWritable, IgaOperationWritable> workerClientRequestProcessor,
      CentralizedServiceWorker<LongWritable, IgaElementWritable, IgaOperationWritable> serviceWorker,
      WorkerGlobalCommUsage workerGlobalCommUsage
  ) {
    super.initialize(graphState, workerClientRequestProcessor, serviceWorker, workerGlobalCommUsage);
    val elementCount = PROBLEM_SIZE.get(getConf());
    val directionTree = new DirectionTree(elementCount);
    val mesh = aMesh().withElements(elementCount).build();

    igaContext = IgaContext.builder()
        .tree(directionTree)
        .mesh(mesh)
        .build();
  }

  protected Optional<IgaOperation> operationOf(Iterable<IgaMessageWritable> messages) {
    return messagesOf(messages)
        .map(IgaMessage::getOperation)
        .findFirst();
  }

  protected IgaVertex vertexOf(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    return vertexOf(vertex.getId().get());
  }

  protected IgaVertex vertexOf(long vertexId) {
    return IgaVertex.vertexOf(getTree(), vertexId);
  }

  protected static IgaElement elementOf(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    return vertex.getValue().getElement();
  }

  protected static Stream<IgaMessage> messagesOf(Iterable<IgaMessageWritable> iterable) {
    return stream(iterable.spliterator(), false)
        .map(IgaMessageWritable::getIgaMessage);
  }

}
