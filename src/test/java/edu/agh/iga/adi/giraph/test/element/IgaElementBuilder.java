package edu.agh.iga.adi.giraph.test.element;

import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;

public class IgaElementBuilder {

  private final IgaVertex vertex;
  private final Mesh mesh;

  private PrimitiveDenseStore ma;
  private PrimitiveDenseStore mb;
  private PrimitiveDenseStore mx;

  private IgaElementBuilder(IgaVertex vertex, Mesh mesh) {
    this.vertex = vertex;
    this.mesh = mesh;
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

  public IgaElementBuilder withMatrixX(PrimitiveDenseStore mx) {
    this.mx = mx;
    return this;
  }

  public IgaElement build() {
    IgaElement e = igaElement(vertex.id(), mesh.getDofsX());
    if (ma != null) {
      e.ma.accept(ma);
    }
    if (mb != null) {
      e.mb.accept(mb);
    }
    if (mx != null) {
      e.mx.accept(mx);
    }
    return e;
  }

}
