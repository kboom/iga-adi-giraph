package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationFactory;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.direction.test.IgaTestGraph;
import edu.agh.iga.adi.giraph.direction.test.SmallProblem;
import edu.agh.iga.adi.giraph.direction.test.assertion.TestGraphAssertions;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.edge.HashMapEdges;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.MasterCompute;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.IntWritable;

import java.util.function.Consumer;
import java.util.function.Function;

import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.setIgaStoreTypes;
import static edu.agh.iga.adi.giraph.direction.test.SmallProblem.DIRECTION_TREE;
import static edu.agh.iga.adi.giraph.direction.test.assertion.TestGraphAssertions.assertThatGraph;
import static org.apache.giraph.conf.GiraphConstants.COMPUTATION_FACTORY_CLASS;
import static org.apache.giraph.conf.GiraphConstants.USE_MESSAGE_SIZE_ENCODING;
import static org.apache.giraph.utils.InternalVertexRunner.runWithInMemoryOutput;

public class ComputationTestRunner {

  public static ComputationRunnerPreconditions whenComputation(
      Class<? extends MasterCompute> masterCompute,
      Class<? extends Computation> computation
  ) {
    GiraphConfiguration conf = new GiraphConfiguration();
    conf.setComputationClass(computation);
    conf.setMasterComputeClass(masterCompute);
    conf.setWorkerContextClass(IgaWorkerContext.class);
    conf.setOutEdgesClass(HashMapEdges.class);
    setIgaStoreTypes(conf);
    COMPUTATION_FACTORY_CLASS.set(conf, IgaComputationFactory.class);
    USE_MESSAGE_SIZE_ENCODING.set(conf, false);
    return new ComputationRunnerPreconditions(conf);
  }

  public static class ComputationRunnerPreconditions {
    private final GiraphConfiguration config;

    private DirectionTree tree = DIRECTION_TREE;
    private Mesh mesh = SmallProblem.MESH;

    private ComputationRunnerPreconditions(GiraphConfiguration config) {
      this.config = config;
    }

    public ComputationRunnerPreconditions ofProblemSize(int problemSize) {
      PROBLEM_SIZE.set(config, problemSize);
      tree = new DirectionTree(problemSize);
      mesh = Mesh.aMesh().withElements(problemSize).build();
      return this;
    }

    public ComputationTestRunAssertions isRunForGraph(Function<IgaTestGraph, IgaTestGraph> modifier) {
      TestGraph<IntWritable, IgaElementWritable, IgaOperationWritable> graph = new TestGraph<>(config);
      modifier.apply(new IgaTestGraph(graph, mesh, tree));
      try {
        return new ComputationTestRunAssertions(runWithInMemoryOutput(config, graph));
      } catch (Exception e) {
        throw new IllegalStateException("Could not finish the computations", e);
      }
    }

  }

  public static class ComputationTestRunAssertions {

    private final TestGraph<IntWritable, IgaElementWritable, IgaOperationWritable> output;

    private ComputationTestRunAssertions(TestGraph<IntWritable, IgaElementWritable, IgaOperationWritable> output) {
      this.output = output;
    }

    public ComputationTestRunAssertions thenAssertThatGraph(Consumer<TestGraphAssertions> consumer) {
      consumer.accept(assertThatGraph(output));
      return this;
    }

  }

}
