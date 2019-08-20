package edu.agh.iga.adi.giraph.initialisation

import java.io.File
import java.lang.ClassLoader.{getSystemResource, getSystemResourceAsStream}
import java.nio.file.Files.createTempDirectory
import java.nio.file.Paths

import edu.agh.iga.adi.giraph.core.Mesh
import edu.agh.iga.adi.giraph.core.Mesh.aMesh

import scala.io.Source.{fromFile, fromInputStream}

class DirectionFlipperJobIT extends AbstractIT {

  class SolverContext(problemSize: Int) {
    implicit val mesh: Mesh = aMesh().withElements(problemSize).build()
    val df = DirectionFlipperJob(mesh)
  }

  "running direction flipper" when {
    val out = createTempDirectory("test").resolve("run")

    "coefficients were ones" should {
      val in = Paths.get(getSystemResource("ones/coefficients.in").toURI)

      "gives proper result" in new SolverContext(12) {
        df.flip(in, out)

        val actualLines = listFiles(out.toString, "part")
          .flatMap(fromFile(_).getLines())

        val fileStream = getSystemResourceAsStream("ones/coefficients.out")
        val lines = fromInputStream(fileStream).getLines

        actualLines should contain theSameElementsAs lines.toTraversable
      }

    }

  }

  def listFiles(dir: String, pattern: String = ""): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).filter(_.getName.startsWith(pattern)).toList
    } else {
      List[File]()
    }
  }

}
