package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static org.apache.commons.lang3.StringUtils.join;

public final class CoefficientMatricesOutputFormat extends TextVertexOutputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Text EMPTY_LINE = new Text();

  @Override
  public TextVertexWriter createVertexWriter(TaskAttemptContext context) {
    final int problemSize = PROBLEM_SIZE.get(context.getConfiguration());
    final DirectionTree directionTree = new DirectionTree(problemSize);
    return new IdWithValueVertexWriter(directionTree);
  }

  protected class IdWithValueVertexWriter extends TextVertexWriterToEachLine {

    private static final char DELIMITER = ',';
    private final DirectionTree directionTree;

    private IdWithValueVertexWriter(DirectionTree directionTree) {
      this.directionTree = directionTree;
    }

    @Override
    protected Text convertVertexToLine(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
      if (vertexOf(directionTree, vertex.getId().get()).is(BranchVertex.class)) {
        return new Text(
            vertex.getId().get() + " " + join(vertex.getValue().getElement().mx.data, DELIMITER)
        );
      } else {
        return EMPTY_LINE;
      }
    }

  }

}
