package io.avery.day11

import scala.annotation.tailrec

object PasswordGenerator {

  def getPasswords(currentPassword: String): Stream[String] = {
    @tailrec
    def nextPassword(p: String): String = {
      val n = increment(p)
      if (isValid(n)) n else nextPassword(n)
    }

    val next = nextPassword(currentPassword)
    Stream(next) #::: getPasswords(next)
  }

  def increment(p: String): String = {
    def rec(col: Int): String = {
      val c = p.charAt(col)
      if (c == 'z') {
        rec(col - 1) + 'a'
      } else {
        p.substring(0, col) + (c + 1).toChar
      }
    }

    rec(p.length - 1)
  }

  def firstPairIndex(p: String): Int = {
    p.sliding(2).filter{ s =>
      s.length == 2 && s.charAt(0) == s.charAt(1)
    }.toList.headOption.map(p.indexOf).getOrElse(-1)
  }

  def isValid(p: String): Boolean = {
    lazy val hasIllegalChars = List('i', 'o', 'l').exists(c => p.contains(c))
    lazy val hasStraight = p.sliding(3).exists { s =>
      s.charAt(0) == s.charAt(1) - 1 && s.charAt(1) == s.charAt(2) - 1
    }
    lazy val fpi = firstPairIndex(p)
    lazy val spi = firstPairIndex(p.substring(fpi + 1))

    !hasIllegalChars && hasStraight && fpi > 0 && spi > 0
  }
}
