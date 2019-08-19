package edu.agh.iga.adi.giraph.initialisation.spark.serialization

import com.esotericsoftware.kryo.Kryo
import edu.agh.iga.adi.giraph.core.Mesh
import org.apache.spark.serializer.KryoRegistrator

import scala.reflect.ClassTag

class IgaAdiKryoRegistrator extends KryoRegistrator {

  override def registerClasses(kryo: Kryo): Unit = {
    val loader = getClass.getClassLoader
    Array(
      Class.forName("scala.reflect.ClassTag$$anon$1", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$10", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$9", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$8", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$7", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$6", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$5", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$4", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$3", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$2", false, loader),
      Class.forName("scala.reflect.ManifestFactory$$anon$1", false, loader),
      Class.forName("org.apache.spark.util.collection.OpenHashSet$LongHasher", false, loader),
      classOf[scala.collection.mutable.WrappedArray.ofRef[_]],
      classOf[java.lang.Class[_]],
      classOf[java.lang.Object],
      classOf[Mesh]
    ).foreach(kryo.register)

    kryo.register(ClassTag(Class.forName("org.apache.spark.util.collection.CompactBuffer")).wrap.runtimeClass)

    BreezeSerializers.register(kryo)
  }
}
