package edu.agh.iga.adi.giraph.initialisation.transposition

import breeze.linalg.DenseVector
import org.apache.spark.rdd.RDD

object Transposition {

  def transpose(m: RDD[(Long, DenseVector[Double])]): RDD[(Long, DenseVector[Double])] =
    m.mapPartitions(rowToTransposedTriplet)
      .mapPartitions(_.flatten) // now we have triplets (newRowIndex, (newColIndex, value))
      .groupByKey
      .mapPartitions(
        _.map { case (a, b) => buildRow(a, b) }, preservesPartitioning = true
      )

  def rowToTransposedTriplet(i: Iterator[(Long, DenseVector[Double])]): Iterator[Array[(Long, (Long, Double))]] =
    i.map(row => row._2.data.zipWithIndex.map {
      case (value, colIndex) => (colIndex.toLong, (row._1, value))
    })

  def buildRow(rowIndex: Long, rowWithIndexes: Iterable[(Long, Double)]): (Long, DenseVector[Double]) = {
    val it = rowWithIndexes.iterator
    val resArr = DenseVector.zeros[Double](rowWithIndexes.size)

    while (it.hasNext) {
      val n = it.next()
      resArr(n._1.toInt) = n._2
    }

    (rowIndex, resArr)
  }

}
