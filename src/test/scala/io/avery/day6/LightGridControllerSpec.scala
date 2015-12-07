package io.avery.day6

import org.scalatest.{Matchers, FlatSpec}

class LightGridControllerSpec extends FlatSpec with Matchers {
  import LightGridController._
  
  "A LightGridController" should "turn on lights" in {
    val grid = new BinaryLightGrid
    applyInstruction(grid, "turn on 0,0 through 0,0").countLightsOn should be(1)
    applyInstruction(grid, "turn on 3,3 through 5,5").countLightsOn should be(9)
  }

  it should "turn off lights" in {
    val grid = new BinaryLightGrid
    applyInstruction(grid, "turn off 0,0 through 0,0").countLightsOn should be(0)

    configureLights(grid, List(
      "turn on 3,3 through 5,5",
      "turn off 0,0 through 9,9"
    )).countLightsOn should be(0)
  }

  it should "toggle lights" in {
    val grid = new BinaryLightGrid
    applyInstruction(grid, "toggle 0,0 through 0,0").countLightsOn should be(1)

    configureLights(grid, List(
      "turn on 3,3 through 5,5",
      "toggle 4,4 through 4,4"
    )).countLightsOn should be(8)
  }
}
