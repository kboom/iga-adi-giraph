package edu.agh.iga.adi.giraph.calculator.assertj;

import io.vavr.control.Either;
import org.assertj.core.api.AbstractAssert;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class EitherAssertions<L, R> extends AbstractAssert<EitherAssertions<L, R>, Either<L, R>> {

  private EitherAssertions(Either<L, R> rs) {
    super(rs, EitherAssertions.class);
  }

  public static <L, R> EitherAssertions<L, R> assertThatEither(Either<L, R> either) {
    return new EitherAssertions<>(either);
  }

  public EitherAssertions<L, R> hasLeft(Consumer<L> conditions) {
    assertThat(actual.isLeft()).as("Should have left").isTrue();
    actual.peekLeft(conditions);
    return this;
  }

  public EitherAssertions<L, R> hasRight(Consumer<R> conditions) {
    assertThat(actual.isRight()).as("Should have right").isTrue();
    actual.peek(conditions);
    return this;
  }

}
