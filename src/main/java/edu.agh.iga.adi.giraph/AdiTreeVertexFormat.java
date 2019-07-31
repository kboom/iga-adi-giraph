package edu.agh.iga.adi.giraph;

import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.VertexInputFormat;
import org.apache.giraph.io.VertexReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.util.List;

public class AdiTreeVertexFormat extends VertexInputFormat<VertexIdWritable, VertexWritable, EdgeWritable> {

  @Override
  public VertexReader<VertexIdWritable, VertexWritable, EdgeWritable> createVertexReader(
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

  private static class InMemoryVertexReader extends VertexReader<VertexIdWritable, VertexWritable, EdgeWritable> {

    private GraphPartition graphPartition;
    private Vertex<VertexIdWritable, VertexWritable, EdgeWritable> currentVertex;

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

    public Vertex<VertexIdWritable, VertexWritable, EdgeWritable> getCurrentVertex() {
      return this.currentVertex;
    }

    public void close() {

    }

    public float getProgress() {
      return 0.0F;
    }

  }

}
