package com.test.command

import com.test.logger.Logger

sealed trait Command {
  def text: String
  def shortcuts: Array[String]
  val regexp = s"""$text|${shortcuts.mkString("|")}"""
}

case object Quite extends Command {
  override def text: String = ":quite"

  override def shortcuts: Array[String] = Array(":q", ":exit")

}

case object Help extends Command {

  override def text: String = ":help"

  override def shortcuts: Array[String] = Array(":h")

}

object Command extends Logger {
  val startCommandSign: String = ":"

  def processCommand(command: String): Unit = {
    command match {
      case Quite.regexp.r(_*) => System.exit(1)
      case Help.regexp.r(_*) =>
        logError("Not implemented yet. Maybe in the future.")
      case _ => logWarning("Unrecognized com.test.command")
    }
  }
}
