package edu.agh.iga.adi.giraph.initialisation

import java.nio.file.Path

import org.apache.spark.streaming.api.java.JavaStreamingContext.jarOfClass
import org.apache.spark.{SparkConf, SparkContext}

object DirectionFlipper {

  private val InfiniteWait = "9999999999s"

  def flip(input: Path, output: Path)(implicit cfg: FlipperConfig): Unit = {
    implicit val sc = Some(new SparkConf())
      .map(
        _.setAppName("IGA ADI Pregel Solver")
          .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
          .set("spark.kryo.registrator", "edu.agh.iga.adi.giraph.initialisation.spark.serialization.IgaAdiKryoRegistrator")
          .set("spark.kryo.registrationRequired", "true")
          .set("spark.kryo.unsafe", "true")
          .set("spark.cleaner.referenceTracking.blocking", "false")
          .set("spark.scheduler.minRegisteredResourcesRatio", "1.0")
          .setIfMissing("spark.locality.wait", InfiniteWait)
          .setIfMissing("spark.scheduler.maxRegisteredResourcesWaitingTime", InfiniteWait)
          .setIfMissing("spark.files.useFetchCache", "false") // as we run only one executor per node
          //          .setIfMissing("spark.shuffle.spill", "false") // can cause OOM
          .setIfMissing("spark.shuffle.spill.compress", "false") // can cause OOM
          .setIfMissing("spark.memory.fraction", "0.8")
          .setIfMissing("spark.memory.storageFraction", "0.4")
          .setIfMissing("spark.memory.offHeap.enabled", "true")
          .setIfMissing("spark.memory.offHeap.size", "3g")
          // https://bigdatatn.blogspot.com/2017/05/spark-performance-optimization-shuffle.html
          .setIfMissing("spark.reducer.maxSizeInFlight", "96MB")
          .setIfMissing("spark.shuffle.io.maxRetries", "100")
          .setIfMissing("spark.shuffle.io.retryWait", "0")

          .setIfMissing("spark.file.transferTo", "false")
          .setIfMissing("spark.shuffle.file.buffer", "1MB")
          .setIfMissing("spark.shuffle.unsafe.file.output.buffer", "5MB")
          .setIfMissing("spark.shuffle.service.index.cache.entries", "2048")
          .setIfMissing("spark.shuffle.io.serverThreads", "128")
          .setIfMissing("spark.shuffle.io.backLog", "8192")
          .setIfMissing("spark.shuffle.registration.timeout", "2m")
          .setIfMissing("spark.shuffle.registration.maxAttempts", "5")
          //          .setIfMissing("spark.unsafe.sorter.spill.reader.buffer.size", "1MB")
          //          .setIfMissing("spark.io.compression.lz4.blockSize", "512KB")
          //          .setIfMissing("spark.reducer.maxSizeInFlight", "4096k")
          //          .setIfMissing("spark.reducer.maxSizeInFlight", "32k") // interesting as the reduce is the slowest...
          //          .setIfMissing("spark.shuffle.service.index.cache.size", "2048")
          .setIfMissing("spark.worker.cleanup.enabled", "true")
          .setIfMissing("spark.deploy.spreadOut", "false") // align partitions next to each other, worker by worker rather than doing round robin
          .setIfMissing("spark.eventLog.enabled", "true")
          .setIfMissing("spark.eventLog.dir", s"file:///${System.getProperty("sparklogs", System.getProperty("java.io.tmpdir"))}")
      ) // https://www.slideshare.net/databricks/tuning-apache-spark-for-largescale-workloads-gaoxiang-liu-and-sital-kedia
      .map(conf => Option(cfg.master).map(conf.setMaster).getOrElse(conf))
      .map(conf => Option(cfg.jars).map(conf.setIfMissing("spark.jars", _)).getOrElse(conf))
      .map(conf => conf.setJars(jarOfClass(getClass)))
      .map(new SparkContext(_)).get


  }

}
