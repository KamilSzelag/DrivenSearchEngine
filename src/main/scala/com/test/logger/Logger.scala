package com.test.logger

trait Logger {

  def logInfo(message: String): Unit ={
    logMessage(LogLevel.INFO, message)
  }

  def logWarning(message: String): Unit ={
    logMessage(LogLevel.WARN, message)
  }

  def logDebug(message: String): Unit ={
    logMessage(LogLevel.DEBUG, message)
  }

  def logError(message: String): Unit ={
    logMessage(LogLevel.ERROR, message)
  }

  private def logMessage(logLevel: LogLevel, message: String): Unit = {
    println(s"[$logLevel] $message")
  }
}
