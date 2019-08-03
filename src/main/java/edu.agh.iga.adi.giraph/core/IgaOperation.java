package edu.agh.iga.adi.giraph.core;

import java.util.Iterator;

public interface IgaOperation<M extends IgaMessage> {

  /**
   * Sends messages in the previous superstep to be consumed in the next superstep
   * by {@link #consumeMessage(IgaElement, Iterator)}
   *
   * @param element the source element from which the messages are sent
   * @return the list of messages to send
   */
  M sendMessage(Long dstId, IgaElement element);

  /**
   * Consumes message sent in the previous superstep by {@link #sendMessages(IgaElement, Iterator message)}
   * and updates the vertex state
   *
   * @param element  the target element to which the message are destined for
   * @param message the message sent to the element
   * @return the updated element
   */
  IgaElement consumeMessage(IgaElement element, M message);

}
