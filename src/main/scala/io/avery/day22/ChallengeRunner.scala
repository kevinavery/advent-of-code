package io.avery.day22

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(WizardRpgSimulator.findMinWinningMana(input))
  println(WizardRpgSimulator.findMinWinningManaWithHandicap(input))
}
