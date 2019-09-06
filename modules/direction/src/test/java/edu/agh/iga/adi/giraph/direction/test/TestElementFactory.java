package edu.agh.iga.adi.giraph.direction.test;

import edu.agh.iga.adi.giraph.core.DirectionTree;
import edu.agh.iga.adi.giraph.core.IgaElement;
import edu.agh.iga.adi.giraph.core.IgaVertex;
import edu.agh.iga.adi.giraph.core.Mesh;
import edu.agh.iga.adi.giraph.core.factory.HorizontalElementFactory;
import edu.agh.iga.adi.giraph.core.problem.Problem;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@NoArgsConstructor
public class TestElementFactory {

  public static Set<IgaElement> elementsFor(Problem problem, DirectionTree tree, Mesh mesh) {
    HorizontalElementFactory ef = new HorizontalElementFactory(mesh);
    return LongStream.rangeClosed(1, tree.lastIndexOfLeafRow())
        .boxed()
        .map(id -> ef.createElement(problem, IgaVertex.vertexOf(tree, id)))
        .collect(Collectors.toSet());
  }

}