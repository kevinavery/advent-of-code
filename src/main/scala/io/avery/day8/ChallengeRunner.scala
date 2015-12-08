package io.avery.day8

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val lines = getResourceLines("input.txt").toList
  println(CharacterCounter.computeInMemoryStringDelta(lines))
  println(CharacterCounter.computeEscapedLiteralDelta(lines))
}
