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
  M sendMessage(IgaVertex dstId, IgaElement element);

  /**
   * Consumes message sent in the previous superstep by {@link #sendMessages(IgaElement, Iterator message)}
   * and updates the vertex state
   *
   * @param element the target element to which the message are destined for
   * @param message the message sent to the element
   * @param tree    the direction tree for this computation
   */
  void consumeMessage(IgaElement element, M message, DirectionTree tree);

  /**
   * After all messages are sent we get an opportunity to free up some memory.
   * By default returns the element without modifications.
   *
   * @param element
   * @param tree
   */
  default IgaElement postSend(IgaElement element, DirectionTree tree) {
    return element;
  }

  /**
   * Runs before any message gets consumed.
   * By default returns unmodified element.
   *  @param vertex
   * @param ctx
   * @param element can be null
   */
  default IgaElement preConsume(IgaVertex vertex, IgaContext ctx, IgaElement element) {
    return element;
  }

  /**
   * After all messages are consumed, some additional processing can take place.
   *
   * @param element
   * @param tree
   */
  default void postConsume(IgaElement element, DirectionTree tree) {
  }

}
