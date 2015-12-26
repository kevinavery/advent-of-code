package io.avery.day25

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString
  println(WeatherMachine.getCode(input))
}
