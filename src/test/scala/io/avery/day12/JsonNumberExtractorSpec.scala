package io.avery.day12

import org.scalatest.{Matchers, FlatSpec}

class JsonNumberExtractorSpec extends FlatSpec with Matchers {
  import JsonNumberExtractor._

  "A JsonNumberExtractor" should "extract and sum all numbers" in {
    sumAllNumbers("[1,2,3]") should be(6)
    sumAllNumbers("{\"a\":2,\"b\":4}") should be(6)
    sumAllNumbers("[[[3]]]") should be(3)
    sumAllNumbers("{\"a\":{\"b\":4},\"c\":-1}") should be(3)
    sumAllNumbers("{\"a\":[-1,1]}") should be(0)
    sumAllNumbers("[-1,{\"a\":1}]") should be(0)
    sumAllNumbers("[]") should be(0)
    sumAllNumbers("{}") should be(0)
  }

  it should "extract and sum all numbers unless object has red field" in {
    sumAllNumbersWithoutRed("[1,2,3]") should be(6)
    sumAllNumbersWithoutRed("[1,{\"c\":\"red\",\"b\":2},3]") should be(4)
    sumAllNumbersWithoutRed("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}") should be(0)
    sumAllNumbersWithoutRed("[1,\"red\",5]") should be(6)
  }
}
