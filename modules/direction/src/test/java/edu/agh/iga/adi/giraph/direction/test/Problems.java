package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.problem.Problem;

import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;

@Deprecated
public class Problems {

  private Problems() {

  }

  public static final Mesh MESH_12 = aMesh().withElements(12).build();
  public static final Mesh MESH_24 = aMesh().withElements(24).build();

  public static final DirectionTree TREE_12 = new DirectionTree(12);
  public static final DirectionTree TREE_24 = new DirectionTree(24);

  public static final Problem LINEAR_PROBLEM = Double::sum;

}
