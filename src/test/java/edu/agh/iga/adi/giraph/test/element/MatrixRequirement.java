package edu.agh.iga.adi.giraph.test.element;

import org.ojalgo.structure.Access2D;
import org.ojalgo.structure.Mutate2D;
import org.ojalgo.structure.Transformation2D;

public abstract class MatrixRequirement implements Transformation2D<Double> {

  @Override
  public final <T extends Mutate2D.ModifiableReceiver<Double> & Access2D<Double>> void transform(T t) {
    t.loopAll((row, col) -> t.set(row, col, valueAt(t.get(row, col), (int) row, (int) col)));
  }

  public abstract double valueAt(double current, int row, int col);

}
