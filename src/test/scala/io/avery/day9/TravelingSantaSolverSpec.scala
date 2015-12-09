package io.avery.day9

import org.scalatest.{Matchers, FlatSpec}

class TravelingSantaSolverSpec extends FlatSpec with Matchers {
  import TravelingSantaSolver._

  val lines =
    """
      |A to B = 10
      |A to C = 20
      |C to B = 5
    """.stripMargin.split("\n").filterNot(_.trim.isEmpty).toList

  "A TravelingSantaSolver" should "find all routes" in {
    val routes = Set(
      List(Segment("A", "B" ,10), Segment("B", "C", 5)),
      List(Segment("A", "C", 20), Segment("C", "B", 5)),
      List(Segment("B", "A", 10), Segment("A", "C", 20)),
      List(Segment("B", "C", 5), Segment("C", "A", 20)),
      List(Segment("C", "A", 20), Segment("A", "B", 10)),
      List(Segment("C", "B", 5), Segment("B", "A", 10))
    )
    buildAllRoutes(lines).map(_.reverse).toSet should be(routes)
  }

  it should "find min distance" in {
    findMinRouteDistance(lines) should be(15)
  }

  it should "find max distance" in {
    findMaxRouteDistance(lines) should be(30)
  }
}
