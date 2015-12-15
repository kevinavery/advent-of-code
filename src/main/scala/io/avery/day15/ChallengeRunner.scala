package io.avery.day15

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(CookieMaker.findMaxCookieScore(input, 100))
  println(CookieMaker.findMaxCookieScoreWithCalories(input, 100, 500))
}
