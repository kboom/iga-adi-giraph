package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.direction.computation.ComputationResolver;
import edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase;
import org.apache.giraph.aggregators.IntOverwriteAggregator;
import org.apache.giraph.graph.Computation;
import org.apache.giraph.master.DefaultMasterCompute;
import org.apache.hadoop.io.IntWritable;
import org.apache.log4j.Logger;

import java.util.Optional;

import static edu.agh.iga.adi.giraph.IgaConfiguration.PROBLEM_SIZE;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputation.PHASE;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.getPhase;
import static edu.agh.iga.adi.giraph.direction.computation.IgaComputationPhase.phaseFor;

/**
 * Computes a one direction of the Alternating Directions Solver.
 */
public class DirectionComputation extends DefaultMasterCompute {

  private static final Logger LOG = Logger.getLogger(DirectionComputation.class);

  private ComputationResolver computationResolver;
  private DirectionTree tree;

  @Override
  public void initialize() throws IllegalAccessException, InstantiationException {
    int problemSize = PROBLEM_SIZE.get(getConf());
    tree = new DirectionTree(problemSize);
    computationResolver = new ComputationResolver(tree);

    registerPersistentAggregator(PHASE, IntOverwriteAggregator.class);
  }

  // alternatively org.apache.giraph.examples.scc.SccPhaseMasterCompute in giraph repo
  @Override
  public final void compute() {
    selectPhase();
    selectComputation();
  }

  private void selectComputation() {
    Optional<Class<? extends Computation>> nextComputation = computationResolver.computationForStep(getSuperstep());
    if (nextComputation.isPresent()) {
      setComputation(nextComputation.get());
    } else {
      haltComputation();
    }
  }

  private void selectPhase() {
    if(getSuperstep() > 0) {
      setPhase(phaseFor(tree, (int) getSuperstep() - 1));
    }
  }

  private void setPhase(IgaComputationPhase phase) {
    setAggregatedValue(PHASE, new IntWritable(phase.ordinal()));
  }

}
