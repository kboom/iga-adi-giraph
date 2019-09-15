package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex.BranchVertex;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import lombok.val;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.GiraphTextOutputFormat;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.ENDING_SUPER_STEP;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.STEP_COUNTER;
import static edu.agh.iga.adi.giraph.direction.io.NoOutputFormat.NO_OUTPUT_FORMAT;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.giraph.conf.GiraphConstants.VERTEX_OUTPUT_FORMAT_SUBDIR;

/**
 * Extracts the solution from the solver. The solution is the large matrix of coefficients for our BSpline basis functions.
 * <p>
 * The files contain coefficients for all branch vertices in the following format:
 * <pre>
 *   [branch vertexId A] [coefficients for 1]
 *   [branch vertexId B] [coefficients for 2]
 * </pre>
 * <p>
 * The files produced can be used directly for the next steps simulations using {@link StepVertexInputFormat}.
 * <p>
 * In reality, the solver runs this as many times as there are super steps and for each super step
 * that is the last super step of any given step we store the values in a subdirectory so we collect all results.
 */
public class StepVertexOutputFormat extends TextVertexOutputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Text EMPTY_LINE = new Text();

  @Override
  public TextVertexWriter createVertexWriter(TaskAttemptContext context) {
    if (endingSuperStep(context)) {
      setRealOutputFormat(context);
      final int problemSize = PROBLEM_SIZE.get(context.getConfiguration());
      final DirectionTree directionTree = new DirectionTree(problemSize);
      return new IdWithValueVertexWriter(directionTree);
    } else {
      setNoOutputFormat();
      return null;
    }
  }

  private void setRealOutputFormat(TaskAttemptContext ctx) {
    val step = ctx.getCounter(STEP_COUNTER).getValue();
    textOutputFormat =
        new GiraphTextOutputFormat() {
          @Override
          protected String getSubdir() {
            return VERTEX_OUTPUT_FORMAT_SUBDIR.get(getConf()) + step;
          }
        };
  }

  private void setNoOutputFormat() {
    textOutputFormat = NO_OUTPUT_FORMAT;
  }

  private boolean endingSuperStep(TaskAttemptContext ctx) {
    return ctx.getCounter(ENDING_SUPER_STEP).getValue() == 1;
  }

  protected class IdWithValueVertexWriter extends TextVertexWriterToEachLine {

    private static final char DELIMITER = ',';
    private final DirectionTree directionTree;

    private IdWithValueVertexWriter(DirectionTree directionTree) {
      this.directionTree = directionTree;
    }

    @Override
    protected Text convertVertexToLine(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
      val vid = vertex.getId().get();
      val v = vertexOf(directionTree, vid);
      if (v.is(BranchVertex.class)) {
        val mx = vertex.getValue().getElement().mx;
        if (v.isLeading()) {
          // we want to store all 5
          return new Text(
              vid + " " + join(mx.data, DELIMITER)
          );
        } else {
          val cols = (int) mx.countColumns();
          val rows = (int) mx.countRows();
          // we want to store only 3 last ones
          StringBuilder sb = new StringBuilder(cols * 3 + 1);
          val data = mx.data;
          for (int r = 2; r < 5; r++) {
            for (int c = 0; c < cols; c++) {
              sb.append(data[c * rows + r]);
              sb.append(",");
            }
          }

          return new Text(
              vid + " " + sb.deleteCharAt(sb.length() - 1).toString()
          );
        }
      } else {
        return EMPTY_LINE;
      }
    }

  }

}
