package com.github.cb372.rainbow

import org.scalatest._
import org.scalatest.matchers._

class RainbowSpec extends FlatSpec with ShouldMatchers {

  behavior of "Rainbow"

  it should "insert correct escape and colour codes" in {
    val result = Rainbow.rainbowify("You've got red on you", Red)
    println(result)
    result should equal("""\033[00;31mYou've got red on you\033[00m""")
  }

  it should "not escape the ANSI codes if it is told not to" in {
    val result = Rainbow.rainbowify("You've got red on you", Red, escape = false)
    result should equal("\033[00;31mYou've got red on you\033[00m")
  }
  
  it should "generate a sensibly coloured string given a list of chars and colours" in {
    val input = List(('a', Red), ('b', Blue), ('c', Blue), ('d', Green))
    val result = Rainbow.rainbowify(input)
    result should equal("""\033[00;31ma\033[00;34mbc\033[00;32md\033[00m""")
  }
  
  it should "generate a sensibly coloured string given a list of chars and RGB colours" in {
    import Colour.fromRGB
    val input = List(('a', (200, 0, 0)))
    val result = Rainbow.rainbowify(input)
    result should equal("""\033[00;31ma\033[00m""")
  }
  
  behavior of "Colour"

  it should "convert a reddish colour to Red" in {
    Colour.fromRGB(200, 0, 0) should equal(Red)
  }

  it should "convert java.awt Red to Red" in {
    Colour.spellColourProperly(java.awt.Color.RED) should equal(Red)
  }
}
