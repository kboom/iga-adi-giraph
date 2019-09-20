package edu.agh.iga.adi.giraph.core;

import edu.agh.iga.adi.giraph.core.factory.MethodCoefficients;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IgaContext {
  DirectionTree tree;
  Mesh mesh;
  MethodCoefficients methodCoefficients;
}
