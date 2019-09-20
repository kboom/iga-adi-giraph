package edu.agh.iga.adi.giraph.test.util;

import edu.agh.iga.adi.giraph.commons.ColumnMajorArray;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;
import static java.lang.Math.round;
import static java.nio.file.Files.list;
import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class CoefficientsFromFileReader {

  private static final String DELIMITER = ",";
  private static final BinaryOperator<PrimitiveDenseStore> ILLEGAL_OPERATION = (a, b) -> {
    throw new IllegalStateException();
  };

  private CoefficientsFromFileReader() {

  }

  public static SortedMap<Long, PrimitiveDenseStore> coefficientsOfFileDefaultPrecision(Path file, int rows) {
    return coefficientsOf(Stream.of(file), rows, withPrecision(3));
  }

  @SneakyThrows
  public static SortedMap<Long, PrimitiveDenseStore> coefficientsOfDir(Path dir, int rows) {
    return coefficientsOf(
        list(dir).filter(p -> p.getFileName().toString().startsWith("part")),
        rows,
        identity()
    );
  }

  @SneakyThrows
  public static SortedMap<Long, PrimitiveDenseStore> coefficientsOfDir(Path dir, int rows, int prec) {
    return coefficientsOf(
        list(dir).filter(p -> p.getFileName().toString().startsWith("part")),
        rows,
        withPrecision(prec)
    );
  }

  private static SortedMap<Long, PrimitiveDenseStore> coefficientsOf(Stream<Path> paths, int rows, Function<Double, Double> rounder) {
    return paths
        .map(CoefficientsFromFileReader::readCoefficientLines)
        .flatMap(List::stream)
        .filter(StringUtils::isNotEmpty)
        .map(line -> {
          final String[] idAndValues = line.split(" ");
          final String[] values = idAndValues[1].split(DELIMITER);
          final long id = parseLong(idAndValues[0]);
          int coefficientCount = values.length;
          int dofs = coefficientCount / rows;
          double[] data = stream(values)
              .mapToDouble(Double::parseDouble)
              .map(rounder::apply)
              .toArray();
          ColumnMajorArray dataAccess = new ColumnMajorArray(rows, dofs, 0, data);
          return Pair.of(id, PrimitiveDenseStore.FACTORY.copy(dataAccess));
        })
        .sorted((a, b) -> (int) (a.getLeft() - b.getLeft()))
        .collect(toMap(Pair::getLeft, Pair::getRight, ILLEGAL_OPERATION, TreeMap::new));
  }

  private static List<String> readCoefficientLines(Path path) {
    try {
      return readAllLines(path);
    } catch (IOException e) {
      throw new IllegalStateException("Could not read coefficients", e);
    }
  }

  private static Function<Double, Double> withPrecision(int prec) {
    return v -> (double) round(v * prec) / prec;
  }

}
