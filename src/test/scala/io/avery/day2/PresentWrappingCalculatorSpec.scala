package io.avery.day2

import org.scalatest.{Matchers, FlatSpec}

class PresentWrappingCalculatorSpec extends FlatSpec with Matchers {
  import PresentWrappingCalculator._

  "A PresentWrappingCalculator" should "parse dimensions" in {
    parseDimensions("10x11x12") should be((10, 11, 12))
  }

  it should "compute wrapping paper area" in {
    computeWrappingPaperArea(2, 3, 4) should be(58)
    computeWrappingPaperArea(1, 1, 10) should be(43)
  }

  it should "compute ribbon length" in {
    computeRibbonLength(2, 3, 4) should be(34)
    computeRibbonLength(1, 1, 10) should be(14)
  }
}
