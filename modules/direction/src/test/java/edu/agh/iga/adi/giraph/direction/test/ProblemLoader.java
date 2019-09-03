package edu.agh.iga.adi.giraph.direction.test;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static edu.agh.iga.adi.giraph.direction.test.ProblemLoader.ProblemLoaderConfig.*;
import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProblemLoader {

  public static ProblemLoaderConfigBuilder problemLoaderConfig() {
    return builder();
  }

  @SneakyThrows
  public static Set<Path> loadProblem(ProblemLoaderConfig cfg) {
    return coefficientsByShard(cfg)
        .entrySet()
        .stream()
        .map(entry -> writeCoefficients(cfg, entry.getKey(), entry.getValue()))
        .collect(collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
  }

  @SneakyThrows
  private static Path writeCoefficients(ProblemLoaderConfig cfg, int part, List<String> coefficients) {
    return Files.write(cfg.targetPath.resolve("part-" + part), coefficients);
  }

  private static Map<Integer, List<String>> coefficientsByShard(ProblemLoaderConfig cfg) throws IOException {
    return readAllLines(pathOfResource(cfg.resource))
        .stream()
        .collect(groupingBy(x -> Integer.valueOf(x.split(" ")[0]) / cfg.shards));
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
