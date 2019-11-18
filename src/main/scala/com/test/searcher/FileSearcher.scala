package com.test.searcher

import com.test.Main.logWarning
import com.test.command.Command
import com.test.model.{FileNameWithPercentMatch, FileNameWithWords}
import com.test.util.CommonRegexPattern.whiteSpace

object FileSearcher {
  private val maxSearchFiles = 10

  def findTopNFiles(fileNameWithWords: Array[FileNameWithWords], searchText: String): Array[FileNameWithPercentMatch] = {
    val words = searchText.split(whiteSpace)
    if (words.isEmpty || words.forall(_.isEmpty)) {
      logWarning("Nothing to search")
      Array.empty
    } else {
      fileNameWithWords.map(fileWithContent => fileWithContent -> fileWithContent.searchWords(words))
        .filter(_._2 > 0)
        .sortBy(_._2)
        .reverse
        .take(maxSearchFiles)
        .map { case (fileWithContent: FileNameWithWords, percentMatch: Double) =>
          FileNameWithPercentMatch(fileWithContent.fileName, percentMatch)
        }
    }
  }
}
