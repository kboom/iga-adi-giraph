package edu.agh.iga.adi.giraph.core.setup;

import edu.agh.iga.adi.giraph.core.IgaContext;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaMessage;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.factory.ElementFactory;
import edu.agh.iga.adi.giraph.core.problem.PartialSolution;
import edu.agh.iga.adi.giraph.core.problem.ProblemFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.ojalgo.matrix.store.TransformableRegion;

import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.setup.VertexDependencies.coefficientsFor;
import static edu.agh.iga.adi.giraph.core.setup.VertexDependencies.verticesDependingOn;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class Initialisation {

  private final IgaContext igaContext;
  private final ElementFactory elementFactory;
  private final ProblemFactory problemFactory;

  public Stream<InitialisationIgaMessage> sendMessages(IgaVertex vertex, IgaElement element) {
    return verticesDependingOn(vertex)
        .mapToObj(dst -> new InitialisationIgaMessage(
            vertex.id(), dst, element.mx.regionByRows(coefficientsFor(vertex, dst))
        ));
  }

  public IgaElement receiveMessages(IgaVertex vertex, Stream<InitialisationIgaMessage> messages) {
    return elementFactory.createElement(
        problemFactory.problemFor(partialSolutionFrom(messages)),
        vertex
    );
  }

  /**
   * Each region is composed of at most 2 messages (coming from 2 branch vertices).
   * Rather than merging elements into a bigger matrix we exploit this to route the requests to appropriate matrix.
   *
   * @param messages
   * @return
   */
  private PartialSolution partialSolutionFrom(Stream<InitialisationIgaMessage> messages) {
    val rowChunks = messages.sorted()
        .map(InitialisationIgaMessage::getMxp)
        .collect(toList());

    val left = rowChunks.get(0);
    val right = rowChunks.size() > 1 ? rowChunks.get(1) : null;

    return new CoefficientSolution(igaContext.getMesh(), new InitialisationAccess2D(left, right));
  }

  @Getter
  public static class InitialisationIgaMessage extends IgaMessage implements Comparable<InitialisationIgaMessage> {

    private final TransformableRegion<Double> mxp;
    private final long dstId;

    public InitialisationIgaMessage(long srcId, long dstId, TransformableRegion<Double> mxp) {
      super(srcId, null);
      this.mxp = mxp;
      this.dstId = dstId;
    }

    @Override
    public int compareTo(InitialisationIgaMessage o) {
      return 0;
    }
  }

}
