package edu.agh.iga.adi.giraph.initialisation

import java.nio.file.Path

import org.apache.spark.SparkContext

class DirectionFlipperJob {

  def flip(in: Path, out: Path)(implicit sc: SparkContext): Unit = {
    sc.textFile(in.toString)
  }

}
