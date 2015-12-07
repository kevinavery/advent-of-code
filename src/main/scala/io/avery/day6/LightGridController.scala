package io.avery.day6

import io.avery.day6.LightGrid.Coordinate

object LightGridController {
  def configureLights[T <: LightGrid[T]](grid: T, instructions: List[String]): T = {
    instructions.foldLeft(grid)(applyInstruction)
  }

  def applyInstruction[T <: LightGrid[T]](grid: T, instruction: String): T = {
    val pattern = "(.+) (\\d+),(\\d+) through (\\d+),(\\d+)".r

    instruction match {
      case pattern(opStr, fromX, fromY, toX, toY) => {
        val coords = for {
          x <- fromX.toInt to toX.toInt
          y <- fromY.toInt to toY.toInt
        } yield Coordinate(x, y)

        val op: (T, Coordinate) => T = (g, c) => opStr match {
          case "turn on" => g.turnOn(c)
          case "turn off" => g.turnOff(c)
          case "toggle" => g.toggle(c)
        }

        coords.foldLeft(grid)(op)
      }
    }
  }
}
