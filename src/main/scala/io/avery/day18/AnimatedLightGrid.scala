package io.avery.day18

object AnimatedLightGrid {
  type LightState = Char
  type Grid = Vector[Vector[LightState]]

  val On: LightState = '#'
  val Off: LightState = '.'

  def animateLights(input: List[String], iterations: Int): Int = {
    val grid = input.toVector.map(_.toVector)
    countLightsOn(1.to(iterations).foldLeft(grid) { (grid, _) =>
      computeNewGrid(grid)
    })
  }

  def animateLightsWithStuckCorners(input: List[String], iterations: Int): Int = {
    val grid = input.toVector.map(_.toVector)
    countLightsOn(1.to(iterations).foldLeft(forceCornersOn(grid)) { (grid, _) =>
      forceCornersOn(computeNewGrid(grid))
    })
  }

  def countLightsOn(grid: Grid): Int = {
    grid.flatten.count(_ == On)
  }

  def forceCornersOn(grid: Grid): Grid = {
    List((0, 0), (0, grid(0).size - 1), (grid.size - 1, 0), (grid(grid.size - 1).size - 1, grid.size - 1)).foldLeft(grid) {
      case (grid, (r, c)) => grid.updated(r, grid(r).updated(c, On))
    }
  }

  def computeNewGrid(grid: Grid): Grid = {
    val coords = for {
      r <- 0.until(grid.size)
      c <- 0.until(grid(r).size)
    } yield (r, c)

    coords.foldLeft(grid) {
      case (newGrid, (r, c)) => newGrid.updated(r, newGrid(r).updated(c, computeNewLightState(grid, r, c)))
    }
  }

  def computeNewLightState(grid: Grid, row: Int, col: Int): LightState = {
    val neighborLights = for {
      r <- row - 1 to row + 1
      if r >= 0 && r < grid.size
      c <- col - 1 to col + 1
      if c >= 0 && c < grid(r).size && !(r == row && c == col)
    } yield grid(r)(c)

    val numLightsOn = neighborLights.count(_ == On)

    if (grid(row)(col) == On) {
      if (numLightsOn == 2 || numLightsOn == 3) On else Off
    } else {
      if (numLightsOn == 3) On else Off
    }
  }
}
