package edu.agh.iga.adi.giraph.direction.computation.factorization;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.core.logging.ElementFormatter.formatElement;
import static java.lang.String.format;

public final class FactorizationLogger {

  private static final Logger LOG = Logger.getLogger(FactorizationLogger.class);
  private static final String SEPARATOR = " ===================================== ";

  public static void logPhase(IgaComputationPhase phase) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(SEPARATOR + phase + SEPARATOR);
    }
  }

  public static void computationLog(IgaElement e) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(format("\nE%d ------------------------ \n%s", e.id, formatElement(e)));
    }
  }

}
