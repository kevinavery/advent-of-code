package io.avery.day2

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(PresentWrappingCalculator.computeTotalWrappingPaperArea(input))
  println(PresentWrappingCalculator.computeTotalRibbonLength(input))
}
