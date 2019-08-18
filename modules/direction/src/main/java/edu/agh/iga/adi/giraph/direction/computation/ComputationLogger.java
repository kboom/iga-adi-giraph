package edu.agh.iga.adi.giraph.direction.computation;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.logging.ElementFormatter.formatElement;
import static java.lang.String.format;

final class ComputationLogger {

  private static final Logger LOG = Logger.getLogger(ComputationLogger.class);
  private static final String SEPARATOR = " ===================================== ";

  static void logPhase(IgaComputationPhase phase) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(SEPARATOR + phase + SEPARATOR);
    }
  }

  static void computationLog(IgaElement e) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(format("\nE%d ------------------------ \n%s", e.id, formatElement(e)));
    }
  }

}
