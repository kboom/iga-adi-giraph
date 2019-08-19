package edu.agh.iga.adi.giraph.initialisation.hadoop.io

import java.io.{DataInput, DataOutput}

import breeze.linalg.DenseMatrix
import edu.agh.iga.adi.giraph.initialisation.ElementCoefficients
import org.apache.hadoop.io.Writable

class ElementCoefficientsWritable(var c: ElementCoefficients) extends Writable[ElementCoefficients] {

  this () {}

  override def write(dataOutput: DataOutput): Unit = {
    val m = c.m
    dataOutput.writeInt(m.rows)
    dataOutput.writeInt(m.cols)
    for (i <- m.data.indices) {
      dataOutput.writeDouble(m.data(i))
    }
  }

  override def readFields(dataInput: DataInput): Unit = {
    val rows = dataInput.readInt()
    val cols = dataInput.readInt()
    val data = new Array[Double](rows * cols)
    for (i <- 0 until rows * cols) {
      data(i) = dataInput.readDouble()
    }
    c = ElementCoefficients(DenseMatrix.create(rows, cols, data))
  }

}
