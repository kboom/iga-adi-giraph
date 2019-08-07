package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;

public class DummyProblem {

  public static final DirectionTree DIRECTION_TREE = new DirectionTree(12);
  public static final Mesh MESH = aMesh().withElements(12).build();


  public static final long LEFT_INTERIM_ID = 4L;
  public static IgaVertex LEFT_INTERIM = vertexOf(DIRECTION_TREE, LEFT_INTERIM_ID);

  public static final long RIGHT_INTERIM_ID = 5L;
  public static IgaVertex RIGHT_INTERIM = vertexOf(DIRECTION_TREE, RIGHT_INTERIM_ID);

  public static final long LEFT_BRANCH_ID = 8L;
  public static IgaVertex LEFT_BRANCH = vertexOf(DIRECTION_TREE, LEFT_BRANCH_ID);
  public static final long RIGHT_BRANCH_ID = 9L;
  public static IgaVertex RIGHT_BRANCH = vertexOf(DIRECTION_TREE, RIGHT_BRANCH_ID);

  public static final long LEFT_LEAF_ID = 16L;
  public static IgaVertex LEFT_LEAF = vertexOf(DIRECTION_TREE, LEFT_LEAF_ID);
  public static final long MIDDLE_LEAF_ID = 17L;
  public static IgaVertex MIDDLE_LEAF = vertexOf(DIRECTION_TREE, MIDDLE_LEAF_ID);
  public static final long RIGHT_LEAF_ID = 18L;
  public static IgaVertex RIGHT_LEAF = vertexOf(DIRECTION_TREE, RIGHT_LEAF_ID);

  public static final IgaVertex INTERIM = LEFT_INTERIM;
  public static final long INTERIM_ID = LEFT_INTERIM_ID;
  public static final IgaVertex BRANCH = LEFT_BRANCH;
  public static final long BRANCH_ID = LEFT_BRANCH_ID;
  public static final IgaVertex LEAF = MIDDLE_LEAF;
  public static final long LEAF_ID = MIDDLE_LEAF_ID;
}
