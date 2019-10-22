package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static edu.agh.iga.adi.giraph.core.Mesh.aMesh;

@Deprecated
public class SmallProblem {

  public static final DirectionTree DIRECTION_TREE = new DirectionTree(12);
  public static final Mesh MESH = aMesh().withElements(12).build();

  public static final int LEFT_INTERIM_ID = 2;
  public static IgaVertex LEFT_INTERIM = vertexOf(DIRECTION_TREE, LEFT_INTERIM_ID);

  public static final int RIGHT_INTERIM_ID = 3;
  public static IgaVertex RIGHT_INTERIM = vertexOf(DIRECTION_TREE, RIGHT_INTERIM_ID);

  public static final int LEFT_BRANCH_ID = 4;
  public static IgaVertex LEFT_BRANCH = vertexOf(DIRECTION_TREE, LEFT_BRANCH_ID);
  public static final int RIGHT_BRANCH_ID = 5;
  public static IgaVertex RIGHT_BRANCH = vertexOf(DIRECTION_TREE, RIGHT_BRANCH_ID);

  public static final int LEFT_LEAF_ID = 8;
  public static IgaVertex LEFT_LEAF = vertexOf(DIRECTION_TREE, LEFT_LEAF_ID);
  public static final int MIDDLE_LEAF_ID = 9;
  public static IgaVertex MIDDLE_LEAF = vertexOf(DIRECTION_TREE, MIDDLE_LEAF_ID);
  public static final int RIGHT_LEAF_ID = 10;
  public static IgaVertex RIGHT_LEAF = vertexOf(DIRECTION_TREE, RIGHT_LEAF_ID);

  public static final IgaVertex INTERIM = LEFT_INTERIM;
  public static final int INTERIM_ID = LEFT_INTERIM_ID;
  public static final IgaVertex BRANCH = LEFT_BRANCH;
  public static final int BRANCH_ID = LEFT_BRANCH_ID;
  public static final IgaVertex LEAF = MIDDLE_LEAF;
  public static final int LEAF_ID = MIDDLE_LEAF_ID;

}
