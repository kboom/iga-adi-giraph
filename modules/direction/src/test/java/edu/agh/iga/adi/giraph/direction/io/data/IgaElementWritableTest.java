package edu.agh.iga.adi.giraph.direction.io.data;

import com.google.common.io.ByteArrayDataOutput;
import org.junit.jupiter.api.Test;
import org.ojalgo.random.Uniform;

import java.io.IOException;

import static com.google.common.io.ByteStreams.newDataInput;
import static com.google.common.io.ByteStreams.newDataOutput;
import static edu.agh.iga.adi.giraph.core.IgaElement.igaElement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ojalgo.matrix.store.PrimitiveDenseStore.FACTORY;

class IgaElementWritableTest {

  private static final Uniform UNIFORM_DISTRIBUTION = new Uniform();

  @Test
  void canSerialize3Rows() throws IOException {
    // given
    IgaElementWritable readElement = new IgaElementWritable();
    ByteArrayDataOutput dataOutput = newDataOutput();
    IgaElementWritable wroteElement = new IgaElementWritable(igaElement(
        1,
        FACTORY.makeFilled(3, 3, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(3, 14, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(3, 14, UNIFORM_DISTRIBUTION)
    ));

    // when
    wroteElement.write(dataOutput);
    readElement.readFields(newDataInput(dataOutput.toByteArray()));

    // then
    assertThat(readElement.getElement()).isEqualToComparingFieldByField(wroteElement.getElement());
  }

  @Test
  void canSerialize6Rows() throws IOException {
    // given
    IgaElementWritable readElement = new IgaElementWritable();
    ByteArrayDataOutput dataOutput = newDataOutput();
    IgaElementWritable wroteElement = new IgaElementWritable(igaElement(
        2,
        FACTORY.makeFilled(6, 6, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(6, 14, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(6, 14, UNIFORM_DISTRIBUTION)
    ));

    // when
    wroteElement.write(dataOutput);
    readElement.readFields(newDataInput(dataOutput.toByteArray()));

    // then
    assertThat(readElement.getElement()).isEqualToComparingFieldByField(wroteElement.getElement());
  }

  @Test
  void canSerializeNullRhs() throws IOException {
    // given
    IgaElementWritable readElement = new IgaElementWritable();
    ByteArrayDataOutput dataOutput = newDataOutput();
    IgaElementWritable wroteElement = new IgaElementWritable(igaElement(
        2,
        FACTORY.makeFilled(6, 6, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(6, 14, UNIFORM_DISTRIBUTION),
        null
    ));

    // when
    wroteElement.write(dataOutput);
    readElement.readFields(newDataInput(dataOutput.toByteArray()));

    // then
    assertThat(readElement.getElement()).isEqualToComparingFieldByField(wroteElement.getElement());
  }

  @Test
  void canSerializeNullCoefficients() throws IOException {
    // given
    IgaElementWritable readElement = new IgaElementWritable();
    ByteArrayDataOutput dataOutput = newDataOutput();
    IgaElementWritable wroteElement = new IgaElementWritable(igaElement(
        2,
        FACTORY.makeFilled(6, 6, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(6, 14, UNIFORM_DISTRIBUTION),
        FACTORY.makeFilled(6, 14, UNIFORM_DISTRIBUTION)
    ));

    // when
    wroteElement.write(dataOutput);
    readElement.readFields(newDataInput(dataOutput.toByteArray()));

    // then
    assertThat(readElement.getElement()).isEqualToComparingFieldByField(wroteElement.getElement());
  }

}