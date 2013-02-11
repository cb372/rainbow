package com.github.cb372.rainbow

import org.scalatest._
import org.scalatest.matchers._

class RainbowSpec extends FlatSpec with ShouldMatchers {

  behavior of "Rainbow"

  it should "insert correct escape and colour codes" in {
    val result = Rainbow.rainbowify("You've got red on you", Red)
    result should equal("""\e[00;31mYou've got red on you\e[00m""")
  }
  
  it should "generate a sensibly coloured string given a list of chars and colours" in {
    val input = List(('a', Red), ('b', Blue), ('c', Blue), ('d', Green))
    val result = Rainbow.rainbowify(input)
    result should equal("""\e[00;31ma\e[00;34mbc\e[00;32md\e[00m""")
  }
  
  it should "generate correct echo command" in {
    val result = Rainbow.toEchoCmd("You've got red on you", Red)
    result should equal("""echo -e "\e[00;31mYou've got red on you\e[00m"""")
  }

}
