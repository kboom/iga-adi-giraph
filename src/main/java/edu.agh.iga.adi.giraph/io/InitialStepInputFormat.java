package edu.agh.iga.adi.giraph.io;

import edu.agh.iga.adi.giraph.GraphPartition;
import edu.agh.iga.adi.giraph.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.VertexInputFormat;
import org.apache.giraph.io.VertexReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.util.List;

public class InitialStepInputFormat extends VertexInputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  @Override
  public VertexReader<LongWritable, IgaElementWritable, IgaOperationWritable> createVertexReader(
      InputSplit inputSplit,
      TaskAttemptContext taskAttemptContext
  ) {
    return new InMemoryVertexReader();
  }

  @Override
  public void checkInputSpecs(Configuration configuration) {

  }

  @Override
  public List<InputSplit> getSplits(JobContext jobContext, int i) {
    return null;
  }

  private static class InMemoryVertexReader extends VertexReader<LongWritable, IgaElementWritable, IgaOperationWritable> {

    private GraphPartition graphPartition;
    private Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> currentVertex;

    public void initialize(InputSplit inputSplit, TaskAttemptContext context) {

    }

    public boolean nextVertex() {
      if (graphPartition.hasNext()) {
        this.currentVertex = graphPartition.next();
        return true;
      } else {
        return false;
      }
    }

    public Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> getCurrentVertex() {
      return this.currentVertex;
    }

    public void close() {

    }

    public float getProgress() {
      return 0.0F;
    }

  }

}
