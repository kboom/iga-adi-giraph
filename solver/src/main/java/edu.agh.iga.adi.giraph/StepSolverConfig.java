package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.core.Mesh;
import lombok.Builder;
import lombok.Data;
import org.apache.hadoop.fs.Path;

@Builder(buildMethodName = "stepSolverConfig")
@Data
public class StepSolverConfig {
  Mesh mesh;
  Path input;
  Path output;
  Path tmp;
}
