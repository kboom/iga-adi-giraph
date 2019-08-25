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
   * After all data is sent
   *
   * @param element
   * @param tree
   */
  default void postSend(IgaElement element, DirectionTree tree) {
    // for now just reset the matrix, then remove the elements to free up the memory
    element.ma.reset();
    element.mb.reset();
    element.mx.reset();
  }

  /**
   * After all messages are consumed, the processing can take place
   *
   * @param element
   * @param tree
   */
  default void postConsume(IgaElement element, DirectionTree tree) {
  }

}
