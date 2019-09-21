package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import org.apache.giraph.worker.DefaultWorkerContext;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;

import static edu.agh.iga.adi.giraph.direction.StepAggregators.ENDING_SUPER_STEP_OF_STEP;
import static edu.agh.iga.adi.giraph.direction.StepAggregators.STEP;

public final class IgaWorkerContext extends DefaultWorkerContext {

  @Override
  public void preSuperstep() {
    BooleanWritable c = getAggregatedValue(ENDING_SUPER_STEP_OF_STEP);
    IntWritable s = getAggregatedValue(STEP);
    StepVertexOutputFormat.step = s.get();
    StepVertexOutputFormat.isLast = c.get();
  }

}
