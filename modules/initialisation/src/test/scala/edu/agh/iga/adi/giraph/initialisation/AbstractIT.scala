package edu.agh.iga.adi.giraph.initialisation

import java.nio.file.Files.createTempDirectory

import org.apache.spark.sql.SparkSession
import org.scalatest._

trait SparkSession extends SuiteMixin with BeforeAndAfterAll { this: TestSuite =>

  private val spark = SparkSession.builder
    .appName("IGA ADI Pregel Solver")
    .master("local[*]")
    .config("spark.ui.enabled", "false")
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .config("spark.kryo.registrator", "edu.agh.iga.adi.giraph.initialisation.spark.serialization.IgaAdiKryoRegistrator")
//    .config("spark.kryo.registrationRequired", "true")
    .config("spark.kryo.unsafe", "true")
    .getOrCreate()

  implicit val sc = spark.sparkContext

  sc.setCheckpointDir(createTempDirectory("spark").toAbsolutePath.toString)

  override protected def afterAll(): Unit = spark.stop()

}

abstract class AbstractIT extends SubjectSpec with SparkSession
