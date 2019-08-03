package edu.agh.iga.adi.giraph.direction;

import java.util.Iterator;

public interface IgaOperation<R extends IgaMessage, S extends IgaMessage> {

  IgaElement consumeMessages(IgaElement element, Iterator<R> messages);

  Iterator<S> sendMessages(IgaElement element);

}
