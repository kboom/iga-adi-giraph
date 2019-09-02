package edu.agh.iga.adi.giraph.core;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IgaContext {
  DirectionTree tree;
  Mesh mesh;
}
