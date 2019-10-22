package edu.agh.iga.adi.giraph.core.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import lombok.NoArgsConstructor;

import static edu.agh.iga.adi.giraph.core.IgaVertex.vertexOf;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Tree12 {

  public static final DirectionTree DIRECTION_TREE = new DirectionTree(12);
  public static final Mesh MESH = Mesh.aMesh().withElements(12).build();

  public static final int INTERIM_2_ID = 2;
  public static IgaVertex INTERIM_2 = vertexOf(DIRECTION_TREE, INTERIM_2_ID);

  public static final int INTERIM_3_ID = 3;
  public static IgaVertex INTERIM_3 = vertexOf(DIRECTION_TREE, INTERIM_3_ID);

  public static final int BRANCH_4_ID = 4;
  public static IgaVertex BRANCH_4 = vertexOf(DIRECTION_TREE, BRANCH_4_ID);

  public static final int BRANCH_5_ID = 5;
  public static IgaVertex BRANCH_5 = vertexOf(DIRECTION_TREE, BRANCH_5_ID);

  public static final int BRANCH_6_ID = 6;
  public static IgaVertex BRANCH_6 = vertexOf(DIRECTION_TREE, BRANCH_6_ID);

  public static final int BRANCH_7_ID = 7;
  public static IgaVertex BRANCH_7 = vertexOf(DIRECTION_TREE, BRANCH_7_ID);

  public static final int LEAF_8_ID = 8;
  public static IgaVertex LEAF_8 = vertexOf(DIRECTION_TREE, LEAF_8_ID);

  public static final int LEAF_9_ID = 9;
  public static IgaVertex LEAF_9 = vertexOf(DIRECTION_TREE, LEAF_9_ID);

  public static final int LEAF_10_ID = 10;
  public static IgaVertex LEAF_10 = vertexOf(DIRECTION_TREE, LEAF_10_ID);

  public static final int LEAF_11_ID = 11;
  public static IgaVertex LEAF_11 = vertexOf(DIRECTION_TREE, LEAF_11_ID);

  public static final int LEAF_12_ID = 12;
  public static IgaVertex LEAF_12 = vertexOf(DIRECTION_TREE, LEAF_12_ID);

  public static final int LEAF_13_ID = 13;
  public static IgaVertex LEAF_13 = vertexOf(DIRECTION_TREE, LEAF_13_ID);

  public static final int LEAF_14_ID = 14;
  public static IgaVertex LEAF_14 = vertexOf(DIRECTION_TREE, LEAF_14_ID);

  public static final int LEAF_15_ID = 15;
  public static IgaVertex LEAF_15 = vertexOf(DIRECTION_TREE, LEAF_15_ID);

  public static final int LEAF_16_ID = 16;
  public static IgaVertex LEAF_16 = vertexOf(DIRECTION_TREE, LEAF_16_ID);

  public static final int LEAF_17_ID = 17;
  public static IgaVertex LEAF_17 = vertexOf(DIRECTION_TREE, LEAF_17_ID);

  public static final int LEAF_18_ID = 18;
  public static IgaVertex LEAF_18 = vertexOf(DIRECTION_TREE, LEAF_18_ID);

  public static final int LEAF_19_ID = 19;
  public static IgaVertex LEAF_19 = vertexOf(DIRECTION_TREE, LEAF_19_ID);

}
