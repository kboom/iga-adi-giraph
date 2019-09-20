package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.core.Mesh;
import lombok.NoArgsConstructor;
import org.apache.giraph.conf.GiraphConfiguration;

import static edu.agh.iga.adi.giraph.direction.config.IgaConfiguration.PROBLEM_SIZE;

@NoArgsConstructor
public class ContextFactory {

  public static Mesh meshOf(GiraphConfiguration configuration) {
    return Mesh.aMesh().withElements(PROBLEM_SIZE.get(configuration)).build();
  }

}
