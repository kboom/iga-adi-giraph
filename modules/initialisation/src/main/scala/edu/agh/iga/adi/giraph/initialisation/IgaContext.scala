package edu.agh.iga.adi.giraph.initialisation

import edu.agh.iga.adi.giraph.core.{DirectionTree, Mesh}
import edu.agh.iga.adi.giraph.initialisation.problem.Problem

sealed trait SolverDirection

case object HORIZONTAL extends SolverDirection

case object VERTICAL extends SolverDirection

sealed case class IgaContext(mesh: Mesh, problem: Problem, direction: SolverDirection = HORIZONTAL) {
  def changedDirection(): IgaContext = IgaContext(mesh, problem, VERTICAL)

  def xTree(): DirectionTree = new DirectionTree(mesh.getElementsX)

  def yTree(): DirectionTree = new DirectionTree()(mesh.getElementsY)

  def tree(): DirectionTree = direction match {
    case HORIZONTAL => xTree()
    case VERTICAL => yTree()
    case _ => throw new IllegalStateException("Unknown direction")
  }
}

object IgaContext {
  val SPLINE_ORDER = 2
}
