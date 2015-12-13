package io.avery.day13

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(DinnerTableArranger.getMaximalHappiness(input))
  println(DinnerTableArranger.getMaximalHappinessWithMe(input))
}
