package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.test.element.IgaElementBuilder;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;

public class DummyProblem {

  public static final DirectionTree directionTree = new DirectionTree(12);
  public static final Mesh MESH = aMesh().withElements(12).build();


  public static IgaVertex BRANCHING_LEAF = vertexOf(directionTree, 4L);

  public static final long LEFT_LEAF_ID = 8L;
  public static IgaVertex LEFT_LEAF = vertexOf(directionTree, LEFT_LEAF_ID);
  public static final long MIDDLE_LEAF_ID = 9L;
  public static IgaVertex MIDDLE_LEAF = vertexOf(directionTree, MIDDLE_LEAF_ID);
  public static final long RIGHT_LEAF_ID = 10L;
  public static IgaVertex RIGHT_LEAF = vertexOf(directionTree, RIGHT_LEAF_ID);

  public static final long CHILD_LEAF_ID = MIDDLE_LEAF_ID;
  public static IgaVertex CHILD_LEAF = MIDDLE_LEAF;

}
