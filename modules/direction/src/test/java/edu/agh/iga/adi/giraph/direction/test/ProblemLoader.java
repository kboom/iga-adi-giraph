package edu.agh.iga.adi.giraph.direction.test;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.ProblemLoaderConfig.*; // do not remove this
import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProblemLoader {

  public static ProblemLoaderConfigBuilder problemLoaderConfig() {
    return builder();
  }

  @SneakyThrows
  public static Set<Path> loadProblem(ProblemLoaderConfig cfg) {
    val coefficientsByShard = coefficientsByShard(cfg);
    coefficientsByShard
        .forEach((key, value) -> writeCoefficients(cfg, key, value));

    return pathsOfShards(coefficientsByShard);
  }

  private static Set<Path> pathsOfShards(Map<Integer, List<String>> coefficientsByShard) {
    return coefficientsByShard.keySet().stream()
        .map(i -> "part" + i)
        .map(Paths::get)
        .collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
  }

  @SneakyThrows
  private static void writeCoefficients(ProblemLoaderConfig cfg, int part, List<String> coefficients) {
    Files.write(cfg.targetPath.resolve("part-" + part), coefficients);
  }

  private static Map<Integer, List<String>> coefficientsByShard(ProblemLoaderConfig cfg) throws IOException {
    return readAllLines(pathOfResource(cfg.resource))
        .stream()
        .collect(groupingBy(x -> parseInt(x.split(" ")[0]) / cfg.shards));
  }

  @SneakyThrows
  private static Path pathOfResource(String resource) {
    return Paths.get(ProblemLoader.class.getClassLoader().getResource(resource).toURI());
  }

  @Builder
  public static class ProblemLoaderConfig {
    String resource;
    Path targetPath;
    int shards;
  }

}
