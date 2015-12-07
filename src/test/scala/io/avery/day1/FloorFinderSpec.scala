package io.avery.day1

import org.scalatest.{Matchers, FlatSpec}

class FloorFinderSpec extends FlatSpec with Matchers {

  "A FloorFinder" should "find the final floor" in {
    import FloorFinder._

    findEndFloor("(())") should be(0)
    findEndFloor("()()") should be(0)
    findEndFloor("(()(()(") should be(3)
    findEndFloor(")())())") should be(-3)
  }
}
