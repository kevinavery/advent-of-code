package io.avery.day9

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val lines = getResourceLines("input.txt").toList
  println(TravelingSantaSolver.findMinRouteDistance(lines))
  println(TravelingSantaSolver.findMaxRouteDistance(lines))
}
