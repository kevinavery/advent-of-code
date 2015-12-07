package io.avery.day6

import io.avery.day6.LightGrid.Coordinate

class DimmingLightGrid(lights: Map[Coordinate, Int]) extends LightGrid[DimmingLightGrid] {

  def this() = this(Map())

  def turnOn(coord: Coordinate): DimmingLightGrid = {
    new DimmingLightGrid(lights.updated(coord, lights.getOrElse(coord, 0) + 1))
  }

  def turnOff(coord: Coordinate): DimmingLightGrid = {
    new DimmingLightGrid(lights.updated(coord, Math.max(lights.getOrElse(coord, 0) - 1, 0)))
  }

  def toggle(coord: Coordinate): DimmingLightGrid = {
    turnOn(coord).turnOn(coord)
  }

  def totalBrightness: Int = {
    lights.values.sum
  }
}
