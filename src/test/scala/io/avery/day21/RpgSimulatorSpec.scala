package io.avery.day21

import org.scalatest.{Matchers, FlatSpec}

class RpgSimulatorSpec extends FlatSpec with Matchers {
  import RpgSimulator._
  import RpgSimulator.CharacterNames._

  "An RpgSimulator" should "simulate game" in {
    val winner = simulateGame(Character(Player, 8, 5, 5), Character(Boss, 12, 7, 2))
    winner.name should be(Player)
  }
}
