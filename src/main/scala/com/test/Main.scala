package com.test

import java.io.File

import com.test.file.FileReader.loadAllFilesIntoMemory
import com.test.logger.Logger
import com.test.model.FileNameWithPercentMatch
import com.test.processing.Processing
import com.test.util.ExecutionMonitor.timeExec

object Main extends App with Logger {
  args.headOption.map(new File(_)) match {
    case Some(directory) if directory.isDirectory =>
      loadFilesAndSearch(directory)

    case Some(file) =>
      val errorMessage = s"$file is not a directory"
      logError(errorMessage)
      throw new IllegalArgumentException(errorMessage)
    case None =>
      throw new IllegalArgumentException("No directory to index")
  }

  private def loadFilesAndSearch(directory: File): Unit = {
    val fileNameWithWords = timeExec {
      loadAllFilesIntoMemory(directory)
    }

    if (fileNameWithWords.isEmpty) {
      logWarning("No such files in directory")
    } else {
      while (true) {
        logInfo("search>")
        val searchText = scala.io.StdIn.readLine()
        timeExec {
          Processing.processStdinCommand(
            fileNameWithWords,
            searchText
          ).zipWithIndex
            .foreach { case (fileNameWithPercentMatch: FileNameWithPercentMatch, index) =>
              logInfo(s"$index) ${fileNameWithPercentMatch.fileName}: ${fileNameWithPercentMatch.percentMatch}%")
            }
        }
      }
    }
  }
}
