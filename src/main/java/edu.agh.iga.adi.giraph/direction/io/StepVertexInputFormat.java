package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.io.formats.TextVertexValueInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;

/*
TextVertexInputFormat
IntIntTextVertexValueInputFormat
 */
public class StepVertexInputFormat extends TextVertexValueInputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Pattern SEPARATOR = Pattern.compile("[ ]");

  @Override
  public void checkInputSpecs(Configuration conf) {

  }

  @Override
  public List<InputSplit> getSplits(JobContext jobContext, int minSplitCountHint) {
    return null;
  }

  @Override
  public DoubleArrayVertexValueReader createVertexValueReader(InputSplit split, TaskAttemptContext context) {
    final int problemSize = PROBLEM_SIZE.get(getConf());
    Mesh mesh = Mesh.aMesh().withElements(problemSize).build();
    return new DoubleArrayVertexValueReader(mesh);
  }

  public class DoubleArrayVertexValueReader extends TextVertexValueReaderFromEachLineProcessed<double[]> {

    private final Mesh mesh;

    private DoubleArrayVertexValueReader(Mesh mesh) {
      this.mesh = mesh;
    }

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
      super.initialize(inputSplit, context);
    }

    @Override
    protected double[] preprocessLine(Text line) {
      String[] split = SEPARATOR.split(line.toString());
      return Stream.of(split)
          .mapToDouble(Double::parseDouble)
          .toArray();
    }

    @Override
    protected LongWritable getId(double[] line) {
      return new LongWritable((long) line[0]);
    }

    @Override
    protected IgaElementWritable getValue(double[] line) {
      return null;
    }

  }

}

