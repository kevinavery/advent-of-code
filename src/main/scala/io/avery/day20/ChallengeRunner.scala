package io.avery.day20

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").mkString.toInt
  println(ElfDeliveryCalculator.findHouse(input))
  println(ElfDeliveryCalculator.findHouseLazyElves(input))
}
