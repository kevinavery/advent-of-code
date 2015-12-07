package io.avery.day4

import org.apache.commons.codec.digest.DigestUtils
import scala.annotation.tailrec

object AdventCoinMiner {

  def findFirst(secretKey: String, hashPrefix: String): Int = {
    @tailrec
    def solveRec(n: Int): Int = {
      if (DigestUtils.md5Hex(s"$secretKey$n").startsWith(hashPrefix)) {
        n
      } else if (n + 1 > 0) {
        solveRec(n + 1)
      } else {
        throw new NoSuchElementException
      }
    }

    solveRec(0)
  }
}
