package edu.agh.iga.adi.giraph.direction.config;

import org.apache.giraph.conf.FloatConfOption;

public class InitialComputationConfiguration {
  public static final FloatConfOption RADIAL_PROBLEM_RADIUS_RATIO = new FloatConfOption("iga.initialisation.radial.radius", 0.25f, "The relative size of the radius");
  public static final FloatConfOption RADIAL_PROBLEM_RADIUS_VALUE = new FloatConfOption("iga.initialisation.radial.value", 10, "The value within the radius with respect to problem size");
}
