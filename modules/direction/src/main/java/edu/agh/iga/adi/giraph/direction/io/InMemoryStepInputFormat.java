package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.LeafVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.RootVertex;
import edu.agh.iga.adi.giraph.core.factory.ElementFactory;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import lombok.val;
import org.apache.giraph.io.VertexValueInputFormat;
import org.apache.giraph.io.VertexValueReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static edu.agh.iga.adi.giraph.core.IgaVertexFactory.childrenOf;
import static edu.agh.iga.adi.giraph.core.IgaVertexFactory.familyOf;
import static edu.agh.iga.adi.giraph.direction.ContextFactory.meshOf;
import static edu.agh.iga.adi.giraph.direction.computation.ProblemFactoryResolver.getProblemFactory;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.apache.log4j.Logger.getLogger;

/**
 * A step that uses no coefficients from HDFS.
 * This is useful mainly for solving dummy problems which initial values can be found through math formulas.
 * Real problems tend to need a series of values which correspond to a bitmap surface which needs to be projected onto BSpline basis.
 */
public class InMemoryStepInputFormat extends VertexValueInputFormat<IntWritable, IgaElementWritable> {

  private static final Logger LOG = getLogger(InMemoryStepInputFormat.class);

  @Override
  public VertexValueReader<IntWritable, IgaElementWritable> createVertexValueReader(InputSplit split, TaskAttemptContext context) {
    IgaInputSplit vertexSplit = (IgaInputSplit) split;
    val mesh = meshOf(getConf());
    val pf = getProblemFactory(getConf());
    val elementFactory = new HorizontalElementFactory(mesh, pf.coefficients());
    return new StaticProblemInputReader(
        elementFactory,
        INITIAL_PROBLEM_TYPE.get(getConf()).createProblem(getConf()),
        vertices(vertexSplit)
    );
  }

  private static Iterator<IgaVertex> vertices(IgaInputSplit vertexSplit) {
    final IgaVertex root = vertexSplit.getRoot();
    final int height = vertexSplit.getHeight();
    if (root.is(RootVertex.class)) {
      return familyOf(root, height);
    } else {
      return childrenOf(root, height);
    }
  }

  @Override
  public void checkInputSpecs(Configuration conf) {

  }

  @Override
  public List<InputSplit> getSplits(JobContext context, int minSplitCountHint) {
    val config = context.getConfiguration();
    val problemSize = PROBLEM_SIZE.get(config);
    val tree = new DirectionTree(problemSize);
    val igaTreeSplitter = new IgaTreeSplitter(tree);
    return igaTreeSplitter.allSplitsFor(minSplitCountHint)
        .stream()
        .map(s -> (InputSplit) s)
        .collect(collectingAndThen(toList(), Collections::unmodifiableList));
  }

  public static final class StaticProblemInputReader extends VertexValueReader<IntWritable, IgaElementWritable> {

    private final Iterator<IgaVertex> vertices;
    private final ElementFactory elementFactory;
    private final Problem problem;
    private IgaVertex currentVertex;

    private StaticProblemInputReader(ElementFactory elementFactory, Problem problem, Iterator<IgaVertex> vertices) {
      this.problem = problem;
      this.vertices = vertices;
      this.elementFactory = elementFactory;
    }

    @Override
    public IntWritable getCurrentVertexId() {
      return new IntWritable(currentVertex.id());
    }

    @Override
    public IgaElementWritable getCurrentVertexValue() {
      if (currentVertex.is(LeafVertex.class)) {
        return new IgaElementWritable(elementFactory.createLeafElement(problem, currentVertex));
      } else {
        return null;
      }
    }

    @Override
    public boolean nextVertex() {
      if (vertices.hasNext()) {
        currentVertex = vertices.next();
        if (LOG.isTraceEnabled()) {
          LOG.trace("Producing vertex " + currentVertex);
        }
        return true;
      } else {
        return false;
      }
    }

    @Override
    public void close() {

    }

    @Override
    public float getProgress() {
      return 0;
    }

  }

}
