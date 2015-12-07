package io.avery.day1

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString
  println(FloorFinder.findEndFloor(input))
  println(FloorFinder.findFirstBasementPosition(input))
}
