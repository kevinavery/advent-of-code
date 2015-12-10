package io.avery.day10

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString
  println(LookAndSayGame.nthLength(input, 40))
  println(LookAndSayGame.nthLength(input, 50))
}
