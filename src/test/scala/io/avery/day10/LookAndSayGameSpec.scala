package io.avery.day10

import org.scalatest.{Matchers, FlatSpec}

class LookAndSayGameSpec extends FlatSpec with Matchers {
  import LookAndSayGame._

  "A LookAndSayGame" should "compute next look-and-say word" in {
    next("1") should be("11")
    next("11") should be("21")
    next("21") should be("1211")
    next("1211") should be("111221")
    next("111221") should be("312211")
  }

  it should "compute length of nth look-and-say word" in {
    nthLength("1", 3) should be(4)
    nthLength("1", 4) should be(6)
    nthLength("1", 5) should be(6)
  }
}
