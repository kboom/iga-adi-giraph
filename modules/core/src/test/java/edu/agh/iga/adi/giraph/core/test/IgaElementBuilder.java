package edu.agh.iga.adi.giraph.core.test;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

public class IgaElementBuilder {

  private final IgaVertex vertex;
  private final Mesh mesh;

  private PrimitiveDenseStore ma;
  private PrimitiveDenseStore mb;
  private PrimitiveDenseStore mx;

  private IgaElementBuilder(IgaVertex vertex, Mesh mesh) {
    this.vertex = vertex;
    this.mesh = mesh;
    ma = FACTORY.makeZero(ROWS_BOUND_TO_NODE, ROWS_BOUND_TO_NODE);
    mb = FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
    mx = FACTORY.makeZero(ROWS_BOUND_TO_NODE, mesh.getDofsX());
  }

  public static IgaElementBuilder elementFor(IgaVertex vertex, Mesh mesh) {
    return new IgaElementBuilder(vertex, mesh);
  }

  public IgaElementBuilder withMatrixA(PrimitiveDenseStore ma) {
    this.ma = ma;
    return this;
  }

  public IgaElement withSpecificMatrixA(PrimitiveDenseStore ma) {
    return withMatrixA(ma).build();
  }

  public IgaElementBuilder withMatrixB(PrimitiveDenseStore mb) {
    this.mb = mb;
    return this;
  }

  public IgaElement withSpecificMatrixB(PrimitiveDenseStore mb) {
    return withMatrixB(mb).build();
  }

  public IgaElementBuilder withMatrixX(PrimitiveDenseStore mx) {
    this.mx = mx;
    return this;
  }

  public IgaElement withSpecificMatrixX(PrimitiveDenseStore ma) {
    return withMatrixX(ma).build();
  }

  public IgaElement build() {
    return igaElement(
        vertex.id(),
        ma,
        mb,
        mx
    );
  }
}
