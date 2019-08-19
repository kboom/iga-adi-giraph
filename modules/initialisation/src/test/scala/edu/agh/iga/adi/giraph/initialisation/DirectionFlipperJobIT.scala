package edu.agh.iga.adi.giraph.initialisation

import java.nio.file.{Files, Paths}

import edu.agh.iga.adi.giraph.core.Mesh
import edu.agh.iga.adi.giraph.core.Mesh.aMesh

import scala.io.Source

class DirectionFlipperJobIT extends AbstractIT {

  class SolverContext(problemSize: Int) {
    implicit val mesh: Mesh = aMesh().withElements(problemSize).build()
    val df = DirectionFlipperJob(mesh)
  }

  "running direction flipper" when {
    val out = Files.createTempDirectory("test")

    "coefficients were ones" should {
      val in = Paths.get(getClass.getResource("ones/coefficients.in").toURI)

      "gives proper result" in new SolverContext(12) {
        df.flip(in, out)
        val fileStream = getClass.getResourceAsStream("ones/coefficients.out")
        val lines = Source.fromInputStream(fileStream).getLines
        lines shouldBe "sfsf"
      }

    }

  }

}
