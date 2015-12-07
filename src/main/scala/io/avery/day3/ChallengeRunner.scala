package io.avery.day3

import io.avery.day2.ChallengeRunner._
import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString
  println(InfiniteHousesSolver.countVisitedHouses(input))
  println(InfiniteHousesSolver.computeVisitedHousesWithRoboSanta(input))
}
