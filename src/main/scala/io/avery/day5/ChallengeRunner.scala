package io.avery.day5

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(NiceStringVerifier.countNiceStrings(input))
  println(NiceStringVerifier.countNewNiceStrings(input))
}
