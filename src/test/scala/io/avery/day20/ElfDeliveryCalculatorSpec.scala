package io.avery.day20

import org.scalatest.{Matchers, FlatSpec}

class ElfDeliveryCalculatorSpec extends FlatSpec with Matchers {
  import ElfDeliveryCalculator._

  "An ElfDeliveryCalculator" should "find the first house to receive at least N presents" in {
    findHouse(10) should be(1)
    findHouse(50) should be(4)
    findHouse(80) should be(6)
    findHouse(120) should be(6)
    findHouse(10000) should be(360)
  }

  it should "find first house to receive at least N presents with lazy elves" in {
    findHouseLazyElves(11) should be(1)
    findHouseLazyElves(50) should be(4)
    findHouseLazyElves(10000) should be(336)
  }
}
