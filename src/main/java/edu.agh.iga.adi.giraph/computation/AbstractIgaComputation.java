package edu.agh.iga.adi.giraph.computation;

import edu.agh.iga.adi.giraph.io.data.IgaElementWritable;
import edu.agh.iga.adi.giraph.io.data.IgaMessageWritable;
import edu.agh.iga.adi.giraph.io.data.IgaOperationWritable;
import org.apache.giraph.graph.BasicComputation;
import org.apache.hadoop.io.LongWritable;

abstract class AbstractIgaComputation extends BasicComputation<LongWritable, IgaElementWritable, IgaOperationWritable, IgaMessageWritable>  {

}
