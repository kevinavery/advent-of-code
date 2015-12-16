package io.avery.day16

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(AuntSueFinder.findSueUsingMFCSAM(input))
  println(AuntSueFinder.findSueUsingRetroEncabulator(input))
}
