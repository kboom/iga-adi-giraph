package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.computation.IgaComputationFactory;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import edu.agh.iga.adi.giraph.test.IgaTestGraph;
import edu.agh.iga.adi.giraph.test.TestGraphAssertions;
import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.MasterCompute;
import org.apache.giraph.utils.TestGraph;
import org.apache.hadoop.io.LongWritable;

import java.util.function.Consumer;
import java.util.function.Function;

import static edu.agh.iga.adi.giraph.test.TestGraphAssertions.assertThat;
import static org.apache.giraph.conf.GiraphConstants.COMPUTATION_FACTORY_CLASS;
import static org.apache.giraph.utils.InternalVertexRunner.runWithInMemoryOutput;

public class ComputationTestRunner {

  public static ComputationRunnerPreconditions whenComputation(
      Class<? extends MasterCompute> masterCompute,
      Class<? extends Computation> computation
  ) {
    GiraphConfiguration conf = new GiraphConfiguration();
    conf.setComputationClass(computation);
    conf.setMasterComputeClass(masterCompute);
    COMPUTATION_FACTORY_CLASS.set(conf, IgaComputationFactory.class);
    return new ComputationRunnerPreconditions(conf);
  }

  public static class ComputationRunnerPreconditions {
    private final GiraphConfiguration config;

    private ComputationRunnerPreconditions(GiraphConfiguration config) {
      this.config = config;
    }

    public ComputationTestRunAssertions isRunForGraph(Function<IgaTestGraph, IgaTestGraph> modifier) {
      TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> graph = new TestGraph<>(config);
      modifier.apply(new IgaTestGraph(graph));
      try {
        return new ComputationTestRunAssertions(runWithInMemoryOutput(config, graph));
      } catch (Exception e) {
        throw new IllegalStateException("Could not finish the computations", e);
      }
    }

  }

  public static class ComputationTestRunAssertions {

    private final TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> output;

    private ComputationTestRunAssertions(TestGraph<LongWritable, IgaElementWritable, IgaOperationWritable> output) {
      this.output = output;
    }

    public ComputationTestRunAssertions thenAssertThatGraph(Consumer<TestGraphAssertions> consumer) {
      consumer.accept(assertThat(output));
      return this;
    }

  }

}
