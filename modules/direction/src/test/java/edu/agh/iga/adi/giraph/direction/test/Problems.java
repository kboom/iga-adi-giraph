package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.Problem;

import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;

@Deprecated
public class Problems {

  private Problems() {

  }

  public static final int PROBLEM_12_SIZE = 12;
  public static final int PROBLEM_24_SIZE = 24;

  public static final Mesh MESH_12 = aMesh().withElements(PROBLEM_12_SIZE).build();
  public static final Mesh MESH_24 = aMesh().withElements(PROBLEM_24_SIZE).build();

  public static final DirectionTree TREE_12 = new DirectionTree(PROBLEM_12_SIZE);
  public static final DirectionTree TREE_24 = new DirectionTree(PROBLEM_24_SIZE);

  public static final Problem LINEAR_PROBLEM = Double::sum;

}
