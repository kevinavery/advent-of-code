package io.avery.day18

import org.scalatest.{Matchers, FlatSpec}

class AnimatedLightGridSpec extends FlatSpec with Matchers {
  import AnimatedLightGrid._

  val input =
    """
      |.#.#.#
      |...##.
      |#....#
      |..#...
      |#.#..#
      |####..
    """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

  "An AnimatedLightGrid" should "animate lights and return number on" in {
    animateLights(input, 1) should be(11)
    animateLights(input, 2) should be(8)
    animateLights(input, 3) should be(4)
    animateLights(input, 4) should be(4)
  }

  it should "animate lights and return number on with corners stuck on" in {
    animateLightsWithStuckCorners(input, 1) should be(18)
    animateLightsWithStuckCorners(input, 2) should be(18)
    animateLightsWithStuckCorners(input, 3) should be(18)
    animateLightsWithStuckCorners(input, 4) should be(14)
    animateLightsWithStuckCorners(input, 5) should be(17)
  }
}
