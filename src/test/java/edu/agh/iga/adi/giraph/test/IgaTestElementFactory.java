package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import org.ojalgo.structure.Access2D;
import org.ojalgo.structure.Mutate2D;
import org.ojalgo.structure.Transformation2D;

import java.util.function.BiFunction;

import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;

public class IgaTestElementFactory {

  private final Mesh mesh;

  public IgaTestElementFactory(Mesh mesh) {
    this.mesh = mesh;
  }

  public IgaElement testElement(
      IgaVertex vertex,
      MatrixCharacteristics c
  ) {
    return testElement(vertex, c, c, c);
  }

  public IgaElement testElement(
      IgaVertex vertex,
      MatrixCharacteristics mca,
      MatrixCharacteristics mcb,
      MatrixCharacteristics mcx
  ) {
    IgaElement e = igaElement(vertex.id(), mesh.getDofsX());
    e.ma.modifyAny(mca);
    e.ma.modifyAny(mcb);
    e.ma.modifyAny(mcx);
    return e;
  }

  public enum MatrixCharacteristics implements Transformation2D<Double> {
    INDEXED((row, col) -> (double) row * col + col);

    BiFunction<Long, Long, Double> transformation;

    MatrixCharacteristics(BiFunction<Long, Long, Double> transformation) {
      this.transformation = transformation;
    }

    @Override
    public <T extends Mutate2D.ModifiableReceiver<Double> & Access2D<Double>> void transform(T transformable) {
      transformable.loopAll((row, col) -> transformable.set(row, col, transformation.apply(row, col)));
    }

  }

}
