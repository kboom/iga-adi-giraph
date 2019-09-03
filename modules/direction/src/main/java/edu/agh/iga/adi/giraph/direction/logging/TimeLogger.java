package edu.agh.iga.adi.giraph.direction.logging;

import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TimeLogger {

  private static final Logger LOG = Logger.getLogger(TimeLogger.class);

  public static void logTotalTime(long time) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(String.format("Total: %d", time));
    }
  }

  public static void logStepTime(long step, long time) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(String.format("%d %d", step, time));
    }
  }

  public static void logTime(int worker, long step, long time) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(String.format("%d %d %.3d", worker, step, time));
    }
  }

  public static String timeReducer(int worker) {
    return "iga.time.reducer." + worker;
  }

}
