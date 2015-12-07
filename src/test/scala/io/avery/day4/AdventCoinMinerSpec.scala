package io.avery.day4

import org.scalatest.{Matchers, FlatSpec}

class AdventCoinMinerSpec extends FlatSpec with Matchers {
  import AdventCoinMiner._

  "An AdventCoinMiner" should "find first coin" in {
    findFirst("abcdef", "00000") should be(609043)
    findFirst("pqrstuv", "00000") should be(1048970)
  }
}
