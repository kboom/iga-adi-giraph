package edu.agh.iga.adi.giraph.test;

import edu.agh.iga.adi.giraph.direction.io.ColumnMajorArray;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static edu.agh.iga.adi.giraph.core.IgaConstants.ROWS_BOUND_TO_NODE;
import static java.lang.Long.parseLong;
import static java.nio.file.Files.list;
import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class CoefficientsFromFileReader {

  private static final String DELIMITER = ",";
  private static final BinaryOperator<PrimitiveDenseStore> ILLEGAL_OPERATION = (a, b) -> {
    throw new IllegalStateException();
  };

  private CoefficientsFromFileReader() {

  }

  public static SortedMap<Long, PrimitiveDenseStore> coefficientsOfFile(Path file) {
    return coefficientsOf(Stream.of(file));
  }

  public static SortedMap<Long, PrimitiveDenseStore> coefficientsOfDir(Path dir) throws IOException {
    return coefficientsOf(
        list(dir).filter(p -> p.getFileName().toString().startsWith("part"))
    );
  }

  private static SortedMap<Long, PrimitiveDenseStore> coefficientsOf(Stream<Path> paths) {
    return paths
        .map(CoefficientsFromFileReader::readCoefficientLines)
        .flatMap(List::stream)
        .filter(StringUtils::isNotEmpty)
        .map(line -> {
          final String[] idAndValues = line.split(" ");
          final String[] values = idAndValues[1].split(DELIMITER);
          final long id = parseLong(idAndValues[0]);
          int coefficientCount = values.length;
          int dofs = coefficientCount / ROWS_BOUND_TO_NODE;
          double[] data = stream(values)
              .mapToDouble(Double::parseDouble)
              .toArray();
          ColumnMajorArray dataAccess = new ColumnMajorArray(ROWS_BOUND_TO_NODE, dofs, 0, data);
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

}
