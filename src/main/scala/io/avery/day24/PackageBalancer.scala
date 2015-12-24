package io.avery.day24

object PackageBalancer {

  def findMinQuantumEntanglement(input: List[String], numGroups: Int): Long = {
    groupsWithMinSize(input.map(_.toLong), numGroups).map(_.product).min
  }

  def groupsWithMinSize(elements: List[Long], numGroups: Int): List[List[Long]] = {
    val groupSum = elements.sum / numGroups
    var minSize = Int.MaxValue

    def rec(remaining: List[Long], group: List[Long]): List[List[Long]] = {
      remaining match {
        case Nil => if (group.sum == groupSum && group.size <= minSize) {
          minSize = group.size
          List(group)
        } else {
          Nil
        }
        case h :: t => {
          val withH = if (group.sum + h <= groupSum && group.size + 1 <= minSize) {
            rec(t, h :: group)
          } else {
            Nil
          }
          withH ++ rec(t, group)
        }
      }
    }

    rec(elements, Nil).filter(_.size == minSize)
  }
}
