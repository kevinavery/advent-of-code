package io.avery.day6

import io.avery.utils.Resources

object ChallengeRunner extends App with Resources {
  val input = getResourceLines("input.txt").toList
  println(LightGridController.configureLights(new BinaryLightGrid, input).countLightsOn)
  println(LightGridController.configureLights(new DimmingLightGrid, input).totalBrightness)
}
