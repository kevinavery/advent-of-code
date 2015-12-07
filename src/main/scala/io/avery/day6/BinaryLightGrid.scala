package io.avery.day6

import io.avery.day6.LightGrid.Coordinate

class BinaryLightGrid(lights: Set[Coordinate]) extends LightGrid[BinaryLightGrid] {

  def this() = this(Set())

  def turnOn(coord: Coordinate): BinaryLightGrid = {
    new BinaryLightGrid(lights + coord)
  }

  def turnOff(coord: Coordinate): BinaryLightGrid = {
    new BinaryLightGrid(lights - coord)
  }

  def toggle(coord: Coordinate): BinaryLightGrid = {
    if (lights.contains(coord)) {
      turnOff(coord)
    } else {
      turnOn(coord)
    }
  }

  def countLightsOn: Int = {
    lights.size
  }
}
