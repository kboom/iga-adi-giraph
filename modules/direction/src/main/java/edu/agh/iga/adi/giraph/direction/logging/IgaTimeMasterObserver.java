package edu.agh.iga.adi.giraph.direction.logging;

import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.master.MasterObserver;
import org.apache.giraph.metrics.AggregatedMetrics;
import org.apache.giraph.partition.PartitionStats;

import java.util.List;

import static edu.agh.iga.adi.giraph.direction.logging.TimeLogger.logStepTime;
import static edu.agh.iga.adi.giraph.direction.logging.TimeLogger.logTotalTime;
import static java.lang.System.currentTimeMillis;

public class IgaTimeMasterObserver implements MasterObserver {

  long computationStartedAt;
  long stepStartedAt;

  @Override
  public void preApplication() {
    computationStartedAt = currentTimeMillis();
  }

  @Override
  public void postApplication() {
    logTotalTime(currentTimeMillis() - computationStartedAt);
  }

  @Override
  public void applicationFailed(Exception e) {

  }

  @Override
  public void preSuperstep(long superstep) {
    stepStartedAt = currentTimeMillis();
  }

  @Override
  public void postSuperstep(long superstep) {
    logStepTime(superstep, currentTimeMillis() - stepStartedAt);
  }

  @Override
  public void superstepMetricsUpdate(long superstep, AggregatedMetrics aggregatedMetrics, List<PartitionStats> partitionStatsList) {

  }

  @Override
  public ImmutableClassesGiraphConfiguration getConf() {
    return null;
  }

  @Override
  public void setConf(ImmutableClassesGiraphConfiguration configuration) {

  }

}
