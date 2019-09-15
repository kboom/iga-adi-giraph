package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.ComputationResolver;
import lombok.val;
import org.apache.giraph.aggregators.IntOverwriteAggregator;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Counter;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.LOCAL_SUPERSTEP;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.STEP_COUNTER;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_START;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.computationResolverFor;
import static edu.agh.iga.adi.giraph.direction.logging.TimeLogger.logTime;
import static edu.agh.iga.adi.giraph.direction.logging.TimeLogger.timeReducer;

/**
 * Computes one full time step of the Alternating Directions Solver.
 */
public class StepComputation extends DefaultMasterCompute {

  private ComputationResolver computationResolver;
  private DirectionTree tree;
  private int currentComputationStart;
  private Class<? extends Computation> currentComputation;
  private int stepCount;

  private final Counter stepCounter = getContext().getCounter(STEP_COUNTER);
  private final Counter localSuperStep = getContext().getCounter(LOCAL_SUPERSTEP);

  @Override
  public void initialize() throws IllegalAccessException, InstantiationException {
    stepCount = STEP_COUNT.get(getConf());
    int problemSize = PROBLEM_SIZE.get(getConf());
    tree = new DirectionTree(problemSize);
    computationResolver = computationResolverFor(INITIALISATION_TYPE.get(getConf()));
    registerAggregator(COMPUTATION_START, IntOverwriteAggregator.class);

    // so that the first increment is 0
    stepCounter.setValue(-1);
    localSuperStep.setValue(-1);
  }

  @Override
  public final void compute() {
    if (getSuperstep() > 0) {
      logTimers();
    }
    Class<? extends Computation> nextComputation = computationResolver.computationFor(tree, getSuperstep());
    if (nextComputation != null) {
      localSuperStep.increment(1);
      setComputation(nextComputation);
      if (currentComputation != nextComputation) {
        currentComputationStart = (int) getSuperstep();
        setComputationStart(currentComputationStart);
      } else {
        setComputationStart(currentComputationStart);
      }
      currentComputation = nextComputation;
    } else {
      localSuperStep.setValue(0);
      if (stepCounter.getValue() >= stepCount) {
        haltComputation();
      }
    }
    stepCounter.increment(1);
  }

  private void logTimers() {
    val workers = getWorkerInfoList();
    for (int w = 0; w < workers.size(); w++) {
      final LongWritable reduced = getReduced(timeReducer(w));
      if (reduced != null) {
        logTime(w, getSuperstep() - 1, reduced.get());
      }
    }
  }

  private void setComputationStart(long start) {
    setAggregatedValue(COMPUTATION_START, new IntWritable((int) start));
  }

}
