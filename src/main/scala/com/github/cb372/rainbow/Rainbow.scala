package com.github.cb372.rainbow

sealed trait Colour { val code:String }
case object Black extends Colour { val code: String = "00;30" }
case object DarkGrey extends Colour { val code: String = "01;30" }
case object LightGrey extends Colour { val code: String = "00;37" }
case object White extends Colour { val code: String = "01;37" }
case object Blue extends Colour { val code: String = "00;34" }
case object LightBlue extends Colour { val code: String = "01;34" }
case object Green extends Colour { val code: String = "00;32" }
case object LightGreen extends Colour { val code: String = "01;32" }
case object Cyan extends Colour { val code: String = "00;36" }
case object LightCyan extends Colour { val code: String = "01;36" }
case object Red extends Colour { val code: String = "00;31" }
case object LightRed extends Colour { val code: String = "01;31" }
case object Purple extends Colour { val code: String = "00;35" }
case object LightPurple extends Colour { val code: String = "01;35" }
case object Brown extends Colour { val code: String = "00;33" }
case object Yellow extends Colour { val code: String = "01;33" }

trait Rainbow {
  def escape(colour: Colour) = s"\\e[${colour.code}m"
  val reset = "\\e[00m"
  
  def rainbowify(string: String, colour: Colour): String =
    s"${escape(colour)}${string}${reset}"

  def toEchoCmd(string: String, colour: Colour): String =
    s"""echo -e "${rainbowify(string, colour)}""""
}

object Rainbow extends Rainbow


