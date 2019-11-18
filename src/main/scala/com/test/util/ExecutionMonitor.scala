package com.test.util

import com.test.logger.Logger

object ExecutionMonitor extends Logger {

  def timeExec[T](block: => T): T = {
    val startTime = System.currentTimeMillis()
    val result = block
    val endTime = System.currentTimeMillis()
    logDebug(s"Total exec time ${endTime - startTime} milliseconds")

    result
  }
}
