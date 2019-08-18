package edu.agh.iga.adi.giraph.commons;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {

  private PathUtil() {

  }

  public static Path pathOfResource(String resourceName) {
    final URL resource = ClassLoader.getSystemClassLoader().getResource(resourceName);
    return Paths.get(resource.getPath());
  }

}
