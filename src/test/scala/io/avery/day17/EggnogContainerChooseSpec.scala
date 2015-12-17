package io.avery.day17

import org.scalatest.{Matchers, FlatSpec}

class EggnogContainerChooseSpec extends FlatSpec with Matchers {
  import EggnogContainerChooser._

  val input = List(20, 15, 10, 5, 5).map(_.toString)

  "An EggnogContainerChooser" should "find the number of combinations of containers that hold eggnog" in {
    countContainerCombinations(input, 25) should be(4)
  }

  it should "find the number of combinations that require the minimum number of containers" in {
    countMinContainerCombinations(input, 25) should be(3)
  }
}
