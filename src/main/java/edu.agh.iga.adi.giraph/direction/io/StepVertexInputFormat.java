package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.ElementFactory;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.CoefficientSolution;
import edu.agh.iga.adi.giraph.core.problem.HeatTransferProblem;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.io.formats.TextVertexValueInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;

/*
TextVertexInputFormat
IntIntTextVertexValueInputFormat
 */

/**
 * The vertex input format is:
 * <pre>
 *   [vertexId 1] [coefficients for 1]
 *   [vertexId 2] [coefficients for 2]
 * </pre>
 */
public class StepVertexInputFormat extends TextVertexValueInputFormat<LongWritable, IgaElementWritable, IgaOperationWritable> {

  private static final Pattern SEPARATOR = Pattern.compile("[ ]");

  private ElementFactory elementFactory;
  private DirectionTree directionTree;
  private Mesh mesh;

  @Override
  public void checkInputSpecs(Configuration conf) {
    final int problemSize = PROBLEM_SIZE.get(getConf());
    mesh = Mesh.aMesh().withElements(problemSize).build();
    directionTree = new DirectionTree(problemSize);
    elementFactory = new HorizontalElementFactory(mesh, directionTree);
  }

  @Override
  public DoubleArrayVertexValueReader createVertexValueReader(InputSplit split, TaskAttemptContext context) {
    return new DoubleArrayVertexValueReader();
  }

  public class DoubleArrayVertexValueReader extends TextVertexValueReaderFromEachLineProcessed<double[]> {

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
    protected IgaElementWritable getValue(final double[] line) {
      IgaVertex igaVertex = vertexOf(directionTree, (long) line[0]);
      final int dofsX = mesh.getDofsX();

      MatrixStore<Double> sol = PrimitiveDenseStore.FACTORY.builder()
          .makeWrapper(new DoubleArrayAccess(3, dofsX, 1, line))
          .get();

      CoefficientSolution coefficientSolution = new CoefficientSolution(mesh, sol);
      Problem problem = new HeatTransferProblem(coefficientSolution);
      IgaElement igaElement = elementFactory.createElement(problem, igaVertex);
      return new IgaElementWritable(igaElement);
    }

  }

}

