package io.avery.day17

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(EggnogContainerChooser.countContainerCombinations(input, 150))
  println(EggnogContainerChooser.countMinContainerCombinations(input, 150))
}
