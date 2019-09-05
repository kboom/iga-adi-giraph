package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import org.apache.giraph.graph.Computation;

public interface ComputationResolver {
  Class<? extends Computation> computationFor(DirectionTree tree, long step);
}
