package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase;
import org.apache.giraph.aggregators.IntOverwriteAggregator;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.hadoop.io.IntWritable;

import static edu.agh.iga.adi.giraph.direction.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.computation.ComputationResolver.computationForStep;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputation.PHASE;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.phaseFor;

/**
 * Computes a one direction of the Alternating Directions Solver.
 */
public class DirectionComputation extends DefaultMasterCompute {

  private DirectionTree tree;

  @Override
  public void initialize() throws IllegalAccessException, InstantiationException {
    int problemSize = PROBLEM_SIZE.get(getConf());
    tree = new DirectionTree(problemSize);
    registerPersistentAggregator(PHASE, IntOverwriteAggregator.class);
  }

  // alternatively org.apache.giraph.examples.scc.SccPhaseMasterCompute in giraph repo
  @Override
  public final void compute() {
    selectPhase();
    selectComputation();
  }

  private void selectComputation() {
    setComputation(computationForStep(getSuperstep()));
  }

  private void selectPhase() {
    if (getSuperstep() > 0) {
      IgaComputationPhase igaComputationPhase = phaseFor(tree, (int) getSuperstep() - 1);
      if (igaComputationPhase != null) {
        setPhase(igaComputationPhase);
      } else {
        haltComputation();
      }
    }
  }

  private void setPhase(IgaComputationPhase phase) {
    setAggregatedValue(PHASE, new IntWritable(phase.ordinal()));
  }

}
