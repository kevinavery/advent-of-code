package io.avery.day14

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(ReindeerRacer.winnerByDistance(input, 2503))
  println(ReindeerRacer.winnerByPoints(input, 2503))
}
