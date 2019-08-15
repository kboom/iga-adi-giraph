package edu.agh.iga.adi.giraph.direction.io;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.IgaVertex.RootVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.ElementFactory;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.ConstantProblem;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import org.apache.giraph.io.VertexValueInputFormat;
import org.apache.giraph.io.VertexValueReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static edu.agh.iga.adi.giraph.IgaConfiguration.HEIGHT_PARTITIONS;
import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.core.IgaVertexFactory.childrenOf;
import static edu.agh.iga.adi.giraph.core.IgaVertexFactory.familyOf;
import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.apache.log4j.Logger.getLogger;

/**
 * A step that uses no coefficients from HDFS.
 * This is useful mainly for solving dummy problems which initial values can be found through math formulas.
 * Real problems tend to need a series of values which correspond to a bitmap surface which needs to be projected onto BSpline basis.
 */
public class InMemoryStepInputFormat extends VertexValueInputFormat<LongWritable, IgaElementWritable> {

  private static final Logger LOG = getLogger(InMemoryStepInputFormat.class);

  @Override
  public VertexValueReader<LongWritable, IgaElementWritable> createVertexValueReader(InputSplit split, TaskAttemptContext context) {
    IgaInputSplit vertexSplit = (IgaInputSplit) split;
    final int size = PROBLEM_SIZE.get(getConf());
    final Mesh mesh = aMesh().withElements(size).build();
    final ElementFactory elementFactory = new HorizontalElementFactory(mesh);
    return new StaticProblemInputReader(
        elementFactory,
        new ConstantProblem(), // todo for now
        vertices(vertexSplit)
    );
  }

  private Iterator<IgaVertex> vertices(IgaInputSplit vertexSplit) {
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

  public final class StaticProblemInputReader extends VertexValueReader<LongWritable, IgaElementWritable> {

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
    public LongWritable getCurrentVertexId() {
      return new LongWritable(currentVertex.id());
    }

    @Override
    public IgaElementWritable getCurrentVertexValue() {
      return new IgaElementWritable(elementFactory.createElement(problem, currentVertex));
    }

    @Override
    public boolean nextVertex() {
      if (vertices.hasNext()) {
        currentVertex = vertices.next();
        if (LOG.isDebugEnabled()) {
          LOG.debug("Producing vertex " + currentVertex);
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
