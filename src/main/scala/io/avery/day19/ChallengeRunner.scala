package io.avery.day19

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(MedicineMachine.countSingleTransformMolecules(input))
  println(MedicineMachine.countModelFabricationSteps(input))
}
