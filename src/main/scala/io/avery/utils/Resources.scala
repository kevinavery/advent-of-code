package io.avery.utils

import scala.io.Source

trait Resources {
  def getResourceLines(filename: String): Iterator[String] = {
    Source.fromInputStream(getClass.getResourceAsStream(s"/${getClass.getPackage.getName}/$filename")).getLines
  }
}
