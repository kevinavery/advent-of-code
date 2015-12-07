package io.avery.day6

import io.avery.day6.LightGrid.Coordinate

object LightGrid {
  case class Coordinate(x: Int, y: Int)
}

trait LightGrid[T <: LightGrid[T]] {
  def turnOn(coordinate: Coordinate): T

  def turnOff(coordinate: Coordinate): T

  def toggle(coordinate: Coordinate): T
}
