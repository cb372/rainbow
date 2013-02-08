package com.github.cb372.rainbow

import org.scalatest._
import org.scalatest.matchers._

class RainbowSpec extends FlatSpec with ShouldMatchers {

  behavior of "Rainbow"

  it should "insert correct escape and colour codes" in {
    val result = Rainbow.rainbowify("You've got red on you", Red)
    result should equal("""\e[00;31mYou've got red on you\e[00m""")
  }
  
  it should "generate correct echo command" in {
    val result = Rainbow.toEchoCmd("You've got red on you", Red)
    result should equal("""echo -e "\e[00;31mYou've got red on you\e[00m"""")
  }

}
