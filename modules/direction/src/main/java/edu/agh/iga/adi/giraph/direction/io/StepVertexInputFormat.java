package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.commons.RowMajorArray;
import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.ElementFactory;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.io.formats.TextVertexValueInputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.ojalgo.matrix.store.MatrixStore;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

/**
 * The initializer that works with {@link edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers#COEFFICIENTS_PROBLEM}
 * <p>
 * Expect vertex input format:
 * <pre>
 *   [branch vertexId A] [coefficients for 1]
 *   [branch vertexId B] [coefficients for 2]
 * </pre>
 * <p>
 * The ordering of vertices is not important but vertex file splits should match the partitioning scheme so
 * data doesn't have to be transferred over the network.
 */
public class StepVertexInputFormat extends TextVertexValueInputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Pattern SEPARATOR = Pattern.compile("[ ,]");

  @Override
  public DoubleArrayVertexValueReader createVertexValueReader(InputSplit split, TaskAttemptContext context) {
    return new DoubleArrayVertexValueReader();
  }

  public class DoubleArrayVertexValueReader extends TextVertexValueReaderFromEachLineProcessed<double[]> {

    private ElementFactory elementFactory;
    private DirectionTree directionTree;
    private Mesh mesh;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
      super.initialize(inputSplit, context);
      final int problemSize = PROBLEM_SIZE.get(getConf());
      mesh = Mesh.aMesh().withElements(problemSize).build();
      directionTree = new DirectionTree(problemSize);
      elementFactory = new HorizontalElementFactory(mesh);
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
    protected IgaElementWritable getValue(final double[] line) {
      return new IgaElementWritable(elementFactory.createBranchElement(vertex(line[0]), asMatrix(line)));
    }

    private IgaVertex vertex(double v) {
      return vertexOf(directionTree, (long) v);
    }

    private MatrixStore<Double> asMatrix(double[] line) {
      return FACTORY.builder()
          .makeWrapper(new RowMajorArray(3, mesh.getDofsX(), 1, line))
          .get();
    }

  }

}

