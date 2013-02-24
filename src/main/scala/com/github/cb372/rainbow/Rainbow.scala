package com.github.cb372.rainbow

import scala.language.implicitConversions

sealed trait Colour { 
  val code:String 
  val r: Int
  val g: Int
  val b: Int
}
case object Black extends Colour { 
  val code: String = "00;30"
  val (r, g, b) = (0, 0, 0)
}
case object DarkGrey extends Colour { 
  val code: String = "01;30" 
  val (r, g, b) = (85, 85, 85)
}
case object LightGrey extends Colour { 
  val code: String = "00;37" 
  val (r, g, b) = (170, 170, 170)
}
case object White extends Colour {
  val code: String = "01;37"
  val (r, g, b) = (255, 255, 255)
}
case object Blue extends Colour {
  val code: String = "00;34"
  val (r, g, b) = (0, 0, 170)
}
case object LightBlue extends Colour {
  val code: String = "01;34"
  val (r, g, b) = (85, 85, 255)
}
case object Green extends Colour {
  val code: String = "00;32"
  val (r, g, b) = (0, 170, 0)
}
case object LightGreen extends Colour {
  val code: String = "01;32"
  val (r, g, b) = (85, 255, 85)
}
case object Cyan extends Colour {
  val code: String = "00;36"
  val (r, g, b) = (0, 170, 170)
}
case object LightCyan extends Colour {
  val code: String = "01;36"
  val (r, g, b) = (85, 255, 255)
}
case object Red extends Colour {
  val code: String = "00;31"
  val (r, g, b) = (170, 0, 0)
}
case object LightRed extends Colour {
  val code: String = "01;31"
  val (r, g, b) = (255, 85, 85)
}
case object Purple extends Colour {
  val code: String = "00;35"
  val (r, g, b) = (170, 0, 170)
}
case object LightPurple extends Colour {
  val code: String = "01;35"
  val (r, g, b) = (255, 85, 255)
}
case object Brown extends Colour {
  val code: String = "00;33"
  val (r, g, b) = (170, 85, 0)
}
case object Yellow extends Colour {
  val code: String = "01;33"
  val (r, g, b) = (255, 255, 85)
}

object Colour {

  val colours = List(Black, DarkGrey, LightGrey, White,
                     Blue, LightBlue, Green, LightGreen,
                     Cyan, LightCyan, Red, LightRed,
                     Purple, LightPurple, Brown, Yellow)

  implicit def fromRGB(rgb: (Int, Int, Int)): Colour = {
    val withDistances = colours.map(c => (c, euclidianDist(c, rgb._1, rgb._2, rgb._3)))
    withDistances.sortBy(_._2).head._1
  }

  implicit def spellColourProperly(color: java.awt.Color): Colour = 
    fromRGB((color.getRed, color.getGreen, color.getBlue))

  private def euclidianDist(c: Colour, r: Int, g: Int, b: Int): Int = 
    Math.abs(c.r - r) + Math.abs(c.g - g) + Math.abs(c.b - b)

}

trait Rainbow {
  def escape(colour: Colour) = s"\\033[${colour.code}m"
  val reset = "\\033[00m"
  
  def rainbowify[C <% Colour](chars: Seq[(Char, C)]): String = {
    val (string, lastColour) = chars.foldLeft[(String, Option[Colour])](("", None)) { 
      case ((acc, prevColour), (char, colour)) if prevColour == Some(colour) => (acc + char, prevColour) // keep the same colour
      case ((acc, prevColour), (char, colour)) => (acc + escape(colour) + char, Some(colour)) // switch to new colour
    }
    return string + reset  
  }
  def rainbowify(string: String, colour: Colour): String =
    s"${escape(colour)}${string}${reset}"

}

object Rainbow extends Rainbow


