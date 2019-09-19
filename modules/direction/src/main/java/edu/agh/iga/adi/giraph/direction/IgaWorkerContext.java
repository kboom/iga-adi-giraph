package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import org.apache.giraph.worker.DefaultWorkerContext;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.log4j.Logger;

import static edu.agh.iga.adi.giraph.direction.StepAggregators.ENDING_SUPER_STEP_OF_STEP;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.STEP;
import static org.apache.log4j.Logger.getLogger;

public final class IgaWorkerContext extends DefaultWorkerContext {

  private static final Logger LOG = getLogger(IgaWorkerContext.class);

  @Override
  public void preSuperstep() {
    BooleanWritable c = getAggregatedValue(ENDING_SUPER_STEP_OF_STEP);
    IntWritable s = getAggregatedValue(STEP);
    LOG.info("Ending super step " + c.get() + ", step " + s.get());
    StepVertexOutputFormat.step = s.get();
    StepVertexOutputFormat.isLast = c.get();
  }

}
