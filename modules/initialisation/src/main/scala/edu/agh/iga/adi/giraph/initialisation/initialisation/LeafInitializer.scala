package edu.agh.iga.adi.giraph.initialisation.initialisation

import edu.agh.iga.adi.giraph.initialisation.{ElementCoefficients, IgaContext}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

trait LeafInitializer {

  def leafData(ctx: IgaContext)(implicit sc: SparkContext): RDD[(Long, ElementCoefficients)]

}
