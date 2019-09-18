package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.ComputationResolver;
import edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialisationComputation;
import lombok.val;
import org.apache.giraph.aggregators.BooleanOverwriteAggregator;
import org.apache.giraph.aggregators.IntOverwriteAggregator;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Counter;

import static edu.agh.iga.adi.giraph.direction.Flags.INT_FALSE;
import static edu.agh.iga.adi.giraph.direction.Flags.INT_TRUE;
import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.*;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.*;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.COMPUTATION_ITERATION;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.LAST_COMPUTATION_FLAG;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.COEFFICIENTS_PROBLEM;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationResolvers.computationResolverFor;
import static edu.agh.iga.adi.giraph.direction.logging.TimeLogger.logTime;
import static edu.agh.iga.adi.giraph.direction.logging.TimeLogger.timeReducer;

/**
 * Computes one full time step of the Alternating Directions Solver.
 */
public class IterativeComputation extends DefaultMasterCompute {

  private DirectionTree tree;
  private Class<? extends Computation> previousComputation;
  private int stepCount;

  private Counter stepCounter;
  private Counter localSuperStep;
  private Counter computationIteration;
  private Counter endingSuperStep;

  @Override
  public void initialize() throws IllegalAccessException, InstantiationException {
    stepCounter = getContext().getCounter(STEP_COUNTER);
    localSuperStep = getContext().getCounter(LOCAL_SUPERSTEP);
    endingSuperStep = getContext().getCounter(ENDING_SUPER_STEP);
    computationIteration = getContext().getCounter(COMPUTATION_ITERATION_COUNTER);

    stepCount = STEP_COUNT.get(getConf());
    int problemSize = PROBLEM_SIZE.get(getConf());
    tree = new DirectionTree(problemSize);
    registerAggregator(COMPUTATION_ITERATION, IntOverwriteAggregator.class);
    registerAggregator(LAST_COMPUTATION_FLAG, BooleanOverwriteAggregator.class);

    stepCounter.setValue(0);
    localSuperStep.setValue(-1);
    endingSuperStep.setValue(INT_FALSE);
  }

  @Override
  public final void compute() {
    endingSuperStep.setValue(INT_FALSE);

    if (getSuperstep() > 0) {
      logTimers();
    }

    localSuperStep.increment(1);
    long localSuperStepNo = localSuperStep.getValue();
    val computationResolver = currentComputationResolver();
    Class<? extends Computation> currentComputation = computationResolver.computationFor(tree, localSuperStepNo);
    if (currentComputation != null) {
      setComputation(currentComputation);
      setLastRunOfComputation(false);

      if (previousComputation != currentComputation) {
        computationIteration.setValue(0);
      } else {
        computationIteration.increment(1);
      }

      setComputationIteration(computationIteration.getValue());

      previousComputation = currentComputation;

      Class<? extends Computation> nextComputation = computationResolver.computationFor(tree, localSuperStepNo + 1);
      if (nextComputation != currentComputation) {
        setLastRunOfComputation(true);
      }

      if (nextComputation == null) {
        endingSuperStep.setValue(INT_TRUE);
      }
    } else {
      stepCounter.increment(1);
      localSuperStep.setValue(0);
      setComputationIteration(0);
      computationIteration.setValue(0);
      if (stepCounter.getValue() >= stepCount) {
        haltComputation();
      } else {
        previousComputation = InitialisationComputation.class;
        setComputation(InitialisationComputation.class);
      }
    }
  }

  /**
   * The first initialisation will use {@link FIRST_INITIALISATION_TYPE}
   * and the next steps will use the {@link COEFFICIENTS_PROBLEM}.
   * <p>
   * This is natural as the first step initialisation is configurable but the rest uses the internal representation
   * of the solver which are the spline coefficients. Note that the output format might actually be different
   * and it will not affect the computations (as we never have to read the coefficients from HDFS after initial load).
   */
  private ComputationResolver currentComputationResolver() {
    return stepCounter.getValue() == 0
        ? computationResolverFor(FIRST_INITIALISATION_TYPE.get(getConf()))
        : COEFFICIENTS_PROBLEM;
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

  private void setComputationIteration(long iteration) {
    setAggregatedValue(COMPUTATION_ITERATION, new IntWritable((int) iteration));
  }

  private void setLastRunOfComputation(boolean lastComputation) {
    setAggregatedValue(LAST_COMPUTATION_FLAG, new BooleanWritable(lastComputation));
  }

}
