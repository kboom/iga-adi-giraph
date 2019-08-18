package edu.agh.iga.adi.giraph.core;

import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public class IgaElement {

  private static final int COLS_BOUND_TO_NODE = 6;

  public final long id;

  public final PrimitiveDenseStore ma;
  public final PrimitiveDenseStore mb;
  public final PrimitiveDenseStore mx;

  private IgaElement(
      long id,
      PrimitiveDenseStore ma,
      PrimitiveDenseStore mb,
      PrimitiveDenseStore mx
  ) {
    this.id = id;
    this.ma = ma;
    this.mb = mb;
    this.mx = mx;
  }

  public static IgaElement igaElement(long id, int elements) {
    return new IgaElement(
        id,
        PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, COLS_BOUND_TO_NODE),
        PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, elements),
        PrimitiveDenseStore.FACTORY.makeZero(ROWS_BOUND_TO_NODE, elements)
    );
  }

  public static IgaElement igaElement(
      long id,
      PrimitiveDenseStore ma,
      PrimitiveDenseStore mb,
      PrimitiveDenseStore mx
  ) {
    return new IgaElement(id, ma, mb, mx);
  }

}