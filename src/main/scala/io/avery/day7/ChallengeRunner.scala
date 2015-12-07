package io.avery.day7

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(CircuitEmulator.solve(input, "a"))
}
