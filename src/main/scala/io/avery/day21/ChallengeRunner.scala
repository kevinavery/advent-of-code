package io.avery.day21

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(RpgSimulator.cheapestWinner(input))
  println(RpgSimulator.mostExpensiveLoser(input))
}
