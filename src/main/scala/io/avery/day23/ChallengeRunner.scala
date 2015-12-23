package io.avery.day23

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toVector
  println(Interpreter.interpret(input, Map("a" -> 0, "b" -> 0)))
  println(Interpreter.interpret(input, Map("a" -> 1, "b" -> 0)))
}
