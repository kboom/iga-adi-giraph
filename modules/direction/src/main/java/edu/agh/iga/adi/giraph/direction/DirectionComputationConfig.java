package edu.agh.iga.adi.giraph.direction;

import lombok.Builder;
import lombok.Data;
import org.apache.hadoop.fs.Path;

@Builder(builderMethodName = "directionComputationConfig")
@Data
public class DirectionComputationConfig {
  Path input;
  Path output;
}
