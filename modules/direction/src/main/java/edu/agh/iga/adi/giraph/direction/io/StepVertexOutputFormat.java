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
import org.apache.log4j.Logger;

import java.io.IOException;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.giraph.conf.GiraphConstants.VERTEX_OUTPUT_FORMAT_SUBDIR;
import static org.apache.log4j.Logger.getLogger;

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

  private static final Logger LOG = getLogger(StepVertexOutputFormat.class);

  private static final Text EMPTY_LINE = new Text();

  /**
   * Dirty hack to store vertices only at the end
   */
  public static boolean isLast = false;

  /**
   * Dirty hack to write to the output subdirectory which corresponds to the current step
   */
  public static int step = 0;

  @Override
  public TextVertexWriter createVertexWriter(final TaskAttemptContext context) {
    final int problemSize = PROBLEM_SIZE.get(context.getConfiguration());
    final DirectionTree directionTree = new DirectionTree(problemSize);

    textOutputFormat =
        new GiraphTextOutputFormat() {
          @Override
          protected String getSubdir() {
            val outputDir = VERTEX_OUTPUT_FORMAT_SUBDIR.getWithDefault(getConf(), "step") + "-" + StepVertexOutputFormat.step;
            LOG.info("Output dir - " + outputDir);
            return outputDir;
          }
        };

    return new IdWithValueVertexWriter(directionTree);
  }

  protected class IdWithValueVertexWriter extends TextVertexWriter {

    private static final char DELIMITER = ',';
    private final DirectionTree directionTree;
    private TaskAttemptContext context;
    private int currentStep = StepVertexOutputFormat.step;

    private IdWithValueVertexWriter(DirectionTree directionTree) {
      this.directionTree = directionTree;
    }

    @Override
    public void initialize(TaskAttemptContext context) throws IOException, InterruptedException {
      this.context = context;
      super.initialize(context);
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
      super.close(context);
    }

    @Override
    public void writeVertex(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) throws IOException, InterruptedException {
      LOG.error("ATTEMPT TO WRITE");
      if (StepVertexOutputFormat.isLast) {
        LOG.error("ISLAST");
        if (currentStep != StepVertexOutputFormat.step) {
          LOG.error("NEWSTEP");
          close(getContext());
          initialize(context); // need to refresh
          currentStep = StepVertexOutputFormat.step;
        }
        LOG.error("WRITING");
        getRecordWriter().write(convertVertexToLine(vertex), null);
      }
    }

    private Text convertVertexToLine(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
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
