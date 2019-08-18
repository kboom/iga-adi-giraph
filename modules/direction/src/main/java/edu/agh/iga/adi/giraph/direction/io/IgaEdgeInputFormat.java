package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaOperationFactory;
import edu.agh.iga.adi.giraph.core.IgaOperationFactory.DirectedOperation;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.io.EdgeInputFormat;
import org.apache.giraph.io.EdgeReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.apache.log4j.Logger.getLogger;

public final class IgaEdgeInputFormat extends EdgeInputFormat<LongWritable, IgaOperationWritable> {

  private static final Logger LOG = getLogger(IgaEdgeInputFormat.class);

  @Override
  public void checkInputSpecs(Configuration conf) {

  }

  @Override
  public List<InputSplit> getSplits(JobContext context, int minSplitCountHint) {
    final Configuration config = context.getConfiguration();
    final int problemSize = PROBLEM_SIZE.get(config);
    final DirectionTree tree = new DirectionTree(problemSize);
    final IgaTreeSplitter igaTreeSplitter = new IgaTreeSplitter(tree);
    final int heightPartitionCountHint = HEIGHT_PARTITIONS.get(config);
    return igaTreeSplitter.allSplitsFor(heightPartitionCountHint)
        .stream()
        .map(s -> (InputSplit) s)
        .collect(collectingAndThen(toList(), Collections::unmodifiableList));
  }

  @Override
  public EdgeReader<LongWritable, IgaOperationWritable> createEdgeReader(InputSplit split, TaskAttemptContext context) {
    IgaInputSplit vertexSplit = (IgaInputSplit) split;
    final int size = PROBLEM_SIZE.get(getConf());
    final DirectionTree tree = new DirectionTree(size);
    return new IgaEdgeReader(
        IgaOperationFactory.operationsFor(
            tree,
            vertexSplit.getRoot(),
            vertexSplit.getHeight()
        )
    );
  }

  public class IgaEdgeReader extends EdgeReader<LongWritable, IgaOperationWritable> {

    private final Iterator<DirectedOperation> operations;
    private DirectedOperation currentOperation;

    private IgaEdgeReader(Iterator<DirectedOperation> operations) {
      this.operations = operations;
    }

    @Override
    public boolean nextEdge() {
      if (operations.hasNext()) {
        currentOperation = operations.next();
        if (LOG.isDebugEnabled()) {
          LOG.debug("Producing operation " + currentOperation);
        }
        return true;
      } else {
        return false;
      }
    }

    @Override
    public LongWritable getCurrentSourceId() {
      return new LongWritable(currentOperation.getSrc().id());
    }

    @Override
    public Edge<LongWritable, IgaOperationWritable> getCurrentEdge() {
      return EdgeFactory.create(
          new LongWritable(currentOperation.getDst().id()),
          new IgaOperationWritable(currentOperation.getOperation())
      );
    }

    @Override
    public void close() {

    }

    @Override
    public float getProgress() {
      return 0;
    }

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) {

    }

  }

}
