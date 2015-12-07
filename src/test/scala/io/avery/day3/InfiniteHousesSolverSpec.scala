package io.avery.day3

import org.scalatest.{Matchers, FlatSpec}

class InfiniteHousesSolverSpec extends FlatSpec with Matchers {
  import InfiniteHousesSolver._

  "An InfiniteHousesSolver" should "compute houses locations" in {
    computeLocations(">^<v").reverse should be(List(
      HouseLocation(0, 0),
      HouseLocation(1, 0),
      HouseLocation(1, 1),
      HouseLocation(0, 1),
      HouseLocation(0, 0)
    ))
  }

  it should "compute number of distinctly visited houses" in {
    countVisitedHouses("") should be(1)
    countVisitedHouses(">") should be(2)
    countVisitedHouses("^>v<") should be(4)
    countVisitedHouses("^v^v^v^v^v") should be(2)
  }

  it should "compute number of visited houses with robo santa" in {
    computeVisitedHousesWithRoboSanta("") should be(1)
    computeVisitedHousesWithRoboSanta("^v") should be(3)
    computeVisitedHousesWithRoboSanta("^>v<") should be(3)
    computeVisitedHousesWithRoboSanta("^v^v^v^v^v") should be(11)
  }
}
