package io.avery.day7

import org.scalatest.{Matchers, FlatSpec}

class CircuitEmulatorSpec extends FlatSpec with Matchers {
  import CircuitEmulator._

  "A CircuitEmulator" should "solve circut" in {
    val instructions =
      """
        |123 -> x
        |456 -> y
        |x AND y -> d
        |x OR y -> e
        |x LSHIFT 2 -> f
        |y RSHIFT 2 -> g
        |NOT x -> h
        |NOT y -> i
      """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

    solve(instructions, "d") should be(72)
    solve(instructions, "e") should be(507)
    solve(instructions, "f") should be(492)
    solve(instructions, "g") should be(114)
    solve(instructions, "h") should be(65412)
    solve(instructions, "i") should be(65079)
    solve(instructions, "x") should be(123)
    solve(instructions, "y") should be(456)
  }
}
