package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.io.EdgeInputFormat;
import org.apache.giraph.io.EdgeReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;
import java.util.List;

public final class IgaEdgeInputFormat extends EdgeInputFormat<LongWritable, IgaOperationWritable> {

  @Override
  public void checkInputSpecs(Configuration conf) {

  }

  @Override
  public List<InputSplit> getSplits(JobContext context, int minSplitCountHint) {
    return null;
  }

  @Override
  public EdgeReader<LongWritable, IgaOperationWritable> createEdgeReader(InputSplit split, TaskAttemptContext context) {
    return null;
  }

  public class IgaEdgeReader extends EdgeReader<LongWritable, IgaOperationWritable> {

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {

    }

    @Override
    public boolean nextEdge() throws IOException, InterruptedException {
      return false;
    }

    @Override
    public LongWritable getCurrentSourceId() throws IOException, InterruptedException {
      return null;
    }

    @Override
    public Edge<LongWritable, IgaOperationWritable> getCurrentEdge() throws IOException, InterruptedException {
      return null;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
      return 0;
    }

  }

}
