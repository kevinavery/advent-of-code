package io.avery.day14

import org.scalatest.{FlatSpec, Matchers}

class ReindeerRacerSpec extends FlatSpec with Matchers {
  import ReindeerRacer._

  val input =
    """
      |Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
      |Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
    """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

  "A ReindeerRacer" should "compute winner based on distance" in {
    winnerByDistance(input, 1000) should be(1120)
  }

  it should "compute winner based on points" in {
    winnerByPoints(input, 1000) should be(689)
  }
}
