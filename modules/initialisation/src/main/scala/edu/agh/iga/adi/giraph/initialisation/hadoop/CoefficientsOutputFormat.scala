package edu.agh.iga.adi.giraph.initialisation.hadoop

import breeze.linalg.DenseMatrix
import org.apache.hadoop.mapred.TextOutputFormat

object CoefficientsOutputFormat extends TextOutputFormat[Long, DenseMatrix[Double]] {

}
