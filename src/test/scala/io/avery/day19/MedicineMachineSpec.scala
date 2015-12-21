package io.avery.day19

import org.scalatest.{Matchers, FlatSpec}

class MedicineMachineSpec extends FlatSpec with Matchers {
  import MedicineMachine._

  "A MedicineMachine" should "find replacements" in {
    val transforms = Map(
      "H" -> List("HO", "OH"),
      "O" -> List("HH")
    )

    val rs = replaceOne(transforms, "HOH")
    rs.size should be(5)
    rs.distinct.size should be(4)

    replaceOne(transforms, "HOHOHO").distinct.size should be(7)
  }

  it should "count steps of decomposition" in {
    val reverseTransforms = Map(
      "H" -> "e",
      "O" -> "e",
      "HO" -> "H",
      "OH" -> "H",
      "HH" -> "O"
    )

    countDecompositions(reverseTransforms, "e", "HOH").min should be(3)
    countDecompositions(reverseTransforms, "e", "HOHOHO").min should be(6)
  }
}
