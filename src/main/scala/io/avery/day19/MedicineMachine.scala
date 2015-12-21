package io.avery.day19

object MedicineMachine {

  val Pattern = "(.+) => (.+)".r

  def countSingleTransformMolecules(input: List[String]): Int = {
    val m = input.filter(s => Pattern.findFirstMatchIn(s).isDefined).foldLeft(Map[String, List[String]]()) {
      case (m, Pattern(a, b)) => m.updated(a, b :: m.getOrElse(a, Nil))
    }
    val start = input.last

    replaceOne(m, start).distinct.size
  }

  def countModelFabricationSteps(input: List[String]): Int = {
    val mr = input.filter(s => Pattern.findFirstMatchIn(s).isDefined).foldLeft(Map[String, String]()) {
      case (m, Pattern(a, b)) => m.updated(b, a)
    }
    val start = input.last

    // It takes too long to compute the true min, so take the first 100 results and use that min.. worked!
    countDecompositions(mr, "e", start).take(100).min
  }

  def countDecompositions(reverseTransformations: Map[String, String], goal: String, start: String): Stream[Int] = {
    val sortedKeys = reverseTransformations.keys.toList.sorted.reverse

    def rec(current: String, numSteps: Int): Stream[Int] = {
      if (current == goal) {
        Stream(numSteps)
      } else {
        sortedKeys.toStream.filter(current.contains).flatMap { k =>
          val idx = current.indexOf(k)
          val prefix = current.substring(0, idx)
          val suffix = current.substring(idx + k.length)
          rec(prefix + reverseTransformations(k) + suffix, numSteps + 1)
        }
      }
    }

    rec(start, 0)
  }

  def replaceOne(transformations: Map[String, List[String]], start: String): List[String] = {
    def rec(remainingInput: String): List[String] = {
      if (remainingInput.length == 0) {
        Nil
      } else {
        transformations.keys.toList.filter(remainingInput.startsWith).flatMap { k =>
          val prefix = start.substring(0, start.length - remainingInput.length)
          val suffix = remainingInput.substring(k.length)
          transformations(k).map(prefix + _ + suffix)
        } ++ rec(remainingInput.substring(1))
      }
    }

    rec(start)
  }
}
