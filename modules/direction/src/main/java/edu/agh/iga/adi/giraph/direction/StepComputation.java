package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.apache.giraph.aggregators.IntOverwriteAggregator;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.hadoop.io.IntWritable;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_START;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationResolver.computationForStep;

/**
 * Computes one full time step of the Alternating Directions Solver.
 */
public class StepComputation extends DefaultMasterCompute {

  private DirectionTree tree;
  private int currentComputationStart;
  private Class<? extends Computation> currentComputation;

  @Override
  public void initialize() throws IllegalAccessException, InstantiationException {
    int problemSize = PROBLEM_SIZE.get(getConf());
    tree = new DirectionTree(problemSize);
    registerPersistentAggregator(COMPUTATION_START, IntOverwriteAggregator.class);
  }

  @Override
  public final void compute() {
    Class<? extends Computation> nextComputation = computationForStep(tree, getSuperstep());
    if (nextComputation != null) {
      setComputation(nextComputation);
      if (currentComputation != nextComputation) {
        currentComputationStart = (int) getSuperstep();
        setComputationStart(currentComputationStart);
      } else {
        setComputationStart(currentComputationStart);
      }
      currentComputation = nextComputation;
    } else {
      haltComputation();
    }
  }

  private void setComputationStart(long start) {
    setAggregatedValue(COMPUTATION_START, new IntWritable((int) start));
  }

}
