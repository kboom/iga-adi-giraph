package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.join;

public final class CoefficientMatricesOutputFormat extends TextVertexOutputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  @Override
  public TextVertexWriter createVertexWriter(TaskAttemptContext context) {
    return new IdWithValueVertexWriter();
  }

  protected class IdWithValueVertexWriter extends TextVertexWriterToEachLine {

    @Override
    protected Text convertVertexToLine(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
      return new Text(
          vertex.getId().get() + " " + join(vertex.getValue().getElement().mx.data, ',')
      );
    }

  }

}
