package io.avery.day20

import scala.annotation.tailrec

object ElfDeliveryCalculator {
  def findHouse(presentsGoal: Int): Int = {
    @tailrec
    def rec(houseNum: Int): Int = {
      val p = factors(houseNum).sum * 10

      if (p >= presentsGoal) {
        houseNum
      } else {
        rec(houseNum + 1)
      }
    }

    rec(0)
  }

  def findHouseLazyElves(presentsGoal: Int): Int = {
    @tailrec
    def rec(houseNum: Int, factorCounts: Map[Int, Int]): Int = {
      val fs = factors(houseNum).filter(f => factorCounts.getOrElse(f, 0) < 50)
      val p = fs.sum * 11

      if (p >= presentsGoal) {
        houseNum
      } else {
        val newFactorCounts = fs.foldLeft(factorCounts) { (fcs, f) =>
          fcs.updated(f, fcs.getOrElse(f, 0) + 1)
        }
        rec(houseNum + 1, newFactorCounts)
      }
    }

    rec(0, Map())
  }

  def factors(n: Int): List[Int] = {
    1.to(Math.sqrt(n).toInt).foldLeft(List[Int]()) { (fs, x) =>
      if (n % x == 0) {
        val y = n / x
        if (y != x) {
          x :: y :: fs
        } else {
          x :: fs
        }
      } else {
        fs
      }
    }
  }
}
