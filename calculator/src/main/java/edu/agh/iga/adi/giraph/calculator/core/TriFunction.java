package edu.agh.iga.adi.giraph.calculator.core;

@FunctionalInterface
public interface TriFunction<A, B, C, D> {
  D apply(A a, B b, C c);
}
