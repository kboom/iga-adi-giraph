package edu.agh.iga.adi.giraph;

import edu.agh.iga.adi.giraph.io.data.IgaMessageWritable;
import org.apache.giraph.combiner.MessageCombiner;
import org.apache.hadoop.io.LongWritable;

public class IgaMessageCombiner implements MessageCombiner<LongWritable, IgaMessageWritable> {

  @Override
  public void combine(LongWritable longWritable, IgaMessageWritable original, IgaMessageWritable other) {
    // loose other for now
  }

  @Override
  public IgaMessageWritable createInitialMessage() {
    return null;
  }

}
