package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.direction.IgaElement;
import edu.agh.iga.adi.giraph.direction.IgaMessage;
import edu.agh.iga.adi.giraph.direction.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.direction.io.data.IgaOperationWritable;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;

import java.util.Iterator;

import static java.util.stream.StreamSupport.stream;

class ComputationUtil {

  static IgaElement elementOf(Vertex<LongWritable, IgaElementWritable, IgaOperationWritable> vertex) {
    return vertex.getValue().getElement();
  }

  static <T extends IgaMessage> Iterator<T> messagesOf(Iterable<IgaMessageWritable> iterable, Class<T> msgClazz) {
    return stream(iterable.spliterator(), false)
        .map(msg -> msg.getMessageAs(msgClazz))
        .iterator();
  }

}
