package edu.agh.iga.adi.giraph.core;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class IgaConstants {
  public static final int LEAF_SIZE = 3;

  public static final int ROWS_BOUND_TO_NODE = 6;
  public static final int COLS_BOUND_TO_NODE = 6;
}
