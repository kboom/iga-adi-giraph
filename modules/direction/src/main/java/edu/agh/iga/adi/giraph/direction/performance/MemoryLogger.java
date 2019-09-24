package edu.agh.iga.adi.giraph.direction.performance;

import lombok.val;
import org.apache.giraph.worker.DefaultWorkerObserver;
import org.apache.log4j.Logger;

import static java.lang.String.format;
import static java.lang.management.ManagementFactory.getMemoryMXBean;
import static org.apache.log4j.Logger.getLogger;

public class MemoryLogger extends DefaultWorkerObserver {

  private static final Logger LOG = getLogger(MemoryLogger.class);

  private static final String START = "START";
  private static final String END = "END";

  @Override
  public void preApplication() {
    super.preApplication();
    if (LOG.isDebugEnabled()) {
      LOG.debug(memoryEnvironment());
    }
  }

  @Override
  public void preSuperstep(long superstep) {
    super.preSuperstep(superstep);
    if (LOG.isDebugEnabled()) {
      LOG.debug(memoryStats(START, superstep));
    }
  }

  @Override
  public void postSuperstep(long superstep) {
    super.postSuperstep(superstep);
    if (LOG.isDebugEnabled()) {
      LOG.debug(memoryStats(END, superstep));
    }
  }

  private static String memoryEnvironment() {
    val heapMemoryUsage = getMemoryMXBean().getHeapMemoryUsage();
    val initMemory = heapMemoryUsage.getInit();
    val maxMemory = heapMemoryUsage.getMax();
    return format("Environment: %d,%d", initMemory, maxMemory);
  }

  private static String memoryStats(String prefix, long superstep) {
    val heapMemoryUsage = getMemoryMXBean().getHeapMemoryUsage();
    val usedMemory = heapMemoryUsage.getUsed();
    val committedMemory = heapMemoryUsage.getCommitted();
    return format("%d,%s,%d,%d", superstep, prefix, usedMemory, committedMemory);
  }

}
