package io.avery.day16

import org.scalatest.{FlatSpec, Matchers}

class AuntSueFinderSpec extends FlatSpec with Matchers {
  import AuntSueFinder._

  val input =
    """
      |Sue 1: children: 3, goldfish: 5, trees: 3
      |Sue 2: children: 3, goldfish: 6, trees: 3
      |Sue 3: children: 3, goldfish: 4, trees: 7
      |Sue 4: children: 3, goldfish: 5, trees: 7
    """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

  "An AuntSueFinder" should "find Sue using My First Crime Scene Analysis Machine" in {
    findSueUsingMFCSAM(input) should be(Some("1"))
  }

  it should "find Sue using Retro Encabulator" in {
    findSueUsingRetroEncabulator(input) should be(Some("3"))
  }

}
