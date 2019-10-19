package edu.agh.iga.adi.giraph.calculator.core;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Problem {

  @Builder.Default
  int size = 12;

  @Builder.Default
  int workers = 1;

}