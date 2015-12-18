package io.avery.day18

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(AnimatedLightGrid.animateLights(input, 100))
  println(AnimatedLightGrid.animateLightsWithStuckCorners(input, 100))
}
