package io.avery.day12

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString
  println(JsonNumberExtractor.sumAllNumbers(input))
  println(JsonNumberExtractor.sumAllNumbersWithoutRed(input))
}
