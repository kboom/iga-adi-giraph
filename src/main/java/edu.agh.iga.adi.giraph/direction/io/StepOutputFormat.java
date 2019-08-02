package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * Dumps the elements
 */
public class StepOutputFormat extends TextVertexOutputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final String LINE_TOKENIZE_VALUE = "output.delimiter";
  private static final String LINE_TOKENIZE_VALUE_DEFAULT = "\t";
  private static final String REVERSE_ID_AND_VALUE = "reverse.id.and.value";
  private static final boolean REVERSE_ID_AND_VALUE_DEFAULT = false;


  @Override
  public TextVertexWriter createVertexWriter(TaskAttemptContext taskAttemptContext) {
    return null;
  }

  protected class IdWithValueVertexWriter extends TextVertexWriterToEachLine {

    private String delimiter;
    private boolean reverseOutput;

    @Override
    public void initialize(TaskAttemptContext context) throws IOException,
        InterruptedException {
      super.initialize(context);
      delimiter = getConf().get(LINE_TOKENIZE_VALUE, LINE_TOKENIZE_VALUE_DEFAULT);
      reverseOutput = getConf().getBoolean(REVERSE_ID_AND_VALUE, REVERSE_ID_AND_VALUE_DEFAULT);
    }

    @Override
    protected Text convertVertexToLine(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
      StringBuilder str = new StringBuilder();
      if (reverseOutput) {
        str.append(vertex.getValue().toString());
        str.append(delimiter);
        str.append(vertex.getId().toString());
      } else {
        str.append(vertex.getId().toString());
        str.append(delimiter);
        str.append(vertex.getValue().toString());
      }
      return new Text(str.toString());
    }
  }

}
