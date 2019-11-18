package com.test.file

import java.io.{ByteArrayOutputStream, File, FileInputStream}

import com.test.logger.Logger
import com.test.model.FileNameWithWords
import com.test.util.CommonRegexPattern.whiteSpace

object FileReader extends Logger {

  def fileToWords(file: File, encoding: String = "UTF-8"): String = {
    val inputStream = new FileInputStream(file)
    val outputStream = new ByteArrayOutputStream

    try {
      var reading = true
      while ( reading ) {
        inputStream.read() match {
          case -1 => reading = false
          case c => outputStream.write(c)
        }
      }
      outputStream.flush()
    }
    finally {
      inputStream.close()
    }
    new String(outputStream.toByteArray, encoding)
  }

  def loadAllFilesIntoMemory(directory: File): Array[FileNameWithWords] = {
    val files = directory.listFiles()
    val fileNameWithContent = files.filter(_.isFile).map { file =>
      val content = FileReader.fileToWords(file)
      val words = content.split(whiteSpace)

      FileNameWithWords(file.getName, words)
    }

    logInfo(s"${fileNameWithContent.length} files read in directory")
    fileNameWithContent
  }

}
