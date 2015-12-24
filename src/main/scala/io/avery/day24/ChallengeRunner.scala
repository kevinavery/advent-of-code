package io.avery.day24

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(PackageBalancer.findMinQuantumEntanglement(input, 3))
  println(PackageBalancer.findMinQuantumEntanglement(input, 4))
}
