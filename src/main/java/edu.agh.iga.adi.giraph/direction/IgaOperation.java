package edu.agh.iga.adi.giraph.direction;

import java.util.Iterator;

public interface IgaOperation<M extends IgaMessage> {

  /**
   * Sends messages in the previous superstep to be consumed in the next superstep
   * by {@link #consumeMessages(IgaElement, Iterator)}
   *
   * @param element the source element from which the messages are sent
   * @return the list of messages to send
   */
  Iterator<M> sendMessages(IgaElement element);

  /**
   * Consumes messages sent in the previous superstep by {@link #sendMessages(IgaElement, Iterator messages)}
   * and updates the vertex state
   *
   * @param element  the target element to which the messages are destined for
   * @param messages the messages sent to the element
   * @return the updated element
   */
  IgaElement consumeMessages(IgaElement element, Iterator<M> messages);

}
