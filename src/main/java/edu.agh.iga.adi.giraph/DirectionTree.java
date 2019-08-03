package edu.agh.iga.adi.giraph;

public final class DirectionTree {

  private final int height;

  public DirectionTree(int problemSize) {
    this.height = (int) Math.log(problemSize / 3) + 1;
  }

  public int height() {
    return height;
  }

}
