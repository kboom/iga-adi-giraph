package edu.agh.iga.adi.giraph.direction;

import edu.agh.iga.adi.giraph.direction.io.StepVertexOutputFormat;
import lombok.val;
import org.apache.giraph.worker.DefaultWorkerContext;

import static edu.agh.iga.adi.giraph.direction.Flags.INT_TRUE;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.ENDING_SUPER_STEP;
import static edu.agh.iga.adi.giraph.direction.IgaCounter.STEP_COUNTER;

public final class IgaWorkerContext extends DefaultWorkerContext {

  @Override
  public void preSuperstep() {
    val c = getContext().getCounter(ENDING_SUPER_STEP);
    val s = getContext().getCounter(STEP_COUNTER);
    StepVertexOutputFormat.step = (int) s.getValue();
    StepVertexOutputFormat.isLast = c.getValue() == INT_TRUE;
  }

}
